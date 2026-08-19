package com.codahale.metrics.spring.boot.event;

import java.util.Map;

/**
 * 业务运行状态事件对象
 */
@SuppressWarnings("serial")
/**
 * <p>Auto-configuration for MeterEvent.</p>
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
public class MeterEvent extends MetricEvent<MetricEventPoint> {

	public MeterEvent(Object source, MetricEventPoint bind) {
		super(source, bind);
	}
	
	public MeterEvent(Object source, String name) {
		super(source, new MetricEventPoint(name, null));
	}
	
	public MeterEvent(Object source, String name, String message) {
		super(source, new MetricEventPoint(name, message));
	}
	
	public MeterEvent(Object source, String name, String message, Map<String, Object> data) {
		super(source, new MetricEventPoint(name, message, data));
	}
	
}
