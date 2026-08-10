package com.codahale.metrics.spring.boot;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.spring.boot.ext.servlet.InstrumentedFilter;
import com.codahale.metrics.spring.boot.ext.servlet.InstrumentedFilterContextListener;
import org.junit.jupiter.api.Test;

import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Tests for {@link InstrumentedFilter} and {@link InstrumentedFilterContextListener}.
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 */
class InstrumentedFilterTest {

    @Test
    void constantsShouldBeCorrect() {
        assertThat(InstrumentedFilter.REGISTRY_ATTRIBUTE)
                .isEqualTo("com.codahale.metrics.servlet.InstrumentedFilter.registry");
    }

    @Test
    void filterShouldInitializeAndTrackMetrics() throws Exception {
        MetricRegistry registry = new MetricRegistry();
        InstrumentedFilter filter = new InstrumentedFilter();

        ServletContext servletContext = mock(ServletContext.class);
        when(servletContext.getAttribute(InstrumentedFilter.REGISTRY_ATTRIBUTE)).thenReturn(registry);

        FilterConfig config = mock(FilterConfig.class);
        when(config.getServletContext()).thenReturn(servletContext);

        filter.init(config);

        // Verify metrics were created
        assertThat(registry.getNames()).isNotEmpty();
        assertThat(registry.getNames()).anyMatch(n -> n.contains("activeRequests"));

        filter.destroy();
    }

    @Test
    void filterShouldProcessRequests() throws Exception {
        MetricRegistry registry = new MetricRegistry();
        InstrumentedFilter filter = new InstrumentedFilter();

        ServletContext servletContext = mock(ServletContext.class);
        when(servletContext.getAttribute(InstrumentedFilter.REGISTRY_ATTRIBUTE)).thenReturn(registry);

        FilterConfig config = mock(FilterConfig.class);
        when(config.getServletContext()).thenReturn(servletContext);

        filter.init(config);

        assertThat(registry.getNames()).isNotEmpty();

        filter.destroy();
    }

    @Test
    void filterShouldHandleNullRegistry() throws Exception {
        InstrumentedFilter filter = new InstrumentedFilter();

        ServletContext servletContext = mock(ServletContext.class);
        when(servletContext.getAttribute(InstrumentedFilter.REGISTRY_ATTRIBUTE)).thenReturn(null);

        FilterConfig config = mock(FilterConfig.class);
        when(config.getServletContext()).thenReturn(servletContext);

        filter.init(config);
        assertThat(filter).isNotNull();
        filter.destroy();
    }

    @Test
    void contextListenerShouldNotBeNull() {
        InstrumentedFilterContextListener listener = new InstrumentedFilterContextListener();
        assertThat(listener).isNotNull();
    }
}
