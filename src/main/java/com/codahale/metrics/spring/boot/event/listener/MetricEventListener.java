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
package com.codahale.metrics.spring.boot.event.listener;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

import com.codahale.metrics.spring.boot.ext.MetricsFactory;

/**
 * <p>Auto-configuration for MetricEventListener.</p>
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
public abstract class MetricEventListener<E extends ApplicationEvent> implements ApplicationListener<E>, InitializingBean {

	@Autowired
	protected MetricsFactory metricsFactory;
	
	protected long initialDelay = 0; 
	
	protected long period = 1;
	
	protected TimeUnit unit = TimeUnit.SECONDS;
	
	@Override
	/**
	 * <p>After properties set.</p>
	 */
	public void afterPropertiesSet() throws Exception {
	}
	
	/** @return return the metrics factory. */
	public MetricsFactory getMetricsFactory() {
		return metricsFactory;
	}

	/** @param metricsFactory set the metrics factory. */
	public void setMetricsFactory(MetricsFactory metricsFactory) {
		this.metricsFactory = metricsFactory;
	}

	/** @return return the initial delay. */
	public long getInitialDelay() {
		return initialDelay;
	}

	/** @param initialDelay set the initial delay. */
	public void setInitialDelay(long initialDelay) {
		this.initialDelay = initialDelay;
	}

	/** @return return the period. */
	public long getPeriod() {
		return period;
	}

	/** @param period set the period. */
	public void setPeriod(long period) {
		this.period = period;
	}

	/** @return return the unit. */
	public TimeUnit getUnit() {
		return unit;
	}

	/** @param unit set the unit. */
	public void setUnit(TimeUnit unit) {
		this.unit = unit;
	}
	
}
