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

import com.codahale.metrics.spring.boot.MetricsReportProperties;

/**
 * <p>Auto-configuration for Slf4jReporterProperties.</p>
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
public class Slf4jReporterProperties extends ReporterProperties  {

	public static final String PREFIX = MetricsReportProperties.PREFIX + ".slf4j";
	
	// Optional
	private String marker;
	private String logger;
	private String level = "DEBUG";

	/** @return return the marker. */
	public String getMarker() {
		return marker;
	}

	/** @param marker set the marker. */
	public void setMarker(String marker) {
		this.marker = marker;
	}

	/** @return return the logger. */
	public String getLogger() {
		return logger;
	}

	/** @param logger set the logger. */
	public void setLogger(String logger) {
		this.logger = logger;
	}

	/** @return return the level. */
	public String getLevel() {
		return level;
	}

	/** @param level set the level. */
	public void setLevel(String level) {
		this.level = level;
	}

}
