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
 * <p>Auto-configuration for GraphiteReporterProperties.</p>
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
public class GraphiteReporterProperties extends ReporterProperties {

	public static final String PREFIX = MetricsReportProperties.PREFIX + ".graphite";
	
	/**
	 * <p>Auto-configuration for Transport.</p>
	 * @author <a href="https://github.com/loong10k">Loong Wan</a>
	 * @since 1.0.0
	 */
	public enum Transport {

		RABBITMQ("rabbitmq"), TCP("tcp"), UDP("udp"), PICKLE("pickle");

		private final String transport;

		Transport(String transport) {
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
		public boolean equals(Transport transport){
			return this.compareTo(transport) == 0;
		}
		
		/**
		 * <p>Equals.</p>
		 * @param transport
		 * @return the result
		 */
		public boolean equals(String transport){
			return this.compareTo(Transport.valueOfIgnoreCase(transport)) == 0;
		}
		
		/**
		 * <p>Value of ignore case.</p>
		 * @param key
		 * @return the result
		 */
		public static Transport valueOfIgnoreCase(String key) {
			for (Transport transport : Transport.values()) {
				if(transport.get().equalsIgnoreCase(key)) {
					return transport;
				}
			}
	    	throw new NoSuchElementException("Cannot found transport with key '" + key + "'.");
	    }
		
	}

	// Required
	private String host = "";
	private String port = "";

	// Optional
	private Transport transport = Transport.TCP;
	private String charset = "UTF-8";

	// Pickle Optional
	private int batchSize = 100;

	// RabbitMQ Required
	private String exchange = "";

	/** @return return the host. */
	public String getHost() {
		return host;
	}

	/** @param host set the host. */
	public void setHost(String host) {
		this.host = host;
	}

	/** @return return the port. */
	public String getPort() {
		return port;
	}

	/** @param port set the port. */
	public void setPort(String port) {
		this.port = port;
	}

	/** @return return the transport. */
	public Transport getTransport() {
		return transport;
	}

	/** @param transport set the transport. */
	public void setTransport(Transport transport) {
		this.transport = transport;
	}

	/** @return return the charset. */
	public String getCharset() {
		return charset;
	}

	/** @param charset set the charset. */
	public void setCharset(String charset) {
		this.charset = charset;
	}

	/** @return return the batch size. */
	public int getBatchSize() {
		return batchSize;
	}

	/** @param batchSize set the batch size. */
	public void setBatchSize(int batchSize) {
		this.batchSize = batchSize;
	}

	/** @return return the exchange. */
	public String getExchange() {
		return exchange;
	}

	/** @param exchange set the exchange. */
	public void setExchange(String exchange) {
		this.exchange = exchange;
	}

}
