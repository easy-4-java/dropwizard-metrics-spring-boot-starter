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

import java.util.Iterator;
import java.util.Map.Entry;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.MetricSet;
import com.codahale.metrics.health.HealthCheckRegistry;
import com.codahale.metrics.spring.boot.ext.MetricsFactory;

/**
 * Spring Boot auto-configuration for the core Dropwizard Metrics objects.
 * <p>
 * Activates only when the {@link MetricRegistry} class is on the classpath. It
 * creates the {@link MetricRegistry} (registering any metric sets declared in
 * {@link MetricsProperties#getMetrics()}), the {@link HealthCheckRegistry} and
 * the {@link MetricsFactory} used by the annotation-driven aspects.</p>
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 * @since 1.0.0
 */
@Configuration
@ConditionalOnClass(MetricRegistry.class)
@EnableConfigurationProperties(MetricsProperties.class)
public class MetricsAutoConfiguration implements DisposableBean {

	/**
	 * Creates the {@link MetricRegistry} and registers every metric set declared
	 * in {@link MetricsProperties#getMetrics()} (name &rarr; {@link MetricSet}
	 * class name). Entries whose class cannot be loaded are skipped.
	 *
	 * @param properties the bound metrics properties
	 * @return the populated metric registry
	 */
	@Bean
	@ConditionalOnMissingBean
	public MetricRegistry metricRegistry(MetricsProperties properties) {
		MetricRegistry metricRegistry = new MetricRegistry();
		
		Iterator<Entry<String, String>>  ite = properties.getMetrics().entrySet().iterator();
		while (ite.hasNext()) {
			
			Entry<String, String> entry = ite.next();
			final String name = entry.getKey();
			try {
				
				AbstractBeanDefinition metricDef = BeanDefinitionReaderUtils.createBeanDefinition(MetricSet.class.getName(), entry.getValue(), this.getClass().getClassLoader());
				
				Object metric = BeanUtils.instantiateClass(metricDef.getBeanClass());
				
				if (StringUtils.hasText(name) && (metric instanceof MetricSet)) {
					metricRegistry.register(name, (MetricSet) metric);
				}
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return metricRegistry;
	}
	
	/**
	 * @param properties the bound metrics properties
	 * @return a new {@link HealthCheckRegistry}.
	 */
	@Bean
	@ConditionalOnMissingBean
	public HealthCheckRegistry healthCheckRegistry(MetricsProperties properties) {
		return new HealthCheckRegistry();
	}

	/**
	 * Creates the {@link MetricsFactory} used by the annotation-driven metrics
	 * aspects, binding it to the given registry when one is available.
	 *
	 * @param metricRegistry the metric registry to bind (may be {@code null})
	 * @return the configured {@link MetricsFactory}
	 */
	@Bean
	@ConditionalOnMissingBean
	public MetricsFactory metricsFactory(MetricRegistry metricRegistry) {
		MetricsFactory metricsFactory = new MetricsFactory();
		if(metricRegistry != null) {
			metricsFactory.setRegistry(metricRegistry);
		}
		return metricsFactory;
	}


	/**
	 * Lifecycle hook invoked by Spring on container shutdown; currently a no-op.
	 *
	 * @throws Exception never thrown by the current implementation
	 */
	@Override
	public void destroy() throws Exception {


	}

}