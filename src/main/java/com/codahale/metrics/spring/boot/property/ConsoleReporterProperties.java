/*
 * Copyright (c) 2018, hiwepy (https://github.com/hiwepy).
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.codahale.metrics.spring.boot.property;

import java.io.PrintStream;

import com.codahale.metrics.spring.boot.MetricsReportProperties;

/**
 * <p>Auto-configuration for ConsoleReporterProperties.</p>
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
public class ConsoleReporterProperties extends ReporterProperties {

	public static final String PREFIX = MetricsReportProperties.PREFIX + ".console";
	
	/**
	 * <p>Auto-configuration for ConsoleStream.</p>
	 * @author <a href="https://github.com/loong10k">Loong Wan</a>
	 * @since 1.0.0
	 */
	public enum ConsoleStream {
		STDOUT(System.out), STDERR(System.err);

		private final PrintStream printStream;

		ConsoleStream(PrintStream printStream) {
			this.printStream = printStream;
		}

		/**
		 * <p>Get.</p>
		 * @return the result
		 */
		public PrintStream get() {
			return printStream;
		}
	}
	
	private String timeZone = "UTC";

	private ConsoleStream output = ConsoleStream.STDOUT;

	/** @return return the time zone. */
	public String getTimeZone() {
		return timeZone;
	}

	/** @param timeZone set the time zone. */
	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	/** @return return the output. */
	public ConsoleStream getOutput() {
		return output;
	}

	/** @param stream set the output. */
	public void setOutput(ConsoleStream stream) {
		this.output = stream;
	}

}
