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

import java.util.HashMap;
import java.util.Map;

import com.codahale.metrics.spring.boot.MetricsReportProperties;

/**
 * <p>Auto-configuration for LibratoReporterProperties.</p>
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
public class LibratoReporterProperties extends ReporterProperties {

	public static final String PREFIX = MetricsReportProperties.PREFIX + ".librato";
	// Required
	private String email;
	private String username;
	private String token;

	// Optional
	private String url = "https://metrics-api.librato.com";
	private String name = "librato";
	private String timeout;
	private String sourceRegex;

	private boolean deleteIdleStats = true;
	private boolean omitComplexGauges;
	private String prefixDelimiter = ".";
	private String expansionConfig = "ALL";
	private String source;
	private String readTimeout;
	private String connectTimeout;
	private Map<String /* name */, String /* value */> tags = new HashMap<String, String>();
	private boolean enableLegacy = true;
	private boolean enableTagging;
	
	/** @return return the email. */
	public String getEmail() {
		return email;
	}

	/** @param email set the email. */
	public void setEmail(String email) {
		this.email = email;
	}

	/** @return return the username. */
	public String getUsername() {
		return username;
	}

	/** @param username set the username. */
	public void setUsername(String username) {
		this.username = username;
	}

	/** @return return the token. */
	public String getToken() {
		return token;
	}

	/** @param token set the token. */
	public void setToken(String token) {
		this.token = token;
	}

	/** @return return the url. */
	public String getUrl() {
		return url;
	}

	/** @param url set the url. */
	public void setUrl(String url) {
		this.url = url;
	}

	/** @return return the name. */
	public String getName() {
		return name;
	}

	/** @param name set the name. */
	public void setName(String name) {
		this.name = name;
	}

	/** @return return the timeout. */
	public String getTimeout() {
		return timeout;
	}

	/** @param timeout set the timeout. */
	public void setTimeout(String timeout) {
		this.timeout = timeout;
	}

	/** @return return the source regex. */
	public String getSourceRegex() {
		return sourceRegex;
	}

	/** @param sourceRegex set the source regex. */
	public void setSourceRegex(String sourceRegex) {
		this.sourceRegex = sourceRegex;
	}

	/** @return return whether delete idle stats is enabled. */
	public boolean isDeleteIdleStats() {
		return deleteIdleStats;
	}

	/** @param deleteIdleStats set the delete idle stats. */
	public void setDeleteIdleStats(boolean deleteIdleStats) {
		this.deleteIdleStats = deleteIdleStats;
	}

	/** @return return whether omit complex gauges is enabled. */
	public boolean isOmitComplexGauges() {
		return omitComplexGauges;
	}

	/** @param omitComplexGauges set the omit complex gauges. */
	public void setOmitComplexGauges(boolean omitComplexGauges) {
		this.omitComplexGauges = omitComplexGauges;
	}

	/** @return return the prefix delimiter. */
	public String getPrefixDelimiter() {
		return prefixDelimiter;
	}

	/** @param prefixDelimiter set the prefix delimiter. */
	public void setPrefixDelimiter(String prefixDelimiter) {
		this.prefixDelimiter = prefixDelimiter;
	}

	/** @return return the expansion config. */
	public String getExpansionConfig() {
		return expansionConfig;
	}

	/** @param expansionConfig set the expansion config. */
	public void setExpansionConfig(String expansionConfig) {
		this.expansionConfig = expansionConfig;
	}

	/** @return return the source. */
	public String getSource() {
		return source;
	}

	/** @param source set the source. */
	public void setSource(String source) {
		this.source = source;
	}

	/** @return return the read timeout. */
	public String getReadTimeout() {
		return readTimeout;
	}

	/** @param readTimeout set the read timeout. */
	public void setReadTimeout(String readTimeout) {
		this.readTimeout = readTimeout;
	}

	/** @return return the connect timeout. */
	public String getConnectTimeout() {
		return connectTimeout;
	}

	/** @param connectTimeout set the connect timeout. */
	public void setConnectTimeout(String connectTimeout) {
		this.connectTimeout = connectTimeout;
	}

	/** @return return the tags. */
	public Map<String, String> getTags() {
		return tags;
	}

	/** @param tags set the tags. */
	public void setTags(Map<String, String> tags) {
		this.tags = tags;
	}

	/** @return return whether enable legacy is enabled. */
	public boolean isEnableLegacy() {
		return enableLegacy;
	}

	/** @param enableLegacy set the enable legacy. */
	public void setEnableLegacy(boolean enableLegacy) {
		this.enableLegacy = enableLegacy;
	}

	/** @return return whether enable tagging is enabled. */
	public boolean isEnableTagging() {
		return enableTagging;
	}

	/** @param enableTagging set the enable tagging. */
	public void setEnableTagging(boolean enableTagging) {
		this.enableTagging = enableTagging;
	}

}
