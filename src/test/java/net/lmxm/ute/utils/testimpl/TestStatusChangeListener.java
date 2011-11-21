package net.lmxm.ute.utils.testimpl;

import net.lmxm.ute.event.StatusChangeEvent;
import net.lmxm.ute.event.StatusChangeListener;

import org.junit.Ignore;

/**
 * The listener interface for receiving myStatusChange events. The class that is interested in processing a
 * myStatusChange event implements this interface, and the object created with that class is registered with a component
 * using the component's <code>addMyStatusChangeListener<code> method. When
 * the myStatusChange event occurs, that object's appropriate
 * method is invoked.
 * 
 * @see StatusChangeEvent
 */
@Ignore
public class TestStatusChangeListener implements StatusChangeListener {

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.listeners.StatusChangeListener#statusChange(net.lmxm.ute.listeners.StatusChangeEvent)
	 */
	@Override
	public void statusChange(final StatusChangeEvent changeEvent) {
		System.out.println(changeEvent);
	}
}
