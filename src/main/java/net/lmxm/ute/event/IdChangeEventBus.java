package net.lmxm.ute.event;

import com.google.common.eventbus.EventBus;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Event bus responsible for handling ID change events.
 */
public final class IdChangeEventBus {
    /**
     * Event bus used to handle events.
     */
    private static EventBus eventBus = new EventBus();

    /**
     * Post a new ID change event.
     *
     * @param idChangeEvent ID change event to post
     */
    public static void post(final IdChangeEvent idChangeEvent) {
        eventBus.post(checkNotNull(idChangeEvent, "ID change event may not be null"));
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
    private IdChangeEventBus() {
        throw new AssertionError("Cannot be instantiated");
    }
}
