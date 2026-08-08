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
package com.codahale.metrics.spring.boot;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Configuration properties for the Dropwizard Metrics starter.
 * <p>
 * Bound to the {@code dropwizard.metrics.*} namespace. In addition to the AOP
 * proxy flags, the {@code metrics} map lets applications register custom
 * {@link com.codahale.metrics.MetricSet MetricSet} instances by name and class
 * name; the auto-configuration instantiates each class and registers it with
 * the {@link com.codahale.metrics.MetricRegistry}.</p>
 *
 * <h3>Configuration</h3>
 * <ul>
 *   <li>{@code dropwizard.metrics.expose-proxy} — expose the AOP proxy via
 *       {@code AopContext} (default {@code false})</li>
 *   <li>{@code dropwizard.metrics.proxy-target-class} — use CGLIB class-based
 *       proxies for the metrics aspects (default {@code false})</li>
 *   <li>{@code dropwizard.metrics.metrics} — ordered map of metric name &rarr;
 *       {@link com.codahale.metrics.MetricSet} implementation class name, e.g.
 *       {@code jvm.gc=com.codahale.metrics.jvm.GarbageCollectorMetricSet}</li>
 * </ul>
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 * @since 1.0.0
 */
@ConfigurationProperties(prefix = MetricsProperties.PREFIX)
public class MetricsProperties {

	/** Configuration prefix for Dropwizard Metrics properties. */
	public static final String PREFIX = "dropwizard.metrics";

	/** Whether to expose the AOP proxy via {@code AopContext} (default {@code false}). */
	private boolean exposeProxy = false;
	/** Whether to use CGLIB class-based proxies for the metrics aspects (default {@code false}). */
	private boolean proxyTargetClass = false;

	/**
	 * Map of metric name &rarr; {@link com.codahale.metrics.MetricSet}
	 * implementation class name. Each entry is instantiated and registered with
	 * the metric registry. Built-in JVM examples:
	 * <ul>
	 *   <li>{@code jvm.gc} &rarr; {@code com.codahale.metrics.jvm.GarbageCollectorMetricSet}</li>
	 *   <li>{@code jvm.memory} &rarr; {@code com.codahale.metrics.jvm.MemoryUsageGaugeSet}</li>
	 *   <li>{@code jvm.thread-states} &rarr; {@code com.codahale.metrics.jvm.ThreadStatesGaugeSet}</li>
	 *   <li>{@code jvm.fd.usage} &rarr; {@code com.codahale.metrics.jvm.FileDescriptorRatioGauge}</li>
	 * </ul>
	 */
	private Map<String /* name */, String /* class */> metrics = new LinkedHashMap<String, String>();

	/** @return {@code true} if the AOP proxy is exposed via {@code AopContext}. */
	public boolean isExposeProxy() {
		return exposeProxy;
	}

	/**
	 * Toggles exposure of the AOP proxy via {@code AopContext}.
	 *
	 * @param exposeProxy {@code true} to expose the proxy
	 */
	public void setExposeProxy(boolean exposeProxy) {
		this.exposeProxy = exposeProxy;
	}

	/** @return {@code true} if CGLIB class-based proxies are used. */
	public boolean isProxyTargetClass() {
		return proxyTargetClass;
	}

	/**
	 * Toggles CGLIB class-based proxying for the metrics aspects.
	 *
	 * @param proxyTargetClass {@code true} to use class-based proxies
	 */
	public void setProxyTargetClass(boolean proxyTargetClass) {
		this.proxyTargetClass = proxyTargetClass;
	}

	/** @return the ordered map of metric name &rarr; metric-set class name. */
	public Map<String, String> getMetrics() {
		return metrics;
	}

	/**
	 * Sets the ordered map of metric name &rarr; metric-set class name.
	 *
	 * @param metrics the metric registration map
	 */
	public void setMetrics(Map<String, String> metrics) {
		this.metrics = metrics;
	}

}
