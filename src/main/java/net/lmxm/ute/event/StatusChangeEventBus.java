package net.lmxm.ute.event;

import com.google.common.eventbus.EventBus;
import net.lmxm.ute.beans.jobs.Job;
import net.lmxm.ute.resources.ResourcesUtils;
import net.lmxm.ute.resources.types.StatusChangeMessageResourceType;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Event bus responsible for handling status change events.
 */
public final class StatusChangeEventBus {
    /**
     * Event bus used to handle events.
     */
    private static EventBus eventBus = new EventBus();

    /**
     * Post a new status change event.
     *
     * @param statusChangeEvent Status change event to post
     */
    public static void post(final StatusChangeEvent statusChangeEvent) {
        eventBus.post(checkNotNull(statusChangeEvent, "Status change event may not be null"));
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
     * Unregister one or more event subscribers.
     *
     * @param objects Subscribers
     */
    public static void unregister(final Object... objects) {
        checkNotNull(objects, "Objects may not be null");

        for (final Object object : objects) {
            eventBus.unregister(object);
        }
    }

    /**
     * Prevent instantiation.
     */
    private StatusChangeEventBus() {
        throw new AssertionError("Cannot be instantiated");
    }


    /**
     * Error.
     *
     * @param statusChangeMessage the status change message
     * @param job the associated job
     * @param arguments the arguments
     */
    public static void error(final StatusChangeMessageResourceType statusChangeMessage, final Job job,
                            final Object... arguments) {
        fireEvent(StatusChangeEventType.ERROR, job, statusChangeMessage, arguments);
    }

    /**
     * Fatal.
     *
     * @param statusChangeMessage the status change message
     * @param job the associated job
     * @param arguments the arguments
     */
    public static void fatal(final StatusChangeMessageResourceType statusChangeMessage, final Job job,
                            final Object... arguments) {
        fireEvent(StatusChangeEventType.FATAL, job, statusChangeMessage, arguments);
    }

    /**
     * Fire event.
     *
     * @param eventType the event type
     * @param job the associated job
     * @param statusChangeMessage the status change message
     * @param arguments the arguments
     */
    protected static void fireEvent(final StatusChangeEventType eventType, final Job job,
                             final StatusChangeMessageResourceType statusChangeMessage, final Object... arguments) {
        checkNotNull(eventType, "Status change event type is null");
        checkNotNull(job, "Job is null");
        checkNotNull(statusChangeMessage, "Status change message resource type is null");

        final String message = formatMessage(statusChangeMessage, arguments);

        StatusChangeEventBus.post(new StatusChangeEvent(eventType, job, message));
    }

    /**
     * Format message.
     *
     * @param statusChangeMessage the status change message
     * @param arguments the arguments
     * @return the string
     */
    private static String formatMessage(final StatusChangeMessageResourceType statusChangeMessage, final Object[] arguments) {
        return String.format(ResourcesUtils.getResourceMessage(statusChangeMessage), arguments);
    }

    /**
     * Heading.
     *
     * @param statusChangeMessage the status change message
     * @param job the associated job
     * @param arguments the arguments
     */
    public static void heading(final StatusChangeMessageResourceType statusChangeMessage, final Job job,
                              final Object... arguments) {
        fireEvent( StatusChangeEventType.HEADING, job, statusChangeMessage, arguments);
    }

    /**
     * Important.
     *
     * @param statusChangeMessage the status change message
     * @param job the associated job
     * @param arguments the arguments
     */
    public static void important(final StatusChangeMessageResourceType statusChangeMessage, final Job job,
                                final Object... arguments) {
        fireEvent( StatusChangeEventType.IMPORTANT, job, statusChangeMessage, arguments);
    }

    /**
     * Info.
     *
     * @param statusChangeMessage the status change message
     * @param job the associated job
     * @param arguments the arguments
     */
    public static void info(final StatusChangeMessageResourceType statusChangeMessage, final Job job,
                           final Object... arguments) {
        fireEvent( StatusChangeEventType.INFO, job, statusChangeMessage, arguments);
    }
}
