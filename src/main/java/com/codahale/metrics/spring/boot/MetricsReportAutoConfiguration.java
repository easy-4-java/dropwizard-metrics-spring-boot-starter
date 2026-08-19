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

import javax.management.MBeanServer;
import javax.sql.DataSource;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.codahale.metrics.Clock;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.ScheduledReporter;
import com.codahale.metrics.spring.boot.factory.ConsoleReporterFactoryBean;
import com.codahale.metrics.spring.boot.factory.DatabaseReporterFactoryBean;
import com.codahale.metrics.spring.boot.factory.JmxReporterFactoryBean;
import com.codahale.metrics.spring.boot.factory.Slf4jReporterFactoryBean;
import com.codahale.metrics.spring.boot.property.ConsoleReporterProperties;
import com.codahale.metrics.spring.boot.property.DatabaseReporterProperties;
import com.codahale.metrics.spring.boot.property.JmxReporterProperties;
import com.codahale.metrics.spring.boot.property.Slf4jReporterProperties;
import com.codahale.metrics.spring.boot.utils.SystemClock;

/**
 * Spring Boot auto-configuration for Dropwizard Metrics reporters.
 * <p>
 * Activates only when both {@link MetricRegistry} and
 * {@link ScheduledReporter} are on the classpath, after
 * {@link MetricsAutoConfiguration}. It exposes the shared {@link Clock} and
 * lazily creates the enabled reporter factory beans (console, SLF4J, JMX,
 * database) according to the bound {@link MetricsReportProperties}.</p>
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
@Configuration
@ConditionalOnClass({ MetricRegistry.class, ScheduledReporter.class })
@EnableConfigurationProperties(MetricsReportProperties.class)
@AutoConfigureAfter(MetricsAutoConfiguration.class)
/**
 * <p>Auto-configuration for MetricsReportAutoConfiguration.</p>
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
public class MetricsReportAutoConfiguration implements DisposableBean {

	/**
	 * @return the shared {@link Clock} ({@link SystemClock}) used by all reporters.
	 */
	@Bean
	@ConditionalOnMissingBean
	/**
	 * <p>Clock.</p>
	 * @return the result
	 */
	public Clock clock() {
		return SystemClock.instance();
	}

	/**
	 * Creates the console reporter factory bean when
	 * {@code dropwizard.metrics.console.enabled=true}.
	 *
	 * @param properties     bound reporter properties
	 * @param clock          the shared clock
	 * @param metricRegistry the metric registry to report
	 * @return a configured {@link ConsoleReporterFactoryBean}
	 */
	@Bean
	@ConditionalOnProperty(prefix = ConsoleReporterProperties.PREFIX, value = "enabled", havingValue = "true")
	public ConsoleReporterFactoryBean consoleReporterFactoryBean(MetricsReportProperties properties, Clock clock,
			MetricRegistry metricRegistry) {
		ConsoleReporterFactoryBean factoryBean = new ConsoleReporterFactoryBean(properties.getConsole());
		factoryBean.setClock(clock);
		factoryBean.setMetricRegistry(metricRegistry);
		return factoryBean;
	}

	/**
	 * Creates the SLF4J reporter factory bean when
	 * {@code dropwizard.metrics.slf4j.enabled=true}.
	 *
	 * @param properties     bound reporter properties
	 * @param clock          the shared clock
	 * @param metricRegistry the metric registry to report
	 * @return a configured {@link Slf4jReporterFactoryBean}
	 */
	@Bean
	@ConditionalOnProperty(prefix = Slf4jReporterProperties.PREFIX, value = "enabled", havingValue = "true")
	public Slf4jReporterFactoryBean slf4jReporterFactoryBean(MetricsReportProperties properties, Clock clock,
			MetricRegistry metricRegistry) {

		Slf4jReporterFactoryBean factoryBean = new Slf4jReporterFactoryBean(properties.getSlf4j());
		factoryBean.setClock(clock);
		factoryBean.setMetricRegistry(metricRegistry);

		return factoryBean;

	}

	/**
	 * Creates the JMX reporter factory bean when
	 * {@code dropwizard.metrics.jmx.enabled=true}.
	 *
	 * @param properties     bound reporter properties
	 * @param clock          the shared clock
	 * @param metricRegistry the metric registry to report
	 * @param mBeanServer    optional {@link MBeanServer} to publish to
	 * @return a configured {@link JmxReporterFactoryBean}
	 */
	@Bean
	@ConditionalOnProperty(prefix = JmxReporterProperties.PREFIX, value = "enabled", havingValue = "true")
	public JmxReporterFactoryBean jmxReporterFactoryBean(MetricsReportProperties properties, Clock clock,
			MetricRegistry metricRegistry, @Autowired(required = false) MBeanServer mBeanServer) {

		JmxReporterFactoryBean factoryBean = new JmxReporterFactoryBean(properties.getJmx());
		factoryBean.setClock(clock);
		factoryBean.setMetricRegistry(metricRegistry);
		factoryBean.setmBeanServer(mBeanServer);

		return factoryBean;
	}

	/**
	 * Creates the database reporter factory bean when
	 * {@code dropwizard.metrics.database.enabled=true} and a
	 * {@link DataSource} bean is present.
	 *
	 * @param properties     bound reporter properties
	 * @param dataSource     the JDBC data source to write metrics to
	 * @param clock          the shared clock
	 * @param metricRegistry the metric registry to report
	 * @return a configured {@link DatabaseReporterFactoryBean}
	 */
	@Bean
	@ConditionalOnProperty(prefix = DatabaseReporterProperties.PREFIX , value = "enabled", havingValue = "true")
	@ConditionalOnBean(DataSource.class)
	public DatabaseReporterFactoryBean databaseReporterFactoryBean(MetricsReportProperties properties,
			DataSource dataSource, Clock clock, MetricRegistry metricRegistry) {

		DatabaseReporterFactoryBean factoryBean = new DatabaseReporterFactoryBean(properties.getDatabase(), dataSource);
		factoryBean.setClock(clock);
		factoryBean.setMetricRegistry(metricRegistry);

		return factoryBean;
	}

	/**
	 * Lifecycle hook invoked by Spring on container shutdown; currently a no-op.
	 *
	 * @throws Exception never thrown by the current implementation
	 */
	@Override
	/**
	 * <p>Destroy.</p>
	 */
	public void destroy() throws Exception {

	}

}