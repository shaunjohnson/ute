package net.lmxm.ute.utils.aether;

import static net.lmxm.ute.resources.types.StatusChangeMessageResourceType.MAVEN_REPOSITORY_ARTIFACT_DEPLOYED;
import static net.lmxm.ute.resources.types.StatusChangeMessageResourceType.MAVEN_REPOSITORY_ARTIFACT_DEPLOYING;
import static net.lmxm.ute.resources.types.StatusChangeMessageResourceType.MAVEN_REPOSITORY_ARTIFACT_DOWNLOADED;
import static net.lmxm.ute.resources.types.StatusChangeMessageResourceType.MAVEN_REPOSITORY_ARTIFACT_DOWNLOADING;
import static net.lmxm.ute.resources.types.StatusChangeMessageResourceType.MAVEN_REPOSITORY_ARTIFACT_INVALID_DESCRIPTOR;
import static net.lmxm.ute.resources.types.StatusChangeMessageResourceType.MAVEN_REPOSITORY_ARTIFACT_MISSING_DESCRIPTOR;
import static net.lmxm.ute.resources.types.StatusChangeMessageResourceType.MAVEN_REPOSITORY_ARTIFACT_RESOLVED;
import static net.lmxm.ute.resources.types.StatusChangeMessageResourceType.MAVEN_REPOSITORY_ARTIFACT_RESOLVING;
import static net.lmxm.ute.resources.types.StatusChangeMessageResourceType.MAVEN_REPOSITORY_METADATA_DEPLOYED;
import static net.lmxm.ute.resources.types.StatusChangeMessageResourceType.MAVEN_REPOSITORY_METADATA_DEPLOYING;
import static net.lmxm.ute.resources.types.StatusChangeMessageResourceType.MAVEN_REPOSITORY_METADATA_RESOLVED;
import static net.lmxm.ute.resources.types.StatusChangeMessageResourceType.MAVEN_REPOSITORY_METADATA_RESOLVING;

import net.lmxm.ute.event.StatusChangeHelper;
import org.sonatype.aether.AbstractRepositoryListener;
import org.sonatype.aether.RepositoryEvent;

import java.io.PrintStream;

/**
 * A simplistic repository listener that logs events to the console.
 */
public class AetherRepositoryListener extends AbstractRepositoryListener {

    /**
     * Status change helper used to relay changes of status to the caller.
     */
    private final StatusChangeHelper statusChangeHelper;

    public AetherRepositoryListener(final StatusChangeHelper statusChangeHelper) {
        super();

        this.statusChangeHelper = statusChangeHelper;
    }

    public void artifactDeployed(RepositoryEvent event) {
        statusChangeHelper.info(this, MAVEN_REPOSITORY_ARTIFACT_DEPLOYED, event.getArtifact(), event.getRepository());
    }

    public void artifactDeploying(RepositoryEvent event) {
        statusChangeHelper.info(this, MAVEN_REPOSITORY_ARTIFACT_DEPLOYING, event.getArtifact(), event.getRepository());
    }

    public void artifactDescriptorInvalid(RepositoryEvent event) {
        statusChangeHelper.info(this, MAVEN_REPOSITORY_ARTIFACT_INVALID_DESCRIPTOR, event.getArtifact(),
                event.getException().getMessage());
    }

    public void artifactDescriptorMissing(RepositoryEvent event) {
        statusChangeHelper.info(this, MAVEN_REPOSITORY_ARTIFACT_MISSING_DESCRIPTOR, event.getArtifact());
    }

    public void artifactInstalled(RepositoryEvent event) {

    }

    public void artifactInstalling(RepositoryEvent event) {

    }

    public void artifactResolved(RepositoryEvent event) {
        statusChangeHelper.info(this, MAVEN_REPOSITORY_ARTIFACT_RESOLVED, event.getArtifact(), event.getRepository());
    }

    public void artifactDownloading(RepositoryEvent event) {
        statusChangeHelper.info(this, MAVEN_REPOSITORY_ARTIFACT_DOWNLOADING, event.getArtifact(), event.getRepository());
    }

    public void artifactDownloaded(RepositoryEvent event) {
        statusChangeHelper.info(this, MAVEN_REPOSITORY_ARTIFACT_DOWNLOADED, event.getArtifact(), event.getRepository());
    }

    public void artifactResolving(RepositoryEvent event) {
        statusChangeHelper.info(this, MAVEN_REPOSITORY_ARTIFACT_RESOLVING, event.getArtifact());
    }

    public void metadataDeployed(RepositoryEvent event) {
        statusChangeHelper.info(this, MAVEN_REPOSITORY_METADATA_DEPLOYED, event.getMetadata(), event.getRepository());
    }

    public void metadataDeploying(RepositoryEvent event) {
        statusChangeHelper.info(this, MAVEN_REPOSITORY_METADATA_DEPLOYING, event.getMetadata(), event.getRepository());
    }

    public void metadataInstalled(RepositoryEvent event) {

    }

    public void metadataInstalling(RepositoryEvent event) {

    }

    public void metadataInvalid(RepositoryEvent event) {

    }

    public void metadataResolved(RepositoryEvent event) {
        statusChangeHelper.info(this, MAVEN_REPOSITORY_METADATA_RESOLVED, event.getMetadata(), event.getRepository());
    }

    public void metadataResolving(RepositoryEvent event) {
        statusChangeHelper.info(this, MAVEN_REPOSITORY_METADATA_RESOLVING, event.getMetadata(), event.getRepository());
    }
}
