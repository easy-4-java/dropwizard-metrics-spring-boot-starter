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
 * <p>Auto-configuration for ZabbixReporterProperties.</p>
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
public class ZabbixReporterProperties extends ReporterProperties {

	public static final String PREFIX = MetricsReportProperties.PREFIX + ".zabbix";
	private String name = "zabbix-reporter";
	private String hostName;
	private String suffix = "";

	private String host;
	private int port;
	private int connectTimeout = 3 * 1000;
	private int socketTimeout = 3 * 1000;

	/** @return return the name. */
	public String getName() {
		return name;
	}

	/** @param name set the name. */
	public void setName(String name) {
		this.name = name;
	}

	/** @return return the host name. */
	public String getHostName() {
		return hostName;
	}

	/** @param hostName set the host name. */
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	/** @return return the suffix. */
	public String getSuffix() {
		return suffix;
	}

	/** @param suffix set the suffix. */
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	/** @return return the host. */
	public String getHost() {
		return host;
	}

	/** @param host set the host. */
	public void setHost(String host) {
		this.host = host;
	}

	/** @return return the port. */
	public int getPort() {
		return port;
	}

	/** @param port set the port. */
	public void setPort(int port) {
		this.port = port;
	}

	/** @return return the connect timeout. */
	public int getConnectTimeout() {
		return connectTimeout;
	}

	/** @param connectTimeout set the connect timeout. */
	public void setConnectTimeout(int connectTimeout) {
		this.connectTimeout = connectTimeout;
	}

	/** @return return the socket timeout. */
	public int getSocketTimeout() {
		return socketTimeout;
	}

	/** @param socketTimeout set the socket timeout. */
	public void setSocketTimeout(int socketTimeout) {
		this.socketTimeout = socketTimeout;
	}

}
