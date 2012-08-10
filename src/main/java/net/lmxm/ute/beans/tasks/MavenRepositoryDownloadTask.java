package net.lmxm.ute.beans.tasks;

import net.lmxm.ute.beans.jobs.Job;
import net.lmxm.ute.beans.sources.MavenRepositorySource;
import net.lmxm.ute.beans.targets.FileSystemTarget;
import net.lmxm.ute.utils.DomainBeanUtils;
import org.apache.commons.lang3.StringUtils;

public final class MavenRepositoryDownloadTask extends AbstractTask implements FileSystemTargetTask, MavenRepositorySourceTask {

    private static final long serialVersionUID = 2537168744808468186L;

    /**
     * The Maven repository to be used to download the artifact.
     */
    private MavenRepositorySource source;

    /**
     * File system location where the downloaded artifact should be placed.
     */
    private FileSystemTarget target;

    /**
     * Maven Coordinates (GAV) of the artifact to download.
     */
    private String artifactCoordinates;

    public MavenRepositoryDownloadTask(final Job job) {
        super(job);
    }

    public String getArtifactCoordinates() {
        return artifactCoordinates;
    }

    public void setArtifactCoordinates(String artifactCoordinates) {
        this.artifactCoordinates = artifactCoordinates;
    }

    @Override
    public FileSystemTarget getTarget() {
        return target;
    }

    @Override
    public void setTarget(FileSystemTarget target) {
        this.target = target;
    }

    @Override
    public MavenRepositorySource getSource() {
        return source;
    }

    @Override
    public void setSource(MavenRepositorySource source) {
        this.source = source;
    }

    /**
     * @see net.lmxm.ute.beans.IdentifiableDomainBean#isEmpty()
     */
    @Override
    public boolean isEmpty() {
        return super.isEmpty() && StringUtils.isBlank(artifactCoordinates) && DomainBeanUtils.isEmpty(source) && DomainBeanUtils.isEmpty(target);
    }

    /**
     * @see net.lmxm.ute.beans.DomainBean#removeEmptyObjects()
     */
    @Override
    public void removeEmptyObjects() {
        super.removeEmptyObjects();
        DomainBeanUtils.removeEmptyObjects(source, target);
    }
}
