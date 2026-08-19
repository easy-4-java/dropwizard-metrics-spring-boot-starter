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
package com.codahale.metrics.spring.boot.property;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

import com.codahale.metrics.spring.boot.ext.filter.FilterType;

/**
 * <p>Auto-configuration for ReporterProperties.</p>
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
public abstract class ReporterProperties {

	protected Boolean enabled = false;
	
	// Required
	private String period = "10s";
	
	// Optional

	private String prefix = "";
	private TimeUnit durationUnit = TimeUnit.MILLISECONDS;
	private TimeUnit rateUnit = TimeUnit.SECONDS;
	private String locale = Locale.SIMPLIFIED_CHINESE.toString();
	
	private FilterType filterType = FilterType.PATTERN;
	private String filterValue = null;
	
	/** @return return the enabled. */
	public Boolean getEnabled() {
		return enabled;
	}

	/** @param enabled set the enabled. */
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	/** @return return the period. */
	public String getPeriod() {
		return period;
	}

	/** @param period set the period. */
	public void setPeriod(String period) {
		this.period = period;
	}

	/** @return return the rate unit. */
	public TimeUnit getRateUnit() {
		return rateUnit;
	}

	/** @param rateUnit set the rate unit. */
	public void setRateUnit(TimeUnit rateUnit) {
		this.rateUnit = rateUnit;
	}

	/** @return return the duration unit. */
	public TimeUnit getDurationUnit() {
		return durationUnit;
	}

	/** @param durationUnit set the duration unit. */
	public void setDurationUnit(TimeUnit durationUnit) {
		this.durationUnit = durationUnit;
	}
	
	/** @return return the locale. */
	public String getLocale() {
		return locale;
	}

	/** @param locale set the locale. */
	public void setLocale(String locale) {
		this.locale = locale;
	}

	/** @return return the filter type. */
	public FilterType getFilterType() {
		return filterType;
	}

	/** @param filterType set the filter type. */
	public void setFilterType(FilterType filterType) {
		this.filterType = filterType;
	}

	/** @return return the filter value. */
	public String getFilterValue() {
		return filterValue;
	}

	/** @param filterValue set the filter value. */
	public void setFilterValue(String filterValue) {
		this.filterValue = filterValue;
	}
	
	/** @return return the prefix. */
	public String getPrefix() {
		return prefix;
	}

	/** @param prefix set the prefix. */
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	
}
