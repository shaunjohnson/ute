package net.lmxm.ute.executers.jobs;

import net.lmxm.ute.beans.jobs.Job;
import net.lmxm.ute.beans.tasks.Task;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Job status event containing information about the event that occurred.
 */
public final class JobStatusEvent {
    /**
     * All possible event types.
     */
    public enum JobStatusEventType {
        JobAborted, JobCompleted, JobStarted, JobStopped, TaskCompleted, TaskSkipped, TaskStarted
    }

    /**
     * Type of event.
     */
    private final JobStatusEventType eventType;

    /**
     * Job for which the event occurred.
     */
    private final Job job;

    /**
     * Task for which the event occurred.
     */
    private final Task task;

    /**
     * Constructs a job status event for the specified job.
     *
     * @param eventType Type of event
     * @param job       Job for which this event occurred
     */
    public JobStatusEvent(final JobStatusEventType eventType, final Job job) {
        this.eventType = checkNotNull(eventType, "Event type may not be null");
        this.task = null;
        this.job = checkNotNull(job, "Job may not be null");
    }

    /**
     * Constructs a job status event for the specified task.
     *
     * @param eventType Type of event
     * @param task      Task for which this event occurred
     */
    public JobStatusEvent(final JobStatusEventType eventType, final Task task) {
        this.eventType = checkNotNull(eventType, "Event type may not be null");
        this.task = checkNotNull(task, "Task may not be null");
        this.job = task.getJob();
    }

    /**
     * Gets the event type.
     *
     * @return Event type
     */
    public JobStatusEventType getEventType() {
        return eventType;
    }

    /**
     * Gets the job for which this event occurred.
     *
     * @return Job object
     */
    public Job getJob() {
        return job;
    }

    /**
     * Gets the task for which this event occurred.
     *
     * @return Task object
     */
    public Task getTask() {
        return task;
    }
}
