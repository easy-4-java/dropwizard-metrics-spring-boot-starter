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

import info.ganglia.gmetric4j.gmetric.GMetric.UDPAddressingMode;

/**
 * <p>Auto-configuration for GangliaReporterProperties.</p>
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
public class GangliaReporterProperties extends ReporterProperties {

	public static final String PREFIX = MetricsReportProperties.PREFIX + ".ganglia";
	
	// Required
	private String group = "group";
	private String port = "port";
	private UDPAddressingMode udpMode = UDPAddressingMode.MULTICAST;
	private String ttl = "ttl";

	// Optional
	private String protocol = "";
	private String uuid = "";
	private String spoof = "";
	private int dmax = 0;
	private int tmax = 60;
     
	/** @return return the group. */
	public String getGroup() {
		return group;
	}
	/** @param group set the group. */
	public void setGroup(String group) {
		this.group = group;
	}
	/** @return return the port. */
	public String getPort() {
		return port;
	}
	/** @param port set the port. */
	public void setPort(String port) {
		this.port = port;
	}
	/** @return return the udp mode. */
	public UDPAddressingMode getUdpMode() {
		return udpMode;
	}
	/** @param udpMode set the udp mode. */
	public void setUdpMode(UDPAddressingMode udpMode) {
		this.udpMode = udpMode;
	}
	/** @return return the ttl. */
	public String getTtl() {
		return ttl;
	}
	/** @param ttl set the ttl. */
	public void setTtl(String ttl) {
		this.ttl = ttl;
	}
	/** @return return the protocol. */
	public String getProtocol() {
		return protocol;
	}
	/** @param protocol set the protocol. */
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}
	/** @return return the uuid. */
	public String getUuid() {
		return uuid;
	}
	/** @param uuid set the uuid. */
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	/** @return return the spoof. */
	public String getSpoof() {
		return spoof;
	}
	/** @param spoof set the spoof. */
	public void setSpoof(String spoof) {
		this.spoof = spoof;
	}
	/** @return return the dmax. */
	public int getDmax() {
		return dmax;
	}
	/** @param dmax set the dmax. */
	public void setDmax(int dmax) {
		this.dmax = dmax;
	}
	/** @return return the tmax. */
	public int getTmax() {
		return tmax;
	}
	/** @param tmax set the tmax. */
	public void setTmax(int tmax) {
		this.tmax = tmax;
	}
	
	
	
}
