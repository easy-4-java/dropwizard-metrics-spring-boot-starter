/**
 * Copyright (C) 2012 Ryan W Tenney (ryan@10e.us)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.codahale.metrics.spring.boot.ext;

import org.springframework.aop.framework.ProxyConfig;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.health.HealthCheckRegistry;
import com.codahale.metrics.spring.boot.aop.CountedMethodInterceptor;
import com.codahale.metrics.spring.boot.aop.ExceptionMeteredMethodInterceptor;
import com.codahale.metrics.spring.boot.aop.MeteredMethodInterceptor;
import com.codahale.metrics.spring.boot.aop.TimedMethodInterceptor;
import com.codahale.metrics.spring.boot.factory.config.AdvisingBeanPostProcessor;
import com.codahale.metrics.spring.boot.factory.config.CachedGaugeAnnotationBeanPostProcessor;
import com.codahale.metrics.spring.boot.factory.config.GaugeFieldAnnotationBeanPostProcessor;
import com.codahale.metrics.spring.boot.factory.config.GaugeMethodAnnotationBeanPostProcessor;
import com.codahale.metrics.spring.boot.factory.config.HealthCheckBeanPostProcessor;
import com.codahale.metrics.spring.boot.factory.config.MetricAnnotationBeanPostProcessor;

/**
 * <p>Auto-configuration for MetricsBeanPostProcessorFactory.</p>
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
public class MetricsBeanPostProcessorFactory {

	private MetricsBeanPostProcessorFactory() {}

	/**
	 * <p>Exception metered.</p>
	 * @param metricRegistry
	 * @param proxyConfig
	 * @return the result
	 */
	public static AdvisingBeanPostProcessor exceptionMetered(final MetricRegistry metricRegistry, final ProxyConfig proxyConfig) {
		return new AdvisingBeanPostProcessor(ExceptionMeteredMethodInterceptor.POINTCUT, ExceptionMeteredMethodInterceptor.adviceFactory(metricRegistry),
				proxyConfig);
	}

	/**
	 * <p>Metered.</p>
	 * @param metricRegistry
	 * @param proxyConfig
	 * @return the result
	 */
	public static AdvisingBeanPostProcessor metered(final MetricRegistry metricRegistry, final ProxyConfig proxyConfig) {
		return new AdvisingBeanPostProcessor(MeteredMethodInterceptor.POINTCUT, MeteredMethodInterceptor.adviceFactory(metricRegistry), proxyConfig);
	}

	/**
	 * <p>Timed.</p>
	 * @param metricRegistry
	 * @param proxyConfig
	 * @return the result
	 */
	public static AdvisingBeanPostProcessor timed(final MetricRegistry metricRegistry, final ProxyConfig proxyConfig) {
		return new AdvisingBeanPostProcessor(TimedMethodInterceptor.POINTCUT, TimedMethodInterceptor.adviceFactory(metricRegistry), proxyConfig);
	}

	/**
	 * <p>Counted.</p>
	 * @param metricRegistry
	 * @param proxyConfig
	 * @return the result
	 */
	public static AdvisingBeanPostProcessor counted(final MetricRegistry metricRegistry, final ProxyConfig proxyConfig) {
		return new AdvisingBeanPostProcessor(CountedMethodInterceptor.POINTCUT, CountedMethodInterceptor.adviceFactory(metricRegistry), proxyConfig);
	}

	/**
	 * <p>Gauge field.</p>
	 * @param metricRegistry
	 * @return the result
	 */
	public static GaugeFieldAnnotationBeanPostProcessor gaugeField(final MetricRegistry metricRegistry) {
		return new GaugeFieldAnnotationBeanPostProcessor(metricRegistry);
	}

	/**
	 * <p>Gauge method.</p>
	 * @param metricRegistry
	 * @return the result
	 */
	public static GaugeMethodAnnotationBeanPostProcessor gaugeMethod(final MetricRegistry metricRegistry) {
		return new GaugeMethodAnnotationBeanPostProcessor(metricRegistry);
	}

	/**
	 * <p>Cached gauge.</p>
	 * @param metricRegistry
	 * @return the result
	 */
	public static CachedGaugeAnnotationBeanPostProcessor cachedGauge(final MetricRegistry metricRegistry) {
		return new CachedGaugeAnnotationBeanPostProcessor(metricRegistry);
	}

	/**
	 * <p>Metric.</p>
	 * @param metricRegistry
	 * @return the result
	 */
	public static MetricAnnotationBeanPostProcessor metric(final MetricRegistry metricRegistry) {
		return new MetricAnnotationBeanPostProcessor(metricRegistry);
	}

	/**
	 * <p>Health check.</p>
	 * @param healthRegistry
	 * @return the result
	 */
	public static HealthCheckBeanPostProcessor healthCheck(final HealthCheckRegistry healthRegistry) {
		return new HealthCheckBeanPostProcessor(healthRegistry);
	}
	
}
