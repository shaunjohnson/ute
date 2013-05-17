/**
 * Copyright (C) 2011 Shaun Johnson, LMXM LLC
 *
 * This file is part of Universal Task Executer.
 *
 * Universal Task Executer is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 *
 * Universal Task Executer is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * Universal Task Executer. If not, see <http://www.gnu.org/licenses/>.
 */
package net.lmxm.ute.executers.tasks;

import net.lmxm.ute.beans.FileReference;
import net.lmxm.ute.beans.tasks.FileSystemDeleteTask;
import net.lmxm.ute.event.StatusChangeEventBus;
import net.lmxm.ute.utils.FileSystemTargetUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static net.lmxm.ute.resources.types.StatusChangeMessageResourceType.*;

/**
 * The Class FileSystemDeleteTaskExecuter.
 */
public final class FileSystemDeleteTaskExecuter extends AbstractTaskExecuter {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(FileSystemDeleteTaskExecuter.class);

    /**
     * The task.
     */
    private final FileSystemDeleteTask task;

    /**
     * Instantiates a new file system delete task executer.
     *
     * @param task               the task
     */
    public FileSystemDeleteTaskExecuter(final FileSystemDeleteTask task) {
        checkNotNull(task, "Task may not be null");

        this.task = task;
    }

    /**
     * Delete files.
     *
     * @param path        the path
     * @param files       the files
     * @param stopOnError the stop on error
     */
    protected void deleteFiles(final String path, final List<FileReference> files, final boolean stopOnError) {
        final String prefix = "execute() :";

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("{} entered", prefix);
            LOGGER.debug("{} path={}", prefix, path);
            LOGGER.debug("{} files={}", prefix, files);
            LOGGER.debug("{} stopOnError={}", prefix, stopOnError);
        }

        final String pathTrimmed = StringUtils.trimToNull(path);

        checkArgument(pathTrimmed != null, "Path may not be blank");

        final File pathFile = new File(pathTrimmed);

        if (!pathFile.exists()) {
            LOGGER.debug("{} path does not exist, returning", prefix);
            StatusChangeEventBus.info(FILE_DELETE_PATH_DOES_NOT_EXIST_ERROR, pathFile.getAbsolutePath());
            return;
        }

        if (pathFile.isFile()) {
            LOGGER.debug("{} deleting file {}", prefix, pathFile.getName());

            StatusChangeEventBus.info(FILE_DELETE_FILE_DELETE_STARTED, pathFile.getAbsolutePath());

            if (forceDelete(pathFile, stopOnError)) {
                StatusChangeEventBus.info(FILE_DELETE_FILE_DELETE_FINISHED, pathFile.getAbsolutePath());
            }
        }
        else if (pathFile.isDirectory()) {
            LOGGER.debug("{} path is a directory", prefix);

            if (CollectionUtils.isEmpty(files)) {
                LOGGER.debug("{} deleting directory {}", prefix, pathFile.getName());

                StatusChangeEventBus.info(FILE_DELETE_DIRECTORY_DELETE_STARTED, pathFile.getAbsolutePath());

                if (forceDelete(pathFile, stopOnError)) {
                    StatusChangeEventBus.info(FILE_DELETE_DIRECTORY_DELETE_FINISHED, pathFile.getAbsolutePath());
                }
            }
            else {
                LOGGER.debug("{} deleting {} files in a directory", prefix, prefix);

                for (final FileReference file : files) {
                    final String fileName = file.getName();

                    LOGGER.debug("{} deleting file {}", prefix, fileName);

                    StatusChangeEventBus.info(FILE_DELETE_FILE_DELETE_STARTED, fileName);

                    if (forceDelete(new File(pathFile, fileName), stopOnError)) {
                        StatusChangeEventBus.info(FILE_DELETE_FILE_DELETE_FINISHED, fileName);
                    }
                }
            }
        }
        else {
            LOGGER.error("{} path is not a file or directory, returning", prefix);
        }

        LOGGER.debug("{} leaving", prefix);
    }

    /*
     * (non-Javadoc)
     * @see net.lmxm.ute.executers.ExecuterIF#execute()
     */
    @Override
    public void execute() {
        final String prefix = "execute() :";

        LOGGER.debug("{} entered", prefix);

        final String path = FileSystemTargetUtils.getFullPath(task.getTarget());
        final List<FileReference> files = task.getFiles();
        final boolean stopOnError = task.getStopOnError();

        deleteFiles(path, files, stopOnError);

        LOGGER.debug("{} returning", prefix);
    }

    /**
     * Force delete.
     *
     * @param pathFile    the path file
     * @param stopOnError the stop on error
     * @return true, if successful
     */
    protected boolean forceDelete(final File pathFile, final boolean stopOnError) {
        final String prefix = "forceDelete() :";

        boolean successful = false;

        try {
            FileUtils.forceDelete(pathFile);

            successful = true;
        }
        catch (final FileNotFoundException e) {
            if (stopOnError) {
                LOGGER.error(prefix + " file not found " + pathFile.getName(), e);
                StatusChangeEventBus.error(FILE_DELETE_FILE_DOES_NOT_EXIST_ERROR, pathFile.getAbsolutePath());
                throw new RuntimeException();
            }
            else {
                LOGGER.debug("{} ignoring error deleting file", prefix);

                StatusChangeEventBus.info(FILE_DELETE_FILE_DOES_NOT_EXIST_ERROR, pathFile.getAbsolutePath());
            }
        }
        catch (final IOException e) {
            if (stopOnError) {
                LOGGER.error(prefix + " error deleting file " + pathFile.getName(), e);
                StatusChangeEventBus.error(FILE_DELETE_ERROR, pathFile.getAbsolutePath());
                throw new RuntimeException();
            }
            else {
                LOGGER.debug("{} ignoring error deleting file", prefix);
                StatusChangeEventBus.info(FILE_DELETE_ERROR, pathFile.getAbsolutePath());
            }
        }

        return successful;
    }
}
