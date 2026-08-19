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
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
@ConfigurationProperties(prefix = MetricsReportProperties.PREFIX)
/**
 * <p>Auto-configuration for MetricsReportProperties.</p>
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
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

	/** @return return the types. */
	public String getTypes() {
		return types;
	}

	/** @param types set the types. */
	public void setTypes(String types) {
		this.types = types;
	}

	/** @return return the console. */
	public ConsoleReporterProperties getConsole() {
		return console;
	}

	/** @param console set the console. */
	public void setConsole(ConsoleReporterProperties console) {
		this.console = console;
	}

	/** @return return the csv. */
	public CsvReporterProperties getCsv() {
		return csv;
	}

	/** @param csv set the csv. */
	public void setCsv(CsvReporterProperties csv) {
		this.csv = csv;
	}

	/** @return return the database. */
	public DatabaseReporterProperties getDatabase() {
		return database;
	}

	/** @param database set the database. */
	public void setDatabase(DatabaseReporterProperties database) {
		this.database = database;
	}

	/** @return return the datadog. */
	public DatadogReporterProperties getDatadog() {
		return datadog;
	}

	/** @param datadog set the datadog. */
	public void setDatadog(DatadogReporterProperties datadog) {
		this.datadog = datadog;
	}

	/** @return return the ganglia. */
	public GangliaReporterProperties getGanglia() {
		return ganglia;
	}

	/** @param ganglia set the ganglia. */
	public void setGanglia(GangliaReporterProperties ganglia) {
		this.ganglia = ganglia;
	}

	/** @return return the graphite. */
	public GraphiteReporterProperties getGraphite() {
		return graphite;
	}

	/** @param graphite set the graphite. */
	public void setGraphite(GraphiteReporterProperties graphite) {
		this.graphite = graphite;
	}

	/** @return return the influxdb. */
	public InfluxdbReporterProperties getInfluxdb() {
		return influxdb;
	}

	/** @param influxdb set the influxdb. */
	public void setInfluxdb(InfluxdbReporterProperties influxdb) {
		this.influxdb = influxdb;
	}

	/** @return return the jmx. */
	public JmxReporterProperties getJmx() {
		return jmx;
	}

	/** @param jmx set the jmx. */
	public void setJmx(JmxReporterProperties jmx) {
		this.jmx = jmx;
	}

	/** @return return the kafka. */
	public KafkaReporterProperties getKafka() {
		return kafka;
	}

	/** @param kafka set the kafka. */
	public void setKafka(KafkaReporterProperties kafka) {
		this.kafka = kafka;
	}

	/** @return return the librato. */
	public LibratoReporterProperties getLibrato() {
		return librato;
	}

	/** @param librato set the librato. */
	public void setLibrato(LibratoReporterProperties librato) {
		this.librato = librato;
	}

	/** @return return the newrelic. */
	public NewRelicReporterProperties getNewrelic() {
		return newrelic;
	}

	/** @param newrelic set the newrelic. */
	public void setNewrelic(NewRelicReporterProperties newrelic) {
		this.newrelic = newrelic;
	}

	/** @return return the rocketmq. */
	public RocketmqReporterProperties getRocketmq() {
		return rocketmq;
	}

	/** @param rocketmq set the rocketmq. */
	public void setRocketmq(RocketmqReporterProperties rocketmq) {
		this.rocketmq = rocketmq;
	}

	/** @return return the slf4j. */
	public Slf4jReporterProperties getSlf4j() {
		return slf4j;
	}

	/** @param slf4j set the slf4j. */
	public void setSlf4j(Slf4jReporterProperties slf4j) {
		this.slf4j = slf4j;
	}

	/** @return return the zabbix. */
	public ZabbixReporterProperties getZabbix() {
		return zabbix;
	}

	/** @param zabbix set the zabbix. */
	public void setZabbix(ZabbixReporterProperties zabbix) {
		this.zabbix = zabbix;
	}

	
}
