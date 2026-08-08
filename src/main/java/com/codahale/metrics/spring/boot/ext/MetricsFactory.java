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
package com.codahale.metrics.spring.boot.ext;


import java.util.concurrent.ConcurrentHashMap;

import jakarta.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codahale.metrics.Counter;
import com.codahale.metrics.Gauge;
import com.codahale.metrics.Histogram;
import com.codahale.metrics.Meter;
import com.codahale.metrics.Metric;
import com.codahale.metrics.MetricFilter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.MetricRegistry.MetricSupplier;
import com.codahale.metrics.MetricSet;
import com.codahale.metrics.SharedMetricRegistries;
import com.codahale.metrics.Timer;
import com.codahale.metrics.health.HealthCheckRegistry;

/**
 * Factory facade for creating, registering and resolving Dropwizard
 * {@link Metric} instances (timers, meters, counters, histograms, gauges).
 * <p>
 * Maintains a per-instance cache so repeated lookups for the same metric name
 * return the same instance, and exposes static helpers for obtaining named
 * {@link SharedMetricRegistries shared registries} partitioned by metric type.</p>
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 * @since 1.0.0
 */
@SuppressWarnings({"unchecked","rawtypes"})
public class MetricsFactory {

	/** Servlet-context attribute name under which the metric registry is published. */
	public static final String SERVLET_CONTEXT_METRIC_REGISTRY = ServletContext.class.getCanonicalName() +  ".registry";
	/** Default, shared health-check registry. */
	protected static final HealthCheckRegistry HEALTH_CHECK_REGISTRY = new HealthCheckRegistry();
	/** Default, shared metric registry. */
	protected static final MetricRegistry DEFAULT_REGISTRY = new MetricRegistry();

	/** The active metric registry (defaults to {@link #DEFAULT_REGISTRY}). */
	protected MetricRegistry registry = DEFAULT_REGISTRY;
	/** Cache of already-resolved metrics keyed by metric name. */
	protected ConcurrentHashMap<String, Metric> COMPLIED_METRICS = new ConcurrentHashMap<String, Metric>();
	protected static final Logger LOG = LoggerFactory.getLogger(MetricsFactory.class);

	/** Default constructor. */
	public MetricsFactory() {

	}

	/**
	 * Sets the active metric registry used by this factory.
	 *
	 * @param registry the metric registry to use
	 */
	public void setRegistry(MetricRegistry registry) {
		this.registry = registry;
	}

	/** @return the active metric registry. */
	public MetricRegistry getRegistry() {
		return registry;
	}

	/**
	 * Returns (creating if necessary) a {@link Timer} registered under the given name segments.
	 *
	 * @param names the name segments composing the metric name
	 * @return the resolved timer
	 */
	public Timer getTimer(String... names) {
		return getMetric(Timer.class, null, names);
	}

	/**
	 * Returns (creating if necessary) a {@link Timer} registered under the given
	 * owning class and name segments.
	 *
	 * @param clazz the owning class used as the name prefix
	 * @param names the name segments composing the metric name
	 * @return the resolved timer
	 */
	public Timer getTimer(Class<?> clazz, String... names) {
		return getMetric(Timer.class, clazz, names);
	}

	/**
	 * Returns (creating if necessary) a {@link Histogram} registered under the given name segments.
	 *
	 * @param names the name segments composing the metric name
	 * @return the resolved histogram
	 */
	public Histogram getHistogram(String... names) {
		return getMetric(Histogram.class, null, names);
	}

	/**
	 * Returns (creating if necessary) a {@link Histogram} registered under the
	 * given owning class and name segments.
	 *
	 * @param clazz the owning class used as the name prefix
	 * @param names the name segments composing the metric name
	 * @return the resolved histogram
	 */
	public Histogram getHistogram(Class<?> clazz, String... names) {
		return getMetric(Histogram.class, clazz, names);
	}

	/**
	 * Returns (creating if necessary) a {@link Counter} registered under the given name segments.
	 *
	 * @param names the name segments composing the metric name
	 * @return the resolved counter
	 */
	public Counter getCounter(String... names) {
		return getMetric(Counter.class, null, names);
	}

	/**
	 * Returns (creating if necessary) a {@link Counter} registered under the
	 * given owning class and name segments.
	 *
	 * @param clazz the owning class used as the name prefix
	 * @param names the name segments composing the metric name
	 * @return the resolved counter
	 */
	public Counter getCounter(Class<?> clazz, String... names) {
		return getMetric(Counter.class, clazz, names);
	}

	/**
	 * Returns (creating if necessary) a {@link Meter} registered under the given name segments.
	 *
	 * @param names the name segments composing the metric name
	 * @return the resolved meter
	 */
	public Meter getMeter(String... names) {
		return getMetric(Meter.class, null, names);
	}

	/**
	 * Returns (creating if necessary) a {@link Meter} registered under the given
	 * owning class and name segments.
	 *
	 * @param clazz the owning class used as the name prefix
	 * @param names the name segments composing the metric name
	 * @return the resolved meter
	 */
	public Meter getMeter(Class<?> clazz, String... names) {
		return getMetric(Meter.class, clazz, names);
	}

	/**
	 * Resolves (creating and registering if necessary) a metric of the requested
	 * type under a name composed of the owning class (optional) and the given
	 * name segments. Results are cached per metric name.
	 *
	 * @param metricClass the metric type ({@link Timer}, {@link Meter}, {@link Counter}, {@link Histogram})
	 * @param clazz       optional owning class used as the name prefix
	 * @param names       the name segments composing the metric name
	 * @param <T>         the metric type
	 * @return the resolved metric instance
	 */
	private <T> T getMetric(Class<T> metricClass, Class<?> clazz, String... names) {
		String prefix = (clazz == null ? "" : clazz.getName());
		String key = MetricRegistry.name(metricClass.getName() + prefix , names);
		Metric ret = COMPLIED_METRICS.get(key);
		if (ret != null) {
			return (T) ret;
		}
		if (metricClass == Histogram.class) {
			ret = this.getRegistry().histogram(MetricRegistry.name(prefix, names));
		}
		if (metricClass == Timer.class) {
			ret = this.getRegistry().timer(MetricRegistry.name(prefix, names));
		}
		if (metricClass == Meter.class) {
			ret = this.getRegistry().meter(MetricRegistry.name(prefix, names));
		}
		if (metricClass == Counter.class) {
			ret = this.getRegistry().counter(MetricRegistry.name(prefix, names));
		}
		Metric existing = COMPLIED_METRICS.putIfAbsent(key, ret);
		if (existing != null) {
			ret = existing;
		}
		return (T) ret;
	 }

	/**
	 * Returns (creating if necessary) a {@link Gauge} registered under the given
	 * owning class and name segments, using the supplied factory to build new instances.
	 *
	 * @param supplier factory used to create a new gauge on first access
	 * @param clazz    the owning class used as the name prefix
	 * @param names    the name segments composing the metric name
	 * @param <T>      the gauge value type
	 * @return the resolved gauge
	 */
	public <T> Gauge<T> getGauge(MetricSupplier<Gauge> supplier, Class<?> clazz, String... names) {
		String key = MetricRegistry.name(clazz , names);
		Metric ret = COMPLIED_METRICS.get(key);
		if (ret != null) {
			return (Gauge<T>) ret;
		}
		ret = this.getRegistry().gauge(key, supplier);
		Metric existing = COMPLIED_METRICS.putIfAbsent(key, ret);
		if (existing != null) {
			ret = existing;
		}
		return (Gauge<T>) ret;
	}
	
	 /**
	  * Registers a metric under the given name.
	  *
	  * @param name   the metric name
	  * @param metric the metric to register
	  * @param <T>    the metric type
	  * @return the registered metric
	  * @throws IllegalArgumentException if the name is already in use
	  */
	 public <T extends Metric> T register(String name, T metric) throws IllegalArgumentException {
		 return this.getRegistry().register(name, metric);
	 }

	 /**
	  * Registers every metric in the given {@link MetricSet}.
	  *
	  * @param metrics the metric set to register
	  * @throws IllegalArgumentException if any name is already in use
	  */
	 public void registerAll(MetricSet metrics) throws IllegalArgumentException {
		 this.getRegistry().registerAll(metrics);
	 }

	 /**
	  * Removes the metric registered under the given name, if any.
	  *
	  * @param name the metric name
	  * @return {@code true} if a metric was removed
	  */
	 public boolean remove(String name) {
		 return this.getRegistry().remove(name);
	 }

	 /**
	  * Removes every metric matching the given filter.
	  *
	  * @param filter the filter selecting metrics to remove
	  */
	 public void removeMatching(MetricFilter filter) {
		 this.getRegistry().removeMatching(filter);
	 }

	/** @return the default metric registry shared across the global context. */
	public static MetricRegistry getContextMetricRegistry() {
		return DEFAULT_REGISTRY;
	}

	/** @return the default health-check registry shared across the global context. */
	public static HealthCheckRegistry getContextHealthCheckRegistry() {
		return HEALTH_CHECK_REGISTRY;
	}

	/** @return the shared "Gauges" metric registry. */
	public static MetricRegistry getGaugeMetricRegistry() {
		return SharedMetricRegistries.getOrCreate("Gauges");
	}

	/** @return the shared "Counters" metric registry. */
	public static MetricRegistry getCounterMetricRegistry() {
		return SharedMetricRegistries.getOrCreate("Counters");
	}

	/** @return the shared "Histograms" metric registry. */
	public static MetricRegistry getHistogramMetricRegistry() {
		return SharedMetricRegistries.getOrCreate("Histograms");
	}

	/** @return the shared "Meters" metric registry. */
	public static MetricRegistry getMeterMetricRegistry() {
		return SharedMetricRegistries.getOrCreate("Meters");
	}

	/** @return the shared "Timers" metric registry. */
	public static MetricRegistry getTimerMetricRegistry() {
		return SharedMetricRegistries.getOrCreate("Timers");
	}

	/**
	 * Returns the named shared metric registry, creating it if necessary.
	 *
	 * @param metrics the registry name (the central container for an application's metrics)
	 * @return the named shared metric registry
	 */
	public static MetricRegistry getMetricRegistry(String metrics) {
		return SharedMetricRegistries.getOrCreate(metrics);
	}

	/**
	 * Returns (creating if necessary) a {@link Histogram} from the shared
	 * "Histograms" registry, named after the given class and segments.
	 *
	 * @param clazz the owning class used to build the metric name
	 * @param names the name segments composing the metric name
	 * @param <T>   the owning class type
	 * @return the resolved histogram
	 */
	public static <T> Histogram histogram(Class<T> clazz, String... names) {
		return getHistogramMetricRegistry().histogram(MetricRegistry.name(clazz, names));
	}

	/**
	 * Returns (creating if necessary) a {@link Timer} from the shared "Timers"
	 * registry, named after the given class and segments.
	 *
	 * @param clazz the owning class used to build the metric name
	 * @param names the name segments composing the metric name
	 * @param <T>   the owning class type
	 * @return the resolved timer
	 */
	public static <T> Timer timer(Class<T> clazz, String... names) {
		return getTimerMetricRegistry().timer(MetricRegistry.name(clazz, names));
	}


	/**
	 * Returns (creating if necessary) a {@link Counter} from the shared
	 * "Counters" registry, named after the given class and segments.
	 *
	 * @param clazz the owning class used to build the metric name
	 * @param names the name segments composing the metric name
	 * @return the resolved counter
	 */
	public static <T> Counter counter(Class<?> clazz, String... names) {
		return getCounterMetricRegistry().counter(MetricRegistry.name(clazz, names));
	}

	/**
	 * Returns (creating if necessary) a {@link Meter} from the shared "Meters"
	 * registry, named after the given class and segments.
	 *
	 * @param clazz the owning class used to build the metric name
	 * @param names the name segments composing the metric name
	 * @param <T>   the owning class type
	 * @return the resolved meter
	 */
	public static <T> Meter meter(Class<T> clazz, String... names) {
		return getMeterMetricRegistry().meter(MetricRegistry.name(clazz, names));
	}

	/**
	 * Returns (creating if necessary) a {@link Gauge} from the shared "Gauges"
	 * registry, named after the given class and segments, using the supplied
	 * factory to build new instances.
	 *
	 * @param supplier factory used to create a new gauge on first access
	 * @param clazz    the owning class used to build the metric name
	 * @param names    the name segments composing the metric name
	 * @param <T>      the gauge value type
	 * @return the resolved gauge
	 */
	public static <T> Gauge<T> gauge(MetricSupplier<Gauge> supplier, Class<?> clazz, String... names) {
		return getGaugeMetricRegistry().gauge(MetricRegistry.name(clazz , names), supplier);
	}

}
