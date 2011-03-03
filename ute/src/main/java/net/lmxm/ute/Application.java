/**
 * Copyright (C) 2011 Shaun Johnson, LMXM LLC
 * 
 * This file is part of Universal Task Executor.
 * 
 * Universal Task Executor is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * Universal Task Executor is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * Universal Task Executor. If not, see <http://www.gnu.org/licenses/>.
 */
package net.lmxm.ute;

import net.lmxm.ute.beans.Configuration;
import net.lmxm.ute.beans.jobs.Job;
import net.lmxm.ute.console.ConsoleJobStatusListener;
import net.lmxm.ute.console.ConsoleStatusChangeListener;
import net.lmxm.ute.executors.jobs.JobExecutorFactory;
import net.lmxm.ute.gui.MainFrame;
import net.lmxm.ute.mapper.ConfigurationMapper;
import net.lmxm.ute.utils.ConfigurationUtils;
import net.lmxm.ute.utils.FileSystemUtils;

import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.UIManager;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jgoodies.looks.Options;
import com.martiansoftware.jsap.FlaggedOption;
import com.martiansoftware.jsap.JSAP;
import com.martiansoftware.jsap.JSAPException;
import com.martiansoftware.jsap.JSAPResult;

/**
 * The Class Application.
 */
public final class Application {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(Application.class);

	/** The Constant stderr. */
	private static final PrintStream stderr = System.err;

	/** The Constant stdout. */
	private static final PrintStream stdout = System.out;

	/**
	 * Execute command line.
	 * 
	 * @param args the args
	 */
	private static void executeCommandLine(final String[] args) {
		final String prefix = "executeCommandLine(String[]) :";

		LOGGER.debug("{} entered", prefix);

		final JSAP jsap = new JSAP();

		try {
			setupJsap(jsap);
		} catch (final JSAPException e1) {
			LOGGER.error("{} Error occurred processing arguments", prefix);
		}

		final JSAPResult result = jsap.parse(args);

		final String[] jobIds = result.getStringArray("job-id");
		final String inputFile = result.getString("input-file");

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("{} jobIds={}", prefix, jobIds);
			LOGGER.debug("{} inputFile={}", prefix, inputFile);
		}

		if (jobIds == null || jobIds.length == 0
				|| StringUtils.isBlank(inputFile)) {
			LOGGER.error("{} insufficient arguments", prefix);

			printUsage(jsap, null);

			return;
		}

		if (!FileSystemUtils.getInstance().fileExists(inputFile)) {
			LOGGER.error("{} Input file does not exist", prefix);

			printUsage(jsap, "Input file does not exist");

			return;
		}

		final Configuration configuration = new ConfigurationMapper()
				.parse(new File(inputFile));
		final List<Job> jobs = new ArrayList<Job>();

		// Load jobs, validating that the job ids are valid
		for (final String jobId : jobIds) {
			final Job job = ConfigurationUtils.getJob(configuration, jobId);

			if (job == null) {
				LOGGER.error("{} job with id \"{}\" does not exist", prefix,
						jobId);

				printUsage(jsap, "Job with id \"" + jobId + "\" does not exist");

				return;
			}

			jobs.add(job);
		}

		// Execute jobs
		for (final Job job : jobs) {
			LOGGER.debug("{} executing job {}", prefix, job.getId());

			final Job jobInterpolated = ConfigurationUtils
					.interpolateJobValues(job, configuration);

			JobExecutorFactory.create(jobInterpolated,
					new ConsoleJobStatusListener(),
					new ConsoleStatusChangeListener()).execute();
		}

		LOGGER.debug("{} leaving", prefix);
	}

	/**
	 * Execute gui.
	 */
	private static void executeGui() {
		final String prefix = "executeGui() :";

		LOGGER.debug("{} entered", prefix);

		try {
			UIManager.setLookAndFeel(Options
					.getCrossPlatformLookAndFeelClassName());
		} catch (final Exception e) {
			LOGGER.error("Error setting native LAF", e);
		}

		final JFrame main = new MainFrame();

		main.setVisible(true);

		LOGGER.debug("{} exiting", prefix);
	}

	/**
	 * The main method.
	 * 
	 * @param args the arguments
	 * @throws InterruptedException the interrupted exception
	 */
	public static void main(final String[] args) throws InterruptedException {
		final String prefix = "main(String[]) :";

		LOGGER.info("{} Application started", prefix);

		if (args.length == 0) {
			executeGui();
		} else {
			executeCommandLine(args);
		}

		LOGGER.info("{} Application ended", prefix);
	}

	/**
	 * Prints the usage.
	 * 
	 * @param jsap the jsap
	 * @param errorMessage the error message
	 */
	private static void printUsage(final JSAP jsap, final String errorMessage) {
		stderr.println("Universal Task Executor Usage\n");

		if (StringUtils.isNotBlank(errorMessage)) {
			stdout.println(errorMessage + "\n");
		}

		stderr.println(jsap.getHelp());
	}

	/**
	 * Sets the up jsap.
	 * 
	 * @param jsap the new up jsap
	 * @throws JSAPException the jSAP exception
	 */
	private static void setupJsap(final JSAP jsap) throws JSAPException {
		final String prefix = "setupJsap() :";

		LOGGER.info("{} entered", prefix);

		// Input file option
		final FlaggedOption inputFileOption = new FlaggedOption("input-file");
		inputFileOption.setStringParser(JSAP.STRING_PARSER);
		inputFileOption.setShortFlag('i');
		inputFileOption.setLongFlag("input-file");
		inputFileOption.setHelp("Path of configuration file");
		jsap.registerParameter(inputFileOption);

		// Job ID option
		final FlaggedOption jobIdOption = new FlaggedOption("job-id");
		jobIdOption.setStringParser(JSAP.STRING_PARSER);
		jobIdOption.setShortFlag('j');
		jobIdOption.setLongFlag("job-id");
		jobIdOption.setHelp("Job ID");
		jobIdOption.setList(true);
		jobIdOption.setListSeparator(',');
		jsap.registerParameter(jobIdOption);

		LOGGER.info("{} leaving", prefix);
	}
}
