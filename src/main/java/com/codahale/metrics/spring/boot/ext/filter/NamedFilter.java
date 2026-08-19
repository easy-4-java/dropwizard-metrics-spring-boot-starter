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
package com.codahale.metrics.spring.boot.ext.filter;


import com.codahale.metrics.Metric;
import com.codahale.metrics.MetricFilter;

/**
 * <p>Auto-configuration for NamedFilter.</p>
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
public class NamedFilter implements MetricFilter {

	protected String metricKey;
	
	public NamedFilter(String metric) {
		this.metricKey = metric;
	}
	
	@Override
	/**
	 * <p>Matches.</p>
	 * @param name
	 * @param metric
	 * @return the result
	 */
	public boolean matches(String name, Metric metric) {
		return name.equals(metricKey);
	}
	
	/** @return return the metric. */
	public String getMetric() {
		return metricKey;
	}

	/** @param metric set the metric. */
	public void setMetric(String metric) {
		this.metricKey = metric;
	}

	@Override
	/**
	 * <p>To string.</p>
	 * @return the result
	 */
	public String toString() {
		return "[NamedFilter metric=" + metricKey + "]";
	}
	
}