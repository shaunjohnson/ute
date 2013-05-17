package net.lmxm.ute.utils.aether;

import net.lmxm.ute.event.JobStatusChangeEventBus;
import net.lmxm.ute.event.StatusChangeEventBus;
import org.sonatype.aether.AbstractRepositoryListener;
import org.sonatype.aether.RepositoryEvent;

import static com.google.inject.internal.util.$Preconditions.checkNotNull;
import static net.lmxm.ute.resources.types.StatusChangeMessageResourceType.*;

/**
 * A simplistic repository listener that logs events to the console.
 */
public class AetherRepositoryListener extends AbstractRepositoryListener {

    /**
     * Job status change event bus.
     */
    private final JobStatusChangeEventBus jobStatusChangeEventBus;

    public AetherRepositoryListener(final JobStatusChangeEventBus jobStatusChangeEventBus) {
        this.jobStatusChangeEventBus = checkNotNull(jobStatusChangeEventBus, "Job status change event bus may not be null");
    }

    public void artifactDeployed(RepositoryEvent event) {
        jobStatusChangeEventBus.info(MAVEN_REPOSITORY_ARTIFACT_DEPLOYED, event.getArtifact(), event.getRepository());
    }

    public void artifactDeploying(RepositoryEvent event) {
        jobStatusChangeEventBus.info(MAVEN_REPOSITORY_ARTIFACT_DEPLOYING, event.getArtifact(), event.getRepository());
    }

    public void artifactDescriptorInvalid(RepositoryEvent event) {
        jobStatusChangeEventBus.info(MAVEN_REPOSITORY_ARTIFACT_INVALID_DESCRIPTOR, event.getArtifact(),
                event.getException().getMessage());
    }

    public void artifactDescriptorMissing(RepositoryEvent event) {
        jobStatusChangeEventBus.info(MAVEN_REPOSITORY_ARTIFACT_MISSING_DESCRIPTOR, event.getArtifact());
    }

    public void artifactInstalled(RepositoryEvent event) {

    }

    public void artifactInstalling(RepositoryEvent event) {

    }

    public void artifactResolved(RepositoryEvent event) {
        jobStatusChangeEventBus.info(MAVEN_REPOSITORY_ARTIFACT_RESOLVED, event.getArtifact(), event.getRepository());
    }

    public void artifactDownloading(RepositoryEvent event) {
        jobStatusChangeEventBus.info(MAVEN_REPOSITORY_ARTIFACT_DOWNLOADING, event.getArtifact(), event.getRepository());
    }

    public void artifactDownloaded(RepositoryEvent event) {
        jobStatusChangeEventBus.info(MAVEN_REPOSITORY_ARTIFACT_DOWNLOADED, event.getArtifact(), event.getRepository());
    }

    public void artifactResolving(RepositoryEvent event) {
        jobStatusChangeEventBus.info(MAVEN_REPOSITORY_ARTIFACT_RESOLVING, event.getArtifact());
    }

    public void metadataDeployed(RepositoryEvent event) {
        jobStatusChangeEventBus.info(MAVEN_REPOSITORY_METADATA_DEPLOYED, event.getMetadata(), event.getRepository());
    }

    public void metadataDeploying(RepositoryEvent event) {
        jobStatusChangeEventBus.info(MAVEN_REPOSITORY_METADATA_DEPLOYING, event.getMetadata(), event.getRepository());
    }

    public void metadataInstalled(RepositoryEvent event) {

    }

    public void metadataInstalling(RepositoryEvent event) {

    }

    public void metadataInvalid(RepositoryEvent event) {

    }

    public void metadataResolved(RepositoryEvent event) {
        jobStatusChangeEventBus.info(MAVEN_REPOSITORY_METADATA_RESOLVED, event.getMetadata(), event.getRepository());
    }

    public void metadataResolving(RepositoryEvent event) {
        jobStatusChangeEventBus.info(MAVEN_REPOSITORY_METADATA_RESOLVING, event.getMetadata(), event.getRepository());
    }
}
