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
package net.lmxm.ute.console;

import java.io.File;
import java.util.List;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.beust.jcommander.Parameters;
import com.beust.jcommander.converters.FileConverter;
import com.beust.jcommander.internal.Lists;

/**
 * The Class ConsoleArguments.
 */
@Parameters(resourceBundle = "resources")
public final class ConsoleArguments {

	/**
	 * The Class InputFileValidator.
	 */
	public static class InputFileValidator implements IParameterValidator {

		/*
		 * (non-Javadoc)
		 * @see com.beust.jcommander.IParameterValidator#validate(java.lang.String, java.lang.String)
		 */
		@Override
		public void validate(final String name, final String value) throws ParameterException {
			if (!new File(value).exists()) {
				throw new ParameterException("Input file does not exist");
			}
		}
	}

	/** The input file. */
	@Parameter(names = { "-i", "--input-file" }, descriptionKey = "parameter.inputFileDescription", required = true, converter = FileConverter.class, validateWith = InputFileValidator.class)
	private File inputFile;

	/** The job ids. */
	@Parameter(names = { "-j", "--job-ids" }, descriptionKey = "parameter.jobIdsDescription")
	private final List<String> jobIds = Lists.newArrayList();

	/**
	 * Gets the input file.
	 * 
	 * @return the input file
	 */
	public File getInputFile() {
		return inputFile;
	}

	/**
	 * Gets the job ids.
	 * 
	 * @return the job ids
	 */
	public List<String> getJobIds() {
		return jobIds;
	}
}
