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
package com.codahale.metrics.spring.boot.factory.support;

/**
 * <p>Auto-configuration for BasicMetricPrefixSupplier.</p>
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
public class BasicMetricPrefixSupplier implements MetricPrefixSupplier {

	private final String prefix;

	public BasicMetricPrefixSupplier(final String prefix) {
		this.prefix = prefix;
	}

	@Override
	/** @return return the prefix. */
	public String getPrefix() {
		return prefix;
	}

}
