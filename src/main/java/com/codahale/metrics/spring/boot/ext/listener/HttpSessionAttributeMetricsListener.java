package com.codahale.metrics.spring.boot.ext.listener;

import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionBindingEvent;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.spring.boot.ext.MetricsFactory;

/**
 * Session属性绑定、移除、更新速率监控
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
public class HttpSessionAttributeMetricsListener implements HttpSessionAttributeListener {

	/**
     * 实例化一个registry，最核心的一个模块，相当于一个应用程序的metrics系统的容器，维护一个Map
     */
	protected MetricRegistry registry = MetricsFactory.getMetricRegistry("http-session-attribute");
	
	@Override
	/**
	 * <p>Attribute added.</p>
	 * @param event
	 */
	public void attributeAdded(HttpSessionBindingEvent event) {

		String prefix = MetricRegistry.name(this.getClass(), event.getSession().getServletContext().getContextPath(), "session", "attributeAdded" );
		registry.meter(prefix).mark();
		
	}

	@Override
	/**
	 * <p>Attribute removed.</p>
	 * @param event
	 */
	public void attributeRemoved(HttpSessionBindingEvent event) {
		
		String prefix = MetricRegistry.name(this.getClass(), event.getSession().getServletContext().getContextPath(), "session", "attributeRemoved" );
		registry.meter(prefix).mark();

	}

	@Override
	/**
	 * <p>Attribute replaced.</p>
	 * @param event
	 */
	public void attributeReplaced(HttpSessionBindingEvent event) {

		String prefix = MetricRegistry.name(this.getClass(), event.getSession().getServletContext().getContextPath(), "session", "attributeReplaced" );
		registry.meter(prefix).mark();

	}

}
