package net.lmxm.ute.event;

import net.lmxm.ute.beans.jobs.Job;
import net.lmxm.ute.resources.types.StatusChangeMessageResourceType;

import static com.google.inject.internal.util.$Preconditions.checkNotNull;

/**
 * Wrapper used to report status change events for a specific job.
 */
public final class JobStatusChangeEventBus {
    /**
     * The associated job.
     */
    private final Job job;

    /**
     * Constructs a new job statuc change listener for the specified job.
     *
     * @param job The associated job object
     */
    public JobStatusChangeEventBus(final Job job) {
        this.job = checkNotNull(job, "Job may not be null");
    }

    public void error(final StatusChangeMessageResourceType resourceType, final Object... arguments) {
        StatusChangeEventBus.error(resourceType, job, arguments);
    }

    public void important(final StatusChangeMessageResourceType resourceType, final Object... arguments) {
        StatusChangeEventBus.important(resourceType, job, arguments);
    }

    public void info(final StatusChangeMessageResourceType resourceType, final Object... arguments) {
        StatusChangeEventBus.info(resourceType, job, arguments);
    }
}
