/**
 * Copyright (C) 2012 Ryan W Tenney (ryan@10e.us)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.codahale.metrics.spring.boot.aop;

import java.lang.reflect.Method;

import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import org.springframework.util.ReflectionUtils.MethodFilter;

import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.annotation.Metered;
import com.codahale.metrics.spring.boot.ext.filter.AnnotationFilter;
import com.codahale.metrics.spring.boot.factory.AdviceFactory;
import com.codahale.metrics.spring.boot.utils.MetricUtils;

/**
 * <p>Auto-configuration for MeteredMethodInterceptor.</p>
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
public class MeteredMethodInterceptor extends AbstractMetricMethodInterceptor<Metered, Meter> {

	public static final Class<Metered> ANNOTATION = Metered.class;
	public static final Pointcut POINTCUT = new AnnotationMatchingPointcut(null, ANNOTATION);
	public static final MethodFilter METHOD_FILTER = new AnnotationFilter(ANNOTATION, AnnotationFilter.PROXYABLE_METHODS);

	public MeteredMethodInterceptor(final MetricRegistry metricRegistry, final Class<?> targetClass) {
		super(metricRegistry, targetClass, ANNOTATION, METHOD_FILTER);
	}

	@Override
	/**
	 * <p>Invoke.</p>
	 * @param invocation
	 * @param meter
	 * @param annotation
	 * @return the result
	 */
	protected Object invoke(MethodInvocation invocation, Meter meter, Metered annotation) throws Throwable {
		meter.mark();
		return invocation.proceed();
	}

	@Override
	/**
	 * <p>Build metric.</p>
	 * @param metricRegistry
	 * @param metricName
	 * @param annotation
	 * @return the result
	 */
	protected Meter buildMetric(MetricRegistry metricRegistry, String metricName, Metered annotation) {
		return metricRegistry.meter(metricName);
	}

	@Override
	/**
	 * <p>Build metric name.</p>
	 * @param targetClass
	 * @param method
	 * @param annotation
	 * @return the result
	 */
	protected String buildMetricName(Class<?> targetClass, Method method, Metered annotation) {
		return MetricUtils.forMeteredMethod(targetClass, method, annotation);
	}

	/**
	 * <p>Advice factory.</p>
	 * @param metricRegistry
	 * @return the result
	 */
	public static AdviceFactory adviceFactory(final MetricRegistry metricRegistry) {
		return new AdviceFactory() {
			@Override
			/** @return return the advice. */
			public Advice getAdvice(Object bean, Class<?> targetClass) {
				return new MeteredMethodInterceptor(metricRegistry, targetClass);
			}
		};
	}

}
