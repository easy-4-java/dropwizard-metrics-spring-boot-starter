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
package com.codahale.metrics.spring.boot.ext.servlet;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.health.HealthCheckRegistry;
import io.dropwizard.metrics.servlets.MetricsServlet;
import io.dropwizard.metrics.servlets.HealthCheckServlet;

/**
 * <p>Auto-configuration for MetricsServletsContextListener.</p>
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
public class MetricsServletsContextListener implements ServletContextListener {

	@Autowired
	private MetricRegistry metricRegistry;

	@Autowired
	private HealthCheckRegistry healthCheckRegistry;

	private final MetricsServletContextListener metricsServletContextListener = new MetricsServletContextListener();
	private final HealthCheckServletContextListener healthCheckServletContextListener = new HealthCheckServletContextListener();

	@Override
	/**
	 * <p>Context initialized.</p>
	 * @param event
	 */
	public void contextInitialized(ServletContextEvent event) {

		WebApplicationContextUtils.getRequiredWebApplicationContext(event.getServletContext())
				.getAutowireCapableBeanFactory().autowireBean(this);
		metricsServletContextListener.contextInitialized(event);
		healthCheckServletContextListener.contextInitialized(event);
	}

	@Override
	/**
	 * <p>Context destroyed.</p>
	 * @param event
	 */
	public void contextDestroyed(ServletContextEvent event) {
	}

	/**
	 * <p>Auto-configuration for MetricsServletContextListener.</p>
	 * @author <a href="https://github.com/loong10k">Loong Wan</a>
	 * @since 1.0.0
	 */
	class MetricsServletContextListener extends MetricsServlet.ContextListener {

		@Override
		/** @return return the metric registry. */
		protected MetricRegistry getMetricRegistry() {
			return metricRegistry;
		}

		@Override
		/** @return return the allowed origin. */
		protected String getAllowedOrigin() {
			return "*";
		}

	}

	/**
	 * <p>Auto-configuration for HealthCheckServletContextListener.</p>
	 * @author <a href="https://github.com/loong10k">Loong Wan</a>
	 * @since 1.0.0
	 */
	class HealthCheckServletContextListener extends HealthCheckServlet.ContextListener {

		@Override
		/** @return return the health check registry. */
		protected HealthCheckRegistry getHealthCheckRegistry() {
			return healthCheckRegistry;
		}

	}

}
