package com.codahale.metrics.spring.boot;

import static org.assertj.core.api.Assertions.assertThat;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.spring.boot.ext.servlet.InstrumentedFilter;
import com.codahale.metrics.spring.boot.ext.servlet.InstrumentedFilterContextListener;
import com.codahale.metrics.spring.boot.factory.support.MetricsInstrumentedRegistrar;
import com.codahale.metrics.spring.boot.factory.support.MetricsServletRegistrar;
import org.junit.jupiter.api.Test;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.web.context.support.ServletContextAttributeExporter;

/**
 * Tests for {@link MetricsInstrumentedRegistrar} and {@link MetricsServletRegistrar}.
 * @author [@Loong Wan](https://github.com/loong10k)
 */
class MetricsRegistrarTest {

    @Test
    void filterAttributeExporterShouldContainRegistry() {
        MetricsInstrumentedRegistrar registrar = new MetricsInstrumentedRegistrar();
        MetricRegistry registry = new MetricRegistry();
        ServletContextAttributeExporter exporter = registrar.filterAttributeExporter(registry);
        assertThat(exporter).isNotNull();
    }

    @Test
    void instrumentedFilterShouldCreateRegistrationBean() {
        MetricsInstrumentedRegistrar registrar = new MetricsInstrumentedRegistrar();
        FilterRegistrationBean bean = registrar.instrumentedFilter();
        assertThat(bean).isNotNull();
        assertThat(bean.getFilter()).isInstanceOf(InstrumentedFilter.class);
    }

    @Test
    void servletListenerBeansShouldBeCreated() {
        MetricsInstrumentedRegistrar registrar = new MetricsInstrumentedRegistrar();
        assertThat(registrar.httpServletContextAttributeMetricsListener()).isNotNull();
        assertThat(registrar.httpServletRequestAttributeMetricsListener()).isNotNull();
        assertThat(registrar.httpServletRequestMetricsListener()).isNotNull();
        assertThat(registrar.httpSessionAttributeMetricsListener()).isNotNull();
        assertThat(registrar.httpSessionMetricsListener()).isNotNull();
    }

    @Test
    void servletsAttributeExporterShouldContainRegistries() {
        MetricsServletRegistrar registrar = new MetricsServletRegistrar();
        MetricRegistry registry = new MetricRegistry();
        ServletContextAttributeExporter exporter = registrar.servletsAttributeExporter(registry);
        assertThat(exporter).isNotNull();
    }

    @Test
    void instrumentedFilterConstants() {
        assertThat(InstrumentedFilter.REGISTRY_ATTRIBUTE)
                .isEqualTo("com.codahale.metrics.servlet.InstrumentedFilter.registry");
    }
}
