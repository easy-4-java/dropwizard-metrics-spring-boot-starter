package com.codahale.metrics.spring.boot.event;

/**
 * 业务计量事件对象
 */
@SuppressWarnings("serial")
/**
 * <p>Auto-configuration for GaugeEvent.</p>
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
public class GaugeEvent extends MetricEvent<MetricEventPoint> {

	public GaugeEvent(Object source, MetricEventPoint bind) {
		super(source, bind);
	}
	
	public GaugeEvent(Object source, String name, Long value) {
		super(source, new MetricEventPoint(name, null, value));
	}
	
	public GaugeEvent(Object source, String name, String message, Long value) {
		super(source, new MetricEventPoint(name, message, value));
	}
	
}
