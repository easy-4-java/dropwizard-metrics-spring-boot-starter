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

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.spring.boot.ext.MetricsFactory;

/**
 * Jakarta Servlet compatible context listener that registers the {@link MetricRegistry}
 * under the {@link InstrumentedFilter#REGISTRY_ATTRIBUTE} attribute name.
 * @author [@Loong Wan](https://github.com/loong10k)
 */
public class InstrumentedFilterContextListener implements jakarta.servlet.ServletContextListener {

    @Override
    public void contextInitialized(jakarta.servlet.ServletContextEvent sce) {
        sce.getServletContext().setAttribute(
                InstrumentedFilter.REGISTRY_ATTRIBUTE,
                getMetricRegistry());
    }

    @Override
    public void contextDestroyed(jakarta.servlet.ServletContextEvent sce) {
        // no-op
    }

    protected MetricRegistry getMetricRegistry() {
        return MetricsFactory.getContextMetricRegistry();
    }

}
