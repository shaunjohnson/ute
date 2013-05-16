package net.lmxm.ute.executers.jobs;

import com.google.common.eventbus.EventBus;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Event bus responsible for handling job status events.
 */
public final class JobStatusEventBus {
    /**
     * Event bus used to handle events.
     */
    private static EventBus eventBus = new EventBus();

    /**
     * Post a new job status event.
     *
     * @param jobStatusEvent Job status event to post
     */
    public static void post(final JobStatusEvent jobStatusEvent) {
        eventBus.post(checkNotNull(jobStatusEvent, "Job status event may not be null"));
    }

    /**
     * Register one or more event subscribers.
     *
     * @param objects Subscribers
     */
    public static void register(final Object... objects) {
        checkNotNull(objects, "Objects may not be null");

        for (final Object object : objects) {
            eventBus.register(object);
        }
    }

    /**
     * Prevent instantiation.
     */
    private JobStatusEventBus() {
        throw new AssertionError("Cannot be instantiated");
    }
}
