package com.codahale.metrics.spring.boot.ext.listener;

import jakarta.servlet.ServletRequestAttributeEvent;
import jakarta.servlet.ServletRequestAttributeListener;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.spring.boot.ext.MetricsFactory;


/**
 * <p>Auto-configuration for HttpServletRequestAttributeMetricsListener.</p>
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
public class HttpServletRequestAttributeMetricsListener implements ServletRequestAttributeListener {

	/**
     * 实例化一个registry，最核心的一个模块，相当于一个应用程序的metrics系统的容器，维护一个Map
     */
	protected MetricRegistry registry = MetricsFactory.getMetricRegistry("http-request-attribute");
	
	@Override
	/**
	 * <p>Attribute added.</p>
	 * @param event
	 */
	public void attributeAdded(ServletRequestAttributeEvent event) {
		String prefix = MetricRegistry.name(this.getClass(), event.getServletContext().getContextPath(), "request-attribute", "attributeAdded" );
		registry.meter(prefix).mark();
	}

	@Override
	/**
	 * <p>Attribute removed.</p>
	 * @param event
	 */
	public void attributeRemoved(ServletRequestAttributeEvent event) {
		String prefix = MetricRegistry.name(this.getClass(), event.getServletContext().getContextPath(), "request-attribute", "attributeAdded" );
		registry.meter(prefix).mark();

	}

	@Override
	/**
	 * <p>Attribute replaced.</p>
	 * @param event
	 */
	public void attributeReplaced(ServletRequestAttributeEvent event) {
		String prefix = MetricRegistry.name(this.getClass(), event.getServletContext().getContextPath(), "request-attribute", "attributeAdded" );
		registry.meter(prefix).mark();
	}
	
}
