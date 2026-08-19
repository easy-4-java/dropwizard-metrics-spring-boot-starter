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
package com.codahale.metrics.spring.boot.factory;

import javax.management.MBeanServer;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.SmartLifecycle;
import org.springframework.util.StringUtils;

import com.codahale.metrics.jmx.JmxReporter;
import com.codahale.metrics.spring.boot.property.JmxReporterProperties;

/**
 * <p>Auto-configuration for JmxReporterFactoryBean.</p>
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
public class JmxReporterFactoryBean extends AbstractReporterFactoryBean<JmxReporter,JmxReporterProperties> implements SmartLifecycle, DisposableBean {

	private boolean running = false;
	private MBeanServer mBeanServer;
	
	public JmxReporterFactoryBean(JmxReporterProperties properties) {
		super(properties);
	}

	@Override
	/** @return return the object type. */
	public Class<JmxReporter> getObjectType() {
		return JmxReporter.class;
	}

	@Override
	/**
	 * <p>Create instance.</p>
	 * @param properties
	 * @return the result
	 */
	protected JmxReporter createInstance(JmxReporterProperties properties) {
		final JmxReporter.Builder reporter = JmxReporter.forRegistry(getMetricRegistry())
				.convertDurationsTo(properties.getDurationUnit())
				.convertRatesTo(properties.getRateUnit())
				.filter(getMetricFilter());

		if (StringUtils.hasText(properties.getDomain())) {
			reporter.inDomain(properties.getDomain());
		}

		if (getmBeanServer() != null) {
			reporter.registerWith(getmBeanServer());
		}

		return reporter.build();
	}

	@Override
	/**
	 * <p>Start.</p>
	 */
	public void start() {
		if (isEnabled() && !isRunning()) {
			getObject().start();
			running = true;
		}
	}

	@Override
	/**
	 * <p>Stop.</p>
	 */
	public void stop() {
		if (isRunning()) {
			getObject().stop();
			running = false;
		}
	}

	@Override
	/** @return return whether running is enabled. */
	public boolean isRunning() {
		return running;
	}

	@Override
	/**
	 * <p>Destroy.</p>
	 */
	public void destroy() throws Exception {
		stop();
	}

	@Override
	/** @return return whether auto startup is enabled. */
	public boolean isAutoStartup() {
		return true;
	}

	@Override
	/**
	 * <p>Stop.</p>
	 * @param runnable
	 */
	public void stop(Runnable runnable) {
		stop();
		runnable.run();
	}

	@Override
	/** @return return the phase. */
	public int getPhase() {
		return 0;
	}

	/** @return return the value of getmBeanServer. */
	public MBeanServer getmBeanServer() {
		return mBeanServer;
	}

	/** @param mBeanServer set the value of setmBeanServer. */
	public void setmBeanServer(MBeanServer mBeanServer) {
		this.mBeanServer = mBeanServer;
	}

}
