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

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import net.lmxm.ute.beans.FileReference;
import net.lmxm.ute.beans.tasks.HttpDownloadTask;
import net.lmxm.ute.event.StatusChangeHelper;
import net.lmxm.ute.resources.StatusChangeMessage;
import net.lmxm.ute.utils.FileSystemTargetUtils;
import net.lmxm.ute.utils.FileSystemUtils;
import net.lmxm.ute.utils.HttpUtils;
import net.lmxm.ute.utils.PathUtils;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;

/**
 * The Class HttpDownloadTaskExecuter.
 */
public final class HttpDownloadTaskExecuter extends AbstractTaskExecuter {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(HttpDownloadTaskExecuter.class);

	/** The task. */
	private final HttpDownloadTask task;

	/**
	 * Instantiates a new http download task executer.
	 * 
	 * @param task the task
	 * @param statusChangeHelper the status change helper
	 */
	public HttpDownloadTaskExecuter(final HttpDownloadTask task, final StatusChangeHelper statusChangeHelper) {
		super(statusChangeHelper);

		Preconditions.checkNotNull(task, "Task may not be null");

		this.task = task;
	}

	/**
	 * Builds the query params.
	 * 
	 * @param queryParams the query params
	 * @return the http params
	 */
	private HttpParams buildQueryParams(final Map<String, String> queryParams) {
		final HttpParams params = new BasicHttpParams();

		// params.setParameter(arg0, arg1)

		return params;
	}

	/**
	 * Download file.
	 * 
	 * @param sourceUrl the source url
	 * @param queryParams the query params
	 * @param destinationFilePath the destination file path
	 */
	private void downloadFile(final String sourceUrl, final Map<String, String> queryParams,
			final String destinationFilePath) {
		final String prefix = "execute() :";

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("{} entered", prefix);
			LOGGER.debug("{} sourceUrl={}", prefix, sourceUrl);
			LOGGER.debug("{} destinationFilePath={}", prefix, destinationFilePath);
		}

		try {
			final DefaultHttpClient httpClient = new DefaultHttpClient();
			final HttpGet httpGet = new HttpGet(sourceUrl);
			httpGet.setParams(buildQueryParams(queryParams));
			final HttpResponse response = httpClient.execute(httpGet);
			final int statusCode = response.getStatusLine().getStatusCode();

			if (statusCode != HttpStatus.SC_OK) {
				LOGGER.debug("HTTP status code {} returned", statusCode);

				getStatusChangeHelper().error(this, StatusChangeMessage.HTTP_DOWNLOAD_STATUS_ERROR, sourceUrl,
						statusCode);

				throw new RuntimeException(); // TODO Use appropriate exception
			}

			final HttpEntity entity = response.getEntity();

			if (entity == null) {
				getStatusChangeHelper().error(this, StatusChangeMessage.HTTP_DOWNLOAD_NO_RESPONSE_ERROR);

				throw new RuntimeException(); // TODO Use appropriate exception
			}
			else {
				final InputStream inputStream = entity.getContent();
				final OutputStream out = new FileOutputStream(destinationFilePath);
				final byte buffer[] = new byte[1024];
				int length;

				while ((length = inputStream.read(buffer)) > 0) {
					out.write(buffer, 0, length);
				}

				out.close();

				getStatusChangeHelper().info(this, StatusChangeMessage.HTTP_DOWNLOAD_FINISHED, sourceUrl,
						destinationFilePath);
			}
		}
		catch (final ClientProtocolException e) {
			LOGGER.debug("ClientProtocolException caught", e);
			getStatusChangeHelper()
					.error(this, StatusChangeMessage.HTTP_DOWNLOAD_ERROR, sourceUrl, destinationFilePath);
			throw new RuntimeException(); // TODO Use appropriate exception
		}
		catch (final IOException e) {
			LOGGER.debug("IOException caught", e);
			getStatusChangeHelper()
					.error(this, StatusChangeMessage.HTTP_DOWNLOAD_ERROR, sourceUrl, destinationFilePath);
			throw new RuntimeException(); // TODO Use appropriate exception
		}
		catch (final Exception e) {
			LOGGER.debug("Exception caught", e);
			getStatusChangeHelper()
					.error(this, StatusChangeMessage.HTTP_DOWNLOAD_ERROR, sourceUrl, destinationFilePath);
			throw new RuntimeException(); // TODO Use appropriate exception
		}

		LOGGER.debug("{} leaving", prefix);
	}

	/**
	 * Download files.
	 * 
	 * @param url the url
	 * @param queryParams the query params
	 * @param destinationPath the destination path
	 * @param files the files
	 */
	protected void downloadFiles(final String url, final Map<String, String> queryParams, final String destinationPath,
			final List<FileReference> files) {
		final String prefix = "downloadFiles() :";

		LOGGER.debug("{} entered", prefix);

		Preconditions.checkArgument(StringUtils.isNotBlank(url), "URL may not be blank or null");
		Preconditions.checkArgument(StringUtils.isNotBlank(destinationPath),
				"Destination path may not be blank or null");

		FileSystemUtils.getInstance().createDirectory(destinationPath);

		if (files == null || files.size() == 0) {
			LOGGER.error("{} downloadFiles", prefix);

			getStatusChangeHelper().fatal(this, StatusChangeMessage.HTTP_DOWNLOAD_FILE_LIST_EMPTY);

			throw new IllegalArgumentException("List of files is empty"); // TODO Use appropriate exception
		}

		// Download each file
		for (final FileReference fileReference : files) {
			LOGGER.debug("{} downloading file {}", prefix, fileReference.getName());

			final String sourceUrl = PathUtils.buildFullPath(url, fileReference.getName());
			final String name = fileReference.getName();
			final String targetName = fileReference.getTargetName();
			final String targetFileName = StringUtils.isBlank(targetName) ? name : targetName;
			final String destinationFilePath = PathUtils.buildFullPath(destinationPath, targetFileName);

			downloadFile(sourceUrl, queryParams, destinationFilePath);
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

		final String url = HttpUtils.getFullUrl(task.getSource());
		final Map<String, String> queryParams = task.getSource().getQueryParams();
		final String destinationPath = FileSystemTargetUtils.getFullPath(task.getTarget());
		final List<FileReference> files = task.getFiles();

		downloadFiles(url, queryParams, destinationPath, files);

		LOGGER.debug("{} returning", prefix);
	}
}
