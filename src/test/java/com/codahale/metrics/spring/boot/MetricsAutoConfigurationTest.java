package com.codahale.metrics.spring.boot;

import static org.assertj.core.api.Assertions.assertThat;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.health.HealthCheckRegistry;
import com.codahale.metrics.spring.boot.ext.MetricsFactory;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

/**
 * Tests for {@link MetricsAutoConfiguration}.
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 */
class MetricsAutoConfigurationTest {

    private final ApplicationContextRunner runner = new ApplicationContextRunner()
            .withConfiguration(AutoConfigurations.of(MetricsAutoConfiguration.class));

    @Test
    void shouldCreateCoreBeans() {
        runner.run(context -> assertThat(context)
                .hasSingleBean(MetricRegistry.class)
                .hasSingleBean(HealthCheckRegistry.class)
                .hasSingleBean(MetricsFactory.class));
    }

    @Test
    void shouldRespectUserDefinedMetricRegistry() {
        MetricRegistry custom = new MetricRegistry();
        runner.withBean(MetricRegistry.class, () -> custom)
                .run(context -> assertThat(context.getBean(MetricRegistry.class)).isSameAs(custom));
    }

    @Test
    void shouldRespectUserDefinedHealthCheckRegistry() {
        HealthCheckRegistry custom = new HealthCheckRegistry();
        runner.withBean(HealthCheckRegistry.class, () -> custom)
                .run(context -> assertThat(context.getBean(HealthCheckRegistry.class)).isSameAs(custom));
    }

    @Test
    void shouldBindMetricsProperties() {
        runner.withPropertyValues(
                "dropwizard.metrics.expose-proxy=true",
                "dropwizard.metrics.proxy-target-class=true")
                .run(context -> {
                    MetricsProperties props = context.getBean(MetricsProperties.class);
                    assertThat(props.isExposeProxy()).isTrue();
                    assertThat(props.isProxyTargetClass()).isTrue();
                });
    }

    @Test
    void propertiesDefaults() {
        MetricsProperties props = new MetricsProperties();
        assertThat(props.isExposeProxy()).isFalse();
        assertThat(props.isProxyTargetClass()).isFalse();
        assertThat(props.getMetrics()).isEmpty();
        assertThat(MetricsProperties.PREFIX).isEqualTo("dropwizard.metrics");
    }

    @Test
    void propertiesAccessors() {
        MetricsProperties props = new MetricsProperties();
        props.setExposeProxy(true);
        props.setProxyTargetClass(true);
        props.setMetrics(java.util.Collections.singletonMap("jvm.gc", "com.codahale.metrics.jvm.GarbageCollectorMetricSet"));
        assertThat(props.isExposeProxy()).isTrue();
        assertThat(props.isProxyTargetClass()).isTrue();
        assertThat(props.getMetrics()).containsEntry("jvm.gc", "com.codahale.metrics.jvm.GarbageCollectorMetricSet");
    }

    @Test
    void destroyShouldNotThrow() throws Exception {
        MetricsAutoConfiguration config = new MetricsAutoConfiguration();
        config.destroy();
    }

    @Test
    void metricRegistryShouldRegisterMetricSets() {
        runner.withPropertyValues(
                "dropwizard.metrics.metrics.jvm.gc=com.codahale.metrics.jvm.GarbageCollectorMetricSet")
                .run(context -> {
                    MetricRegistry registry = context.getBean(MetricRegistry.class);
                    assertThat(registry).isNotNull();
                    assertThat(registry.getNames()).isNotEmpty();
                });
    }

    @Test
    void metricRegistryShouldHandleInvalidClass() {
        runner.withPropertyValues(
                "dropwizard.metrics.metrics.invalid=com.invalid.ClassThatDoesNotExist")
                .run(context -> {
                    MetricRegistry registry = context.getBean(MetricRegistry.class);
                    assertThat(registry).isNotNull();
                });
    }
}
