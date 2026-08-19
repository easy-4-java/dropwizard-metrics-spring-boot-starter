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
package com.codahale.metrics.spring.boot.ext.servlet;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;

import com.codahale.metrics.Counter;
import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;

/**
 * Jakarta Servlet compatible replacement for {@code com.codahale.metrics.servlet.InstrumentedFilter}.
 * Tracks HTTP request metrics (status codes, active requests, timers) using Dropwizard Metrics.
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
public class InstrumentedFilter implements Filter {

    /** Servlet context attribute name used to look up the {@link MetricRegistry}. */
    public static final String REGISTRY_ATTRIBUTE = "com.codahale.metrics.servlet.InstrumentedFilter.registry";

    private static final String METRIC_PREFIX = "instrumentedFilter";
    private static final String NAME_PREFIX = "requests";

    private static final int OK = 200;
    private static final int CREATED = 201;
    private static final int NO_CONTENT = 204;
    private static final int BAD_REQUEST = 400;
    private static final int NOT_FOUND = 404;
    private static final int SERVER_ERROR = 500;

    private MetricRegistry registry;
    private ConcurrentMap<Integer, Meter> metersByStatusCode;
    private Meter otherMeter;
    private Meter timeoutsMeter;
    private Meter errorsMeter;
    private Counter activeRequests;
    private Timer requestTimer;

    @Override
    /**
     * <p>Init.</p>
     * @param config
     */
    public void init(FilterConfig config) throws ServletException {
        this.registry = getMetricRegistry(config);
        this.metersByStatusCode = new ConcurrentHashMap<>();
        this.otherMeter = registry.meter(name("other"));
        this.timeoutsMeter = registry.meter(name("timeouts"));
        this.errorsMeter = registry.meter(name("errors"));
        this.activeRequests = registry.counter(name("activeRequests"));
        this.requestTimer = registry.timer(name("requests"));
    }

    @Override
    /**
     * <p>Do filter.</p>
     * @param request
     * @param response
     * @param chain
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        activeRequests.inc();
        final Timer.Context context = requestTimer.time();
        try {
            final StatusExposingServletResponse wrappedResponse = new StatusExposingServletResponse(
                    (HttpServletResponse) response);
            chain.doFilter(request, wrappedResponse);
            final int status = wrappedResponse.getStatus();
            if (status >= 100 && status < 200) {
                // informational
            } else if (status >= 200 && status < 300) {
                markMeterForStatusCode(status);
            } else if (status >= 300 && status < 400) {
                // redirection
            } else if (status >= 400 && status < 500) {
                markMeterForStatusCode(status);
            } else if (status >= 500 && status < 600) {
                markMeterForStatusCode(status);
            } else {
                otherMeter.mark();
            }
        } finally {
            context.stop();
            activeRequests.dec();
        }
    }

    @Override
    /**
     * <p>Destroy.</p>
     */
    public void destroy() {
        // no-op
    }

    /**
     * <p>Mark meter for status code.</p>
     * @param status
     */
    private void markMeterForStatusCode(int status) {
        Meter meter = metersByStatusCode.get(status);
        if (meter == null) {
            meter = registry.meter(name(Integer.toString(status)));
            metersByStatusCode.putIfAbsent(status, meter);
        }
        meter.mark();
    }

    /**
     * <p>Name.</p>
     * @param parts
     * @return the result
     */
    private String name(String... parts) {
        final String[] n = new String[parts.length + 1];
        n[0] = METRIC_PREFIX;
        System.arraycopy(parts, 0, n, 1, parts.length);
        return MetricRegistry.name(NAME_PREFIX, n);
    }

    /** @return return the metric registry. */
    private MetricRegistry getMetricRegistry(FilterConfig config) {
        final MetricRegistry registry = (MetricRegistry) config.getServletContext()
                .getAttribute(REGISTRY_ATTRIBUTE);
        if (registry == null) {
            return new MetricRegistry();
        }
        return registry;
    }

    /**
     * <p>Auto-configuration for StatusExposingServletResponse.</p>
     * @author <a href="https://github.com/loong10k">Loong Wan</a>
     * @since 1.0.0
     */
    private static class StatusExposingServletResponse extends HttpServletResponseWrapper {
        private int httpStatus = HttpServletResponse.SC_OK;

        public StatusExposingServletResponse(HttpServletResponse response) {
            super(response);
        }

        @Override
        /**
         * <p>Send error.</p>
         * @param sc
         */
        public void sendError(int sc) throws IOException {
            httpStatus = sc;
            super.sendError(sc);
        }

        @Override
        /**
         * <p>Send error.</p>
         * @param sc
         * @param msg
         */
        public void sendError(int sc, String msg) throws IOException {
            httpStatus = sc;
            super.sendError(sc, msg);
        }

        @Override
        /** @param sc set the status. */
        public void setStatus(int sc) {
            httpStatus = sc;
            super.setStatus(sc);
        }

        @Override
        /** @return return the status. */
        public int getStatus() {
            return httpStatus;
        }
    }
}
