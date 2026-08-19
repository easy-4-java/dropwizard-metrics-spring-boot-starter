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
package com.codahale.metrics.spring.boot.factory.support;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.codahale.metrics.spring.boot.EnableEventMetrics;
import com.codahale.metrics.spring.boot.event.listener.CountedEventListener;
import com.codahale.metrics.spring.boot.event.listener.GaugeEventListener;
import com.codahale.metrics.spring.boot.event.listener.HistogramEventListener;
import com.codahale.metrics.spring.boot.event.listener.MeterEventListener;

@Configuration
@ConditionalOnClass(EnableEventMetrics.class)
/**
 * <p>Auto-configuration for MetricsEventListenerRegistrar.</p>
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
public class MetricsEventListenerRegistrar{

	@Bean
	/**
	 * <p>Counted event listener.</p>
	 * @return the result
	 */
	public CountedEventListener countedEventListener() {
		return new CountedEventListener();
	}
	
	@Bean
	/**
	 * <p>Gauge event listener.</p>
	 * @return the result
	 */
	public GaugeEventListener gaugeEventListener() {
		return new GaugeEventListener();
	}
	
	@Bean
	/**
	 * <p>Histogram event listener.</p>
	 * @return the result
	 */
	public HistogramEventListener histogramEventListener() {
		return new HistogramEventListener();
	}
	
	@Bean
	/**
	 * <p>Meter event listener.</p>
	 * @return the result
	 */
	public MeterEventListener meterEventListener() {
		return new MeterEventListener();
	}
	
}