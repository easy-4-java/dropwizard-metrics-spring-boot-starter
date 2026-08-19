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

import com.codahale.metrics.spring.boot.MetricsReportProperties;

/**
 * <p>Auto-configuration for DatabaseReporterProperties.</p>
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
public class DatabaseReporterProperties extends ReporterProperties {

	public static final String PREFIX = MetricsReportProperties.PREFIX + ".database";
	/**
	 * Whether to roll back the transaction when an exception is thrown.
	 */
	private boolean rollbackOnException;
	/**
	 * Whether to close the connection when a statement is commit.
	 */
	private boolean closeOnCommit;

	private String caugeTable = "cauge_metrics";
	private String counterTable = "counter_metrics";
	private String histogramTable = "histogram_metrics";
	private String meterTable = "meter_metrics";
	private String timerTable = "timer_metrics";

	private boolean allowCauge = false;
	private boolean allowCounter = true;
	private boolean allowHistogram = false;
	private boolean allowMeter = false;
	private boolean allowTimer = false;

	/** @return return whether rollback on exception is enabled. */
	public boolean isRollbackOnException() {
		return rollbackOnException;
	}

	/** @param rollbackOnException set the rollback on exception. */
	public void setRollbackOnException(boolean rollbackOnException) {
		this.rollbackOnException = rollbackOnException;
	}

	/** @return return whether close on commit is enabled. */
	public boolean isCloseOnCommit() {
		return closeOnCommit;
	}

	/** @param closeOnCommit set the close on commit. */
	public void setCloseOnCommit(boolean closeOnCommit) {
		this.closeOnCommit = closeOnCommit;
	}

	/** @return return the cauge table. */
	public String getCaugeTable() {
		return caugeTable;
	}

	/** @param caugeTable set the cauge table. */
	public void setCaugeTable(String caugeTable) {
		this.caugeTable = caugeTable;
	}

	/** @return return the counter table. */
	public String getCounterTable() {
		return counterTable;
	}

	/** @param counterTable set the counter table. */
	public void setCounterTable(String counterTable) {
		this.counterTable = counterTable;
	}

	/** @return return the histogram table. */
	public String getHistogramTable() {
		return histogramTable;
	}

	/** @param histogramTable set the histogram table. */
	public void setHistogramTable(String histogramTable) {
		this.histogramTable = histogramTable;
	}

	/** @return return the meter table. */
	public String getMeterTable() {
		return meterTable;
	}

	/** @param meterTable set the meter table. */
	public void setMeterTable(String meterTable) {
		this.meterTable = meterTable;
	}

	/** @return return the timer table. */
	public String getTimerTable() {
		return timerTable;
	}

	/** @param timerTable set the timer table. */
	public void setTimerTable(String timerTable) {
		this.timerTable = timerTable;
	}

	/** @return return whether allow cauge is enabled. */
	public boolean isAllowCauge() {
		return allowCauge;
	}

	/** @param allowCauge set the allow cauge. */
	public void setAllowCauge(boolean allowCauge) {
		this.allowCauge = allowCauge;
	}

	/** @return return whether allow counter is enabled. */
	public boolean isAllowCounter() {
		return allowCounter;
	}

	/** @param allowCounter set the allow counter. */
	public void setAllowCounter(boolean allowCounter) {
		this.allowCounter = allowCounter;
	}

	/** @return return whether allow histogram is enabled. */
	public boolean isAllowHistogram() {
		return allowHistogram;
	}

	/** @param allowHistogram set the allow histogram. */
	public void setAllowHistogram(boolean allowHistogram) {
		this.allowHistogram = allowHistogram;
	}

	/** @return return whether allow meter is enabled. */
	public boolean isAllowMeter() {
		return allowMeter;
	}

	/** @param allowMeter set the allow meter. */
	public void setAllowMeter(boolean allowMeter) {
		this.allowMeter = allowMeter;
	}

	/** @return return whether allow timer is enabled. */
	public boolean isAllowTimer() {
		return allowTimer;
	}

	/** @param allowTimer set the allow timer. */
	public void setAllowTimer(boolean allowTimer) {
		this.allowTimer = allowTimer;
	}

}
