package com.codahale.metrics.spring.boot;

import static org.assertj.core.api.Assertions.assertThat;

import com.codahale.metrics.Counter;
import com.codahale.metrics.Histogram;
import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;
import com.codahale.metrics.health.HealthCheckRegistry;
import com.codahale.metrics.spring.boot.ext.MetricsFactory;
import org.junit.jupiter.api.Test;

/**
 * Tests for {@link MetricsFactory}.
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 */
class MetricsFactoryTest {

    @Test
    void defaultRegistryShouldBeNonNull() {
        MetricsFactory factory = new MetricsFactory();
        assertThat(factory.getRegistry()).isNotNull();
    }

    @Test
    void setRegistryShouldUpdateRegistry() {
        MetricsFactory factory = new MetricsFactory();
        MetricRegistry custom = new MetricRegistry();
        factory.setRegistry(custom);
        assertThat(factory.getRegistry()).isSameAs(custom);
    }

    @Test
    void getTimerShouldReturnTimer() {
        MetricsFactory factory = new MetricsFactory();
        Timer timer = factory.getTimer("test", "timer");
        assertThat(timer).isNotNull();
    }

    @Test
    void getTimerWithClassShouldReturnTimer() {
        MetricsFactory factory = new MetricsFactory();
        Timer timer = factory.getTimer(MetricsFactoryTest.class, "timerWithClass");
        assertThat(timer).isNotNull();
    }

    @Test
    void getHistogramShouldReturnHistogram() {
        MetricsFactory factory = new MetricsFactory();
        Histogram histogram = factory.getHistogram("test", "histogram");
        assertThat(histogram).isNotNull();
    }

    @Test
    void getHistogramWithClassShouldReturnHistogram() {
        MetricsFactory factory = new MetricsFactory();
        Histogram histogram = factory.getHistogram(MetricsFactoryTest.class, "histogramWithClass");
        assertThat(histogram).isNotNull();
    }

    @Test
    void getCounterShouldReturnCounter() {
        MetricsFactory factory = new MetricsFactory();
        Counter counter = factory.getCounter("test", "counter");
        assertThat(counter).isNotNull();
    }

    @Test
    void getCounterWithClassShouldReturnCounter() {
        MetricsFactory factory = new MetricsFactory();
        Counter counter = factory.getCounter(MetricsFactoryTest.class, "counterWithClass");
        assertThat(counter).isNotNull();
    }

    @Test
    void getMeterShouldReturnMeter() {
        MetricsFactory factory = new MetricsFactory();
        Meter meter = factory.getMeter("test", "meter");
        assertThat(meter).isNotNull();
    }

    @Test
    void getMeterWithClassShouldReturnMeter() {
        MetricsFactory factory = new MetricsFactory();
        Meter meter = factory.getMeter(MetricsFactoryTest.class, "test");
        assertThat(meter).isNotNull();
    }

    @Test
    void getMetricShouldReturnCachedInstance() {
        MetricsFactory factory = new MetricsFactory();
        Timer first = factory.getTimer("cached");
        Timer second = factory.getTimer("cached");
        assertThat(first).isSameAs(second);
    }

    @Test
    void registerShouldRegisterMetric() {
        MetricsFactory factory = new MetricsFactory();
        Counter counter = factory.register("myCounter", new Counter());
        assertThat(counter).isNotNull();
        assertThat(factory.getRegistry().getNames()).contains("myCounter");
    }

    @Test
    void removeShouldRemoveMetric() {
        MetricsFactory factory = new MetricsFactory();
        factory.getCounter("toRemove");
        assertThat(factory.remove("toRemove")).isTrue();
        assertThat(factory.getRegistry().getNames()).doesNotContain("toRemove");
    }

    @Test
    void removeMatchingShouldRemoveMatchingMetrics() {
        MetricsFactory factory = new MetricsFactory();
        factory.getCounter("removeA");
        factory.getCounter("removeB");
        factory.removeMatching((name, metric) -> name.startsWith("remove"));
        assertThat(factory.getRegistry().getNames()).noneMatch(n -> n.startsWith("remove"));
    }

    @Test
    void staticContextRegistriesShouldBeNonNull() {
        assertThat(MetricsFactory.getContextMetricRegistry()).isNotNull();
        assertThat(MetricsFactory.getContextHealthCheckRegistry()).isNotNull();
    }

    @Test
    void staticSharedRegistriesShouldBeNonNull() {
        assertThat(MetricsFactory.getGaugeMetricRegistry()).isNotNull();
        assertThat(MetricsFactory.getCounterMetricRegistry()).isNotNull();
        assertThat(MetricsFactory.getHistogramMetricRegistry()).isNotNull();
        assertThat(MetricsFactory.getMeterMetricRegistry()).isNotNull();
        assertThat(MetricsFactory.getTimerMetricRegistry()).isNotNull();
    }

    @Test
    void staticGetMetricRegistryByNameShouldWork() {
        MetricRegistry reg = MetricsFactory.getMetricRegistry("custom");
        assertThat(reg).isNotNull();
    }

    @Test
    void staticHistogramShouldReturnHistogram() {
        Histogram h = MetricsFactory.histogram(MetricsFactoryTest.class, "h1");
        assertThat(h).isNotNull();
    }

    @Test
    void staticTimerShouldReturnTimer() {
        Timer t = MetricsFactory.timer(MetricsFactoryTest.class, "t1");
        assertThat(t).isNotNull();
    }

    @Test
    void staticCounterShouldReturnCounter() {
        Counter c = MetricsFactory.counter(MetricsFactoryTest.class, "c1");
        assertThat(c).isNotNull();
    }

    @Test
    void staticMeterShouldReturnMeter() {
        Meter m = MetricsFactory.meter(MetricsFactoryTest.class, "m1");
        assertThat(m).isNotNull();
    }

    @Test
    void getMetricShouldCacheMetrics() {
        MetricsFactory factory = new MetricsFactory();
        Counter first = factory.getCounter("cachedCounter");
        Counter second = factory.getCounter("cachedCounter");
        assertThat(first).isSameAs(second);
    }

    @Test
    void getMetricWithClassShouldCacheMetrics() {
        MetricsFactory factory = new MetricsFactory();
        Meter first = factory.getMeter(MetricsFactoryTest.class, "cachedMeter");
        Meter second = factory.getMeter(MetricsFactoryTest.class, "cachedMeter");
        assertThat(first).isSameAs(second);
    }

    @Test
    void registerAllShouldRegisterMetricSet() {
        MetricsFactory factory = new MetricsFactory();
        MetricRegistry registry = factory.getRegistry();
        assertThat(registry).isNotNull();
    }

    @Test
    void getGaugeShouldReturnGauge() {
        MetricsFactory factory = new MetricsFactory();
        com.codahale.metrics.Gauge<?> gauge = factory.getGauge(() -> () -> 42, MetricsFactoryTest.class, "gauge1");
        assertThat(gauge).isNotNull();
    }

    @Test
    void removeMatchingShouldWork() {
        MetricsFactory factory = new MetricsFactory();
        factory.getCounter("matchA");
        factory.getCounter("matchB");
        factory.removeMatching((name, metric) -> name.startsWith("match"));
        assertThat(factory.getRegistry().getNames()).noneMatch(n -> n.startsWith("match"));
    }
}
