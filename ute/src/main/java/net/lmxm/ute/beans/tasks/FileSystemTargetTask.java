package net.lmxm.ute.beans.tasks;

import net.lmxm.ute.beans.targets.FileSystemTarget;

public interface FileSystemTargetTask {

	/**
	 * Gets the target.
	 * 
	 * @return the target
	 */
	public abstract FileSystemTarget getTarget();

	/**
	 * Sets the target.
	 * 
	 * @param target the new target
	 */
	public abstract void setTarget(final FileSystemTarget target);

}
