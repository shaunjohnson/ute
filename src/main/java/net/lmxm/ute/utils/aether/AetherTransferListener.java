package net.lmxm.ute.utils.aether;

import static net.lmxm.ute.resources.types.StatusChangeMessageResourceType.MAVEN_REPOSITORY_DOWNLOAD_INITIATED;
import static net.lmxm.ute.resources.types.StatusChangeMessageResourceType.MAVEN_REPOSITORY_DOWNLOAD_SUCCEEDED;
import static net.lmxm.ute.resources.types.StatusChangeMessageResourceType.MAVEN_REPOSITORY_TRANSFER_COMPLETED;
import static net.lmxm.ute.resources.types.StatusChangeMessageResourceType.MAVEN_REPOSITORY_TRANSFER_FAILED;
import static net.lmxm.ute.resources.types.StatusChangeMessageResourceType.MAVEN_REPOSITORY_TRANSFER_CORRUPTED;
import static net.lmxm.ute.resources.types.StatusChangeMessageResourceType.MAVEN_REPOSITORY_TRANSFER_PROGRESSED;
import static net.lmxm.ute.resources.types.StatusChangeMessageResourceType.MAVEN_REPOSITORY_TRANSFER_STARTED;
import static net.lmxm.ute.resources.types.StatusChangeMessageResourceType.MAVEN_REPOSITORY_UPLOAD_INITIATED;
import static net.lmxm.ute.resources.types.StatusChangeMessageResourceType.MAVEN_REPOSITORY_UPLOAD_SUCCEEDED;

import net.lmxm.ute.event.StatusChangeHelper;
import net.lmxm.ute.resources.types.StatusChangeMessageResourceType;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.sonatype.aether.transfer.TransferCancelledException;
import org.sonatype.aether.transfer.TransferEvent;
import org.sonatype.aether.transfer.TransferListener;
import org.sonatype.aether.transfer.TransferResource;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AetherTransferListener implements TransferListener {

    private Map<TransferResource, Long> downloads = new ConcurrentHashMap<TransferResource, Long>();

    private int lastLength;

    /**
     * Status change helper used to relay changes of status to the caller.
     */
    private final StatusChangeHelper statusChangeHelper;

    public AetherTransferListener(final StatusChangeHelper statusChangeHelper) {
        super();

        this.statusChangeHelper = statusChangeHelper;
    }

    private String getStatus(long complete, long total) {
        if (total >= 1024) {
            return toKB(complete) + "/" + toKB(total) + " KB ";
        }
        else if (total >= 0) {
            return complete + "/" + total + " B ";
        }
        else if (complete >= 1024) {
            return toKB(complete) + " KB ";
        }
        else {
            return complete + " B ";
        }
    }

//    private void pad(StringBuilder buffer, int spaces) {
//        String block = "                                        ";
//        while (spaces > 0) {
//            int n = Math.min(spaces, block.length());
//            buffer.append(block, 0, n);
//            spaces -= n;
//        }
//    }

    protected long toKB(long bytes) {
        return (bytes + 1023) / 1024;
    }

    private void transferCompleted(TransferEvent event) {
        downloads.remove(event.getResource());
//
//        final StringBuilder buffer = new StringBuilder(64);
//        pad(buffer, lastLength);
//
//        statusChangeHelper.info(this, MAVEN_REPOSITORY_TRANSFER_COMPLETED, buffer.toString());
    }

    @Override
    public void transferCorrupted(TransferEvent event) throws TransferCancelledException {
        statusChangeHelper.info(this, MAVEN_REPOSITORY_TRANSFER_CORRUPTED,
                ExceptionUtils.getFullStackTrace(event.getException()));
    }

    @Override
    public void transferInitiated(TransferEvent event) throws TransferCancelledException {
        final StatusChangeMessageResourceType type = event.getRequestType() == TransferEvent.RequestType.PUT ?
                MAVEN_REPOSITORY_UPLOAD_INITIATED : MAVEN_REPOSITORY_DOWNLOAD_INITIATED;
        final TransferResource resource = event.getResource();
        statusChangeHelper.info(this, type, resource.getRepositoryUrl(), resource.getResourceName());
    }

    /**
     * Transfer of the artifact has started. Reported to the user along with current progress.
     *
     * @param event Information about the transfer event
     * @throws TransferCancelledException Thrown if the transfer should be cancelled
     */
    @Override
    public void transferStarted(TransferEvent event) throws TransferCancelledException {
        final TransferResource resource = event.getResource();
        downloads.put(resource, event.getTransferredBytes());

        final StringBuilder buffer = new StringBuilder();

        for (Map.Entry<TransferResource, Long> entry : downloads.entrySet()) {
            final long total = entry.getKey().getContentLength();
            final long complete = entry.getValue();

            buffer.append(getStatus(complete, total)).append("  ");
        }

//        final int pad = lastLength - buffer.length();
//        lastLength = buffer.length();
//        pad(buffer, pad);

        statusChangeHelper.info(this, MAVEN_REPOSITORY_TRANSFER_STARTED, buffer);
    }

    /**
     * Transfer of the artifact has progressed. Reported current progress to the user.
     *
     * @param event Information about the transfer event
     * @throws TransferCancelledException Thrown if the transfer should be cancelled
     */
    @Override
    public void transferProgressed(TransferEvent event) throws TransferCancelledException {
        final TransferResource resource = event.getResource();
        downloads.put(resource, event.getTransferredBytes());

        final StringBuilder buffer = new StringBuilder();

        for (Map.Entry<TransferResource, Long> entry : downloads.entrySet()) {
            final long total = entry.getKey().getContentLength();
            final long complete = entry.getValue();

            buffer.append(getStatus(complete, total)).append("  ");
        }
//
//        int pad = lastLength - buffer.length();
//        lastLength = buffer.length();
//        pad(buffer, pad);

        statusChangeHelper.info(this, MAVEN_REPOSITORY_TRANSFER_PROGRESSED, buffer);
    }

    @Override
    public void transferSucceeded(TransferEvent event) {
        transferCompleted(event);

        final TransferResource resource = event.getResource();
        final long contentLength = event.getTransferredBytes();
        if (contentLength >= 0) {
            final StatusChangeMessageResourceType type = event.getRequestType() == TransferEvent.RequestType.PUT ?
                    MAVEN_REPOSITORY_UPLOAD_SUCCEEDED : MAVEN_REPOSITORY_DOWNLOAD_SUCCEEDED;
            final String len = contentLength >= 1024 ? toKB(contentLength) + " KB" : contentLength + " B";

            final String throughput;
            long duration = System.currentTimeMillis() - resource.getTransferStartTime();
            if (duration > 0) {
                final DecimalFormat format = new DecimalFormat("0.0", new DecimalFormatSymbols(Locale.ENGLISH));
                double kbPerSec = (contentLength / 1024.0) / (duration / 1000.0);
                throughput = " at " + format.format(kbPerSec) + " KB/sec";
            }
            else {
                throughput = "";
            }

            statusChangeHelper.info(this, type, resource.getRepositoryUrl(), resource.getResourceName(), len, throughput);
        }
    }

    @Override
    public void transferFailed(TransferEvent event) {
        transferCompleted(event);

        statusChangeHelper.info(this, MAVEN_REPOSITORY_TRANSFER_FAILED,
                ExceptionUtils.getFullStackTrace(event.getException()));
    }
}
