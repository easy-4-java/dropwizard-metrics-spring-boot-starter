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
 * <p>Auto-configuration for KafkaReporterProperties.</p>
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
public class KafkaReporterProperties extends ReporterProperties {

	public static final String PREFIX = MetricsReportProperties.PREFIX + ".kafka";
	private String name = "kafka-reporter";
	private boolean showSamples;
	private String hostName;
	private String ip;
	private String topic;

	/** @return return the name. */
	public String getName() {
		return name;
	}

	/** @param name set the name. */
	public void setName(String name) {
		this.name = name;
	}

	/** @return return whether show samples is enabled. */
	public boolean isShowSamples() {
		return showSamples;
	}

	/** @param showSamples set the show samples. */
	public void setShowSamples(boolean showSamples) {
		this.showSamples = showSamples;
	}

	/** @return return the host name. */
	public String getHostName() {
		return hostName;
	}

	/** @param hostName set the host name. */
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	/** @return return the ip. */
	public String getIp() {
		return ip;
	}

	/** @param ip set the ip. */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/** @return return the topic. */
	public String getTopic() {
		return topic;
	}

	/** @param topic set the topic. */
	public void setTopic(String topic) {
		this.topic = topic;
	}

}
