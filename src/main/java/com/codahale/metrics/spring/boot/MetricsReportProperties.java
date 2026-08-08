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
package com.codahale.metrics.spring.boot;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import com.codahale.metrics.spring.boot.property.ConsoleReporterProperties;
import com.codahale.metrics.spring.boot.property.CsvReporterProperties;
import com.codahale.metrics.spring.boot.property.DatabaseReporterProperties;
import com.codahale.metrics.spring.boot.property.DatadogReporterProperties;
import com.codahale.metrics.spring.boot.property.GangliaReporterProperties;
import com.codahale.metrics.spring.boot.property.GraphiteReporterProperties;
import com.codahale.metrics.spring.boot.property.InfluxdbReporterProperties;
import com.codahale.metrics.spring.boot.property.JmxReporterProperties;
import com.codahale.metrics.spring.boot.property.KafkaReporterProperties;
import com.codahale.metrics.spring.boot.property.LibratoReporterProperties;
import com.codahale.metrics.spring.boot.property.NewRelicReporterProperties;
import com.codahale.metrics.spring.boot.property.RocketmqReporterProperties;
import com.codahale.metrics.spring.boot.property.Slf4jReporterProperties;
import com.codahale.metrics.spring.boot.property.ZabbixReporterProperties;

/**
 * Configuration properties for the Dropwizard Metrics reporters.
 * <p>
 * Bound to the {@code dropwizard.metrics.report.*} namespace. Aggregates the
 * nested configuration for every supported reporter backend (console, CSV,
 * database, Datadog, Ganglia, Graphite, InfluxDB, JMX, Kafka, Librato,
 * New Relic, RocketMQ, SLF4J and Zabbix).</p>
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 * @since 1.0.0
 */
@ConfigurationProperties(prefix = MetricsReportProperties.PREFIX)
public class MetricsReportProperties {

	/** Configuration prefix for the metrics reporter properties. */
	public static final String PREFIX = "dropwizard.metrics.report";

	/**
	 * Comma-separated list of reporter types to activate (default
	 * {@code "console"}). Each enabled backend also needs its own
	 * {@code enabled=true} flag.
	 */
	// Required
	protected String types = "console";

	/** Nested console reporter configuration. */
	@NestedConfigurationProperty
	private ConsoleReporterProperties console;
	/** Nested CSV reporter configuration. */
	@NestedConfigurationProperty
	private CsvReporterProperties csv;
	/** Nested database (JDBC) reporter configuration. */
	@NestedConfigurationProperty
	private DatabaseReporterProperties database;
	/** Nested Datadog reporter configuration. */
	@NestedConfigurationProperty
	private DatadogReporterProperties datadog;
	/** Nested Ganglia reporter configuration. */
	@NestedConfigurationProperty
	private GangliaReporterProperties ganglia;
	/** Nested Graphite reporter configuration. */
	@NestedConfigurationProperty
	private GraphiteReporterProperties graphite;
	/** Nested InfluxDB reporter configuration. */
	@NestedConfigurationProperty
	private InfluxdbReporterProperties influxdb;
	/** Nested JMX reporter configuration. */
	@NestedConfigurationProperty
	private JmxReporterProperties jmx;
	/** Nested Kafka reporter configuration. */
	@NestedConfigurationProperty
	private KafkaReporterProperties kafka;
	/** Nested Librato reporter configuration. */
	@NestedConfigurationProperty
	private LibratoReporterProperties librato;
	/** Nested New Relic reporter configuration. */
	@NestedConfigurationProperty
	private NewRelicReporterProperties newrelic;
	/** Nested RocketMQ reporter configuration. */
	@NestedConfigurationProperty
	private RocketmqReporterProperties rocketmq;
	/** Nested SLF4J reporter configuration. */
	@NestedConfigurationProperty
	private Slf4jReporterProperties slf4j;
	/** Nested Zabbix reporter configuration. */
	@NestedConfigurationProperty
	private ZabbixReporterProperties zabbix;

	public String getTypes() {
		return types;
	}

	public void setTypes(String types) {
		this.types = types;
	}

	public ConsoleReporterProperties getConsole() {
		return console;
	}

	public void setConsole(ConsoleReporterProperties console) {
		this.console = console;
	}

	public CsvReporterProperties getCsv() {
		return csv;
	}

	public void setCsv(CsvReporterProperties csv) {
		this.csv = csv;
	}

	public DatabaseReporterProperties getDatabase() {
		return database;
	}

	public void setDatabase(DatabaseReporterProperties database) {
		this.database = database;
	}

	public DatadogReporterProperties getDatadog() {
		return datadog;
	}

	public void setDatadog(DatadogReporterProperties datadog) {
		this.datadog = datadog;
	}

	public GangliaReporterProperties getGanglia() {
		return ganglia;
	}

	public void setGanglia(GangliaReporterProperties ganglia) {
		this.ganglia = ganglia;
	}

	public GraphiteReporterProperties getGraphite() {
		return graphite;
	}

	public void setGraphite(GraphiteReporterProperties graphite) {
		this.graphite = graphite;
	}

	public InfluxdbReporterProperties getInfluxdb() {
		return influxdb;
	}

	public void setInfluxdb(InfluxdbReporterProperties influxdb) {
		this.influxdb = influxdb;
	}

	public JmxReporterProperties getJmx() {
		return jmx;
	}

	public void setJmx(JmxReporterProperties jmx) {
		this.jmx = jmx;
	}

	public KafkaReporterProperties getKafka() {
		return kafka;
	}

	public void setKafka(KafkaReporterProperties kafka) {
		this.kafka = kafka;
	}

	public LibratoReporterProperties getLibrato() {
		return librato;
	}

	public void setLibrato(LibratoReporterProperties librato) {
		this.librato = librato;
	}

	public NewRelicReporterProperties getNewrelic() {
		return newrelic;
	}

	public void setNewrelic(NewRelicReporterProperties newrelic) {
		this.newrelic = newrelic;
	}

	public RocketmqReporterProperties getRocketmq() {
		return rocketmq;
	}

	public void setRocketmq(RocketmqReporterProperties rocketmq) {
		this.rocketmq = rocketmq;
	}

	public Slf4jReporterProperties getSlf4j() {
		return slf4j;
	}

	public void setSlf4j(Slf4jReporterProperties slf4j) {
		this.slf4j = slf4j;
	}

	public ZabbixReporterProperties getZabbix() {
		return zabbix;
	}

	public void setZabbix(ZabbixReporterProperties zabbix) {
		this.zabbix = zabbix;
	}

	
}
