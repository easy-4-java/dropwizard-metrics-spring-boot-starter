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

import java.util.NoSuchElementException;

import com.codahale.metrics.spring.boot.MetricsReportProperties;


/**
 * <p>Auto-configuration for DatadogReporterProperties.</p>
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
public class DatadogReporterProperties extends ReporterProperties {

	public static final String PREFIX = MetricsReportProperties.PREFIX + ".datadog";
	
	/**
	 * <p>Auto-configuration for TransportEnum.</p>
	 * @author <a href="https://github.com/loong10k">Loong Wan</a>
	 * @since 1.0.0
	 */
	public enum TransportEnum {

		HTTP("http"), UDP("udp"), STATSD("statsd");

		private final String transport;

		TransportEnum(String transport) {
			this.transport = transport;
		}

		/**
		 * <p>Get.</p>
		 * @return the result
		 */
		public String get() {
			return transport;
		}
		
		/**
		 * <p>Equals.</p>
		 * @param transport
		 * @return the result
		 */
		public boolean equals(TransportEnum transport){
			return this.compareTo(transport) == 0;
		}
		
		/**
		 * <p>Equals.</p>
		 * @param transport
		 * @return the result
		 */
		public boolean equals(String transport){
			return this.compareTo(TransportEnum.valueOfIgnoreCase(transport)) == 0;
		}
		
		/**
		 * <p>Value of ignore case.</p>
		 * @param key
		 * @return the result
		 */
		public static TransportEnum valueOfIgnoreCase(String key) {
			for (TransportEnum transport : TransportEnum.values()) {
				if(transport.get().equalsIgnoreCase(key)) {
					return transport;
				}
			}
	    	throw new NoSuchElementException("Cannot found transport with key '" + key + "'.");
	    }
		
	}
	
	// Required

	/**
	 * http,udp,statsd
	 */
	private TransportEnum transport = TransportEnum.HTTP;

	// HTTP Transport
	private String apiKey;
	private String connectTimeout;
	private String socketTimeout;

	// UDP Transport
	private String statsdHost;
	private String statsdPort;
	private String statsdPrefix;

	// Optional
	private String host;
	private boolean useEc2Host = false;
	private String expansions;
	private String tags;

	/** @return return the transport. */
	public TransportEnum getTransport() {
		return transport;
	}

	/** @param transport set the transport. */
	public void setTransport(TransportEnum transport) {
		this.transport = transport;
	}

	/** @return return the api key. */
	public String getApiKey() {
		return apiKey;
	}

	/** @param apiKey set the api key. */
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	/** @return return the connect timeout. */
	public String getConnectTimeout() {
		return connectTimeout;
	}

	/** @param connectTimeout set the connect timeout. */
	public void setConnectTimeout(String connectTimeout) {
		this.connectTimeout = connectTimeout;
	}

	/** @return return the socket timeout. */
	public String getSocketTimeout() {
		return socketTimeout;
	}

	/** @param socketTimeout set the socket timeout. */
	public void setSocketTimeout(String socketTimeout) {
		this.socketTimeout = socketTimeout;
	}

	/** @return return the statsd host. */
	public String getStatsdHost() {
		return statsdHost;
	}

	/** @param statsdHost set the statsd host. */
	public void setStatsdHost(String statsdHost) {
		this.statsdHost = statsdHost;
	}

	/** @return return the statsd port. */
	public String getStatsdPort() {
		return statsdPort;
	}

	/** @param statsdPort set the statsd port. */
	public void setStatsdPort(String statsdPort) {
		this.statsdPort = statsdPort;
	}

	/** @return return the statsd prefix. */
	public String getStatsdPrefix() {
		return statsdPrefix;
	}

	/** @param statsdPrefix set the statsd prefix. */
	public void setStatsdPrefix(String statsdPrefix) {
		this.statsdPrefix = statsdPrefix;
	}

	/** @return return the host. */
	public String getHost() {
		return host;
	}

	/** @param host set the host. */
	public void setHost(String host) {
		this.host = host;
	}

	/** @return return whether use ec2 host is enabled. */
	public boolean isUseEc2Host() {
		return useEc2Host;
	}

	/** @param useEc2Host set the use ec2 host. */
	public void setUseEc2Host(boolean useEc2Host) {
		this.useEc2Host = useEc2Host;
	}

	/** @return return the expansions. */
	public String getExpansions() {
		return expansions;
	}

	/** @param expansions set the expansions. */
	public void setExpansions(String expansions) {
		this.expansions = expansions;
	}

	/** @return return the tags. */
	public String getTags() {
		return tags;
	}

	/** @param tags set the tags. */
	public void setTags(String tags) {
		this.tags = tags;
	}
	
}
