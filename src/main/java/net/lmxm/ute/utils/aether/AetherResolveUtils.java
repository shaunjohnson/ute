package net.lmxm.ute.utils.aether;

import net.lmxm.ute.event.JobStatusChangeEventBus;
import net.lmxm.ute.exceptions.TaskExecuterException;
import net.lmxm.ute.resources.types.ExceptionResourceType;
import org.apache.maven.repository.internal.MavenRepositorySystemSession;
import org.codehaus.plexus.DefaultPlexusContainer;
import org.codehaus.plexus.PlexusContainerException;
import org.codehaus.plexus.component.repository.exception.ComponentLookupException;
import org.codehaus.plexus.util.FileUtils;
import org.codehaus.plexus.util.StringUtils;
import org.sonatype.aether.RepositorySystem;
import org.sonatype.aether.RepositorySystemSession;
import org.sonatype.aether.artifact.Artifact;
import org.sonatype.aether.repository.LocalRepository;
import org.sonatype.aether.repository.RemoteRepository;
import org.sonatype.aether.resolution.ArtifactRequest;
import org.sonatype.aether.resolution.ArtifactResult;
import org.sonatype.aether.util.artifact.DefaultArtifact;

import java.io.File;
import java.io.IOException;

import static com.google.inject.internal.util.$Preconditions.checkNotNull;
import static net.lmxm.ute.resources.types.StatusChangeMessageResourceType.MAVEN_REPOSITORY_DOWNLOAD_FAILED;
import static net.lmxm.ute.resources.types.StatusChangeMessageResourceType.MAVEN_REPOSITORY_DOWNLOAD_UNABLE_TO_DELETE_LOCAL_REPOSITORY;

/**
 * Aether library wrapper used to resolve Maven artifacts and copy to them a destination directory.
 */
public final class AetherResolveUtils {

    /**
     * Maven repository identifier used by Aether.
     */
    private static final String REPOSITORY_ID = "ute";

    /**
     * Maven repostiroy type used by Aether.
     */
    private static final String REPOSITORY_TYPE = "default";

    /**
     * Job status change event bus.
     */
    private final JobStatusChangeEventBus jobStatusChangeEventBus;

    /**
     * Remote Maven repository handle.
     */
    private final RemoteRepository remoteRepository;

    /**
     * Remove Maven repository system handle.
     */
    private final RepositorySystem system;

    /**
     * Create a new utils instance that will work with the Maven repository at the provided URL.
     *
     * @param jobStatusChangeEventBus Job status change event bus
     * @param repositoryUrl URL of the Maven repository to work with
     * @throws Exception Error occurred during initialization of Aether
     */
    public AetherResolveUtils(final JobStatusChangeEventBus jobStatusChangeEventBus, String repositoryUrl) {
        super();

        this.jobStatusChangeEventBus = checkNotNull(jobStatusChangeEventBus, "Job status change event bus");
        remoteRepository = new RemoteRepository(REPOSITORY_ID, REPOSITORY_TYPE, repositoryUrl);

        try {
            system = new DefaultPlexusContainer().lookup(RepositorySystem.class);
        }
        catch (final ComponentLookupException e) {
            throw new TaskExecuterException(ExceptionResourceType.UNEXPECTED_ERROR, e);
        }
        catch (final PlexusContainerException e) {
            throw new TaskExecuterException(ExceptionResourceType.UNEXPECTED_ERROR, e);
        }
    }

    /**
     * Creates a temporary directory to be used as a local repository base. This is the file system directory that will
     * be used to temporarily hold downloaded artifacts. The directory created is uniquely named and should not be
     * used by other executions.
     *
     * @return Unique, temporary directory to be used as a local repository base
     */
    private File createLocalRepositoryBaseDirectory() {
        return FileUtils.createTempFile(REPOSITORY_ID, "aether-resolve", null);
    }

    /**
     * Create and configures a Maven repository system session. This method initializes the Aether API objects used to
     * hold session information.
     *
     * @param system                       Maven repository system to work with
     * @param localRepositoryBaseDirectory Location of the local repository where files will be downloaded
     * @return Fully configured Maven repository system session instance
     */
    private RepositorySystemSession newRepositorySystemSession(final RepositorySystem system, final File localRepositoryBaseDirectory) {
        final MavenRepositorySystemSession session = new MavenRepositorySystemSession();

        final LocalRepository localRepository = new LocalRepository(localRepositoryBaseDirectory);
        session.setLocalRepositoryManager(system.newLocalRepositoryManager(localRepository));
        session.setTransferListener(new AetherTransferListener(jobStatusChangeEventBus));
        session.setRepositoryListener(new AetherRepositoryListener(jobStatusChangeEventBus));

        return session;
    }

    /**
     * Resolves, downloads and moves targeted artifact to the provided destination directory. This is the main entry
     * point into this utils class and performs all the necessary work to download an artifact from Maven and put the
     * artifact in the expected location.
     *
     * @param artifactCoordinates  Maven coordinates (GAV) of the artifact to download (groupId:artifactId:version)
     * @param destinationDirectory Directory where the artifact will be copied when done. This file must exist and must
     *                             be a directory.
     * @param targetName           Optional target name for the downlaoded artifact.
     */
    public void resolveArtifact(final String artifactCoordinates, final File destinationDirectory, final String targetName) {
        final File localRepositoryBaseDirectory = createLocalRepositoryBaseDirectory();

        try {
            final ArtifactRequest artifactRequest = new ArtifactRequest();
            artifactRequest.setArtifact(new DefaultArtifact(artifactCoordinates));
            artifactRequest.addRepository(remoteRepository);

            final RepositorySystemSession session = newRepositorySystemSession(system, localRepositoryBaseDirectory);
            final ArtifactResult artifactResult = system.resolveArtifact(session, artifactRequest);
            final Artifact artifact = artifactResult.getArtifact();

            if (StringUtils.isEmpty(targetName)) {
                FileUtils.copyFileToDirectory(artifact.getFile(), destinationDirectory);
            }
            else {
                FileUtils.copyFile(artifact.getFile(), new File(destinationDirectory, targetName));
            }
        }
        catch (Exception e) {
            jobStatusChangeEventBus.error(MAVEN_REPOSITORY_DOWNLOAD_FAILED);
        }
        finally {
            try {
                FileUtils.deleteDirectory(localRepositoryBaseDirectory);
            }
            catch (IOException e) {
                jobStatusChangeEventBus.error(MAVEN_REPOSITORY_DOWNLOAD_UNABLE_TO_DELETE_LOCAL_REPOSITORY, e.getMessage());
                throw new TaskExecuterException(ExceptionResourceType.UNABLE_TO_DELETE_DIRECTORY,
                        localRepositoryBaseDirectory.getAbsoluteFile());
            }
        }
    }
}
