package com.codahale.metrics.spring.boot.ext.listener;

import jakarta.servlet.ServletContextAttributeEvent;
import jakarta.servlet.ServletContextAttributeListener;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.spring.boot.ext.MetricsFactory;

/**
 * ServletContext上下文属性绑定、移除、更新速率监控
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
public class HttpServletContextAttributeMetricsListener implements ServletContextAttributeListener {

	/**
     * 实例化一个registry，最核心的一个模块，相当于一个应用程序的metrics系统的容器，维护一个Map
     */
	protected MetricRegistry registry = MetricsFactory.getMetricRegistry("http-servletcontext-attribute");
	
	@Override
	/**
	 * <p>Attribute added.</p>
	 * @param event
	 */
	public void attributeAdded(ServletContextAttributeEvent event) {
		
		String prefix = MetricRegistry.name(this.getClass(), event.getServletContext().getContextPath(), "ServletContext", "attributeAdded" );
		registry.meter(prefix).mark();

	}

	@Override
	/**
	 * <p>Attribute removed.</p>
	 * @param event
	 */
	public void attributeRemoved(ServletContextAttributeEvent event) {

		String prefix = MetricRegistry.name(this.getClass(), event.getServletContext().getContextPath(), "ServletContext", "attributeRemoved" );
		registry.meter(prefix).mark();

	}

	@Override
	/**
	 * <p>Attribute replaced.</p>
	 * @param event
	 */
	public void attributeReplaced(ServletContextAttributeEvent event) {
		
		String prefix = MetricRegistry.name(this.getClass(), event.getServletContext().getContextPath(), "ServletContext", "attributeReplaced" );
		registry.meter(prefix).mark();

	}
	
}
