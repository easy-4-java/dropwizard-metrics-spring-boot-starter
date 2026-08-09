package com.codahale.metrics.spring.boot;

import static org.assertj.core.api.Assertions.assertThat;

import com.codahale.metrics.spring.boot.property.*;
import org.junit.jupiter.api.Test;

/**
 * Tests for {@link MetricsReportProperties} and nested reporter properties.
 * @author [@Loong Wan](https://github.com/loong10k)
 */
class MetricsReportPropertiesTest {

    @Test
    void defaultsAndAccessors() {
        MetricsReportProperties props = new MetricsReportProperties();
        assertThat(MetricsReportProperties.PREFIX).isEqualTo("dropwizard.metrics.report");
        assertThat(props.getTypes()).isEqualTo("console");
        assertThat(props.getConsole()).isNull();
        assertThat(props.getCsv()).isNull();
        assertThat(props.getDatabase()).isNull();
        assertThat(props.getDatadog()).isNull();
        assertThat(props.getGanglia()).isNull();
        assertThat(props.getGraphite()).isNull();
        assertThat(props.getInfluxdb()).isNull();
        assertThat(props.getJmx()).isNull();
        assertThat(props.getKafka()).isNull();
        assertThat(props.getLibrato()).isNull();
        assertThat(props.getNewrelic()).isNull();
        assertThat(props.getRocketmq()).isNull();
        assertThat(props.getSlf4j()).isNull();
        assertThat(props.getZabbix()).isNull();
    }

    @Test
    void settersAndGetters() {
        MetricsReportProperties props = new MetricsReportProperties();
        props.setTypes("jmx");
        assertThat(props.getTypes()).isEqualTo("jmx");

        ConsoleReporterProperties console = new ConsoleReporterProperties();
        props.setConsole(console);
        assertThat(props.getConsole()).isSameAs(console);

        CsvReporterProperties csv = new CsvReporterProperties();
        props.setCsv(csv);
        assertThat(props.getCsv()).isSameAs(csv);

        DatabaseReporterProperties database = new DatabaseReporterProperties();
        props.setDatabase(database);
        assertThat(props.getDatabase()).isSameAs(database);

        DatadogReporterProperties datadog = new DatadogReporterProperties();
        props.setDatadog(datadog);
        assertThat(props.getDatadog()).isSameAs(datadog);

        GangliaReporterProperties ganglia = new GangliaReporterProperties();
        props.setGanglia(ganglia);
        assertThat(props.getGanglia()).isSameAs(ganglia);

        GraphiteReporterProperties graphite = new GraphiteReporterProperties();
        props.setGraphite(graphite);
        assertThat(props.getGraphite()).isSameAs(graphite);

        InfluxdbReporterProperties influxdb = new InfluxdbReporterProperties();
        props.setInfluxdb(influxdb);
        assertThat(props.getInfluxdb()).isSameAs(influxdb);

        JmxReporterProperties jmx = new JmxReporterProperties();
        props.setJmx(jmx);
        assertThat(props.getJmx()).isSameAs(jmx);

        KafkaReporterProperties kafka = new KafkaReporterProperties();
        props.setKafka(kafka);
        assertThat(props.getKafka()).isSameAs(kafka);

        LibratoReporterProperties librato = new LibratoReporterProperties();
        props.setLibrato(librato);
        assertThat(props.getLibrato()).isSameAs(librato);

        NewRelicReporterProperties newrelic = new NewRelicReporterProperties();
        props.setNewrelic(newrelic);
        assertThat(props.getNewrelic()).isSameAs(newrelic);

        RocketmqReporterProperties rocketmq = new RocketmqReporterProperties();
        props.setRocketmq(rocketmq);
        assertThat(props.getRocketmq()).isSameAs(rocketmq);

        Slf4jReporterProperties slf4j = new Slf4jReporterProperties();
        props.setSlf4j(slf4j);
        assertThat(props.getSlf4j()).isSameAs(slf4j);

        ZabbixReporterProperties zabbix = new ZabbixReporterProperties();
        props.setZabbix(zabbix);
        assertThat(props.getZabbix()).isSameAs(zabbix);
    }

    @Test
    void reporterPropertiesPrefixes() {
        assertThat(ConsoleReporterProperties.PREFIX).isEqualTo("dropwizard.metrics.report.console");
        assertThat(CsvReporterProperties.PREFIX).isEqualTo("dropwizard.metrics.report.csv");
        assertThat(DatabaseReporterProperties.PREFIX).isEqualTo("dropwizard.metrics.report.database");
        assertThat(DatadogReporterProperties.PREFIX).isEqualTo("dropwizard.metrics.report.datadog");
        assertThat(GangliaReporterProperties.PREFIX).isEqualTo("dropwizard.metrics.report.ganglia");
        assertThat(GraphiteReporterProperties.PREFIX).isEqualTo("dropwizard.metrics.report.graphite");
        assertThat(InfluxdbReporterProperties.PREFIX).isEqualTo("dropwizard.metrics.report.influxdb");
        assertThat(JmxReporterProperties.PREFIX).isEqualTo("dropwizard.metrics.report.jmx");
        assertThat(KafkaReporterProperties.PREFIX).isEqualTo("dropwizard.metrics.report.kafka");
        assertThat(LibratoReporterProperties.PREFIX).isEqualTo("dropwizard.metrics.report.librato");
        assertThat(NewRelicReporterProperties.PREFIX).isEqualTo("dropwizard.metrics.report.newrelic");
        assertThat(Slf4jReporterProperties.PREFIX).isEqualTo("dropwizard.metrics.report.slf4j");
        assertThat(ZabbixReporterProperties.PREFIX).isEqualTo("dropwizard.metrics.report.zabbix");
    }

    @Test
    void reporterPropertiesDefaults() {
        assertReporterDefaults(new ConsoleReporterProperties());
        assertReporterDefaults(new CsvReporterProperties());
        assertReporterDefaults(new DatabaseReporterProperties());
        assertReporterDefaults(new DatadogReporterProperties());
        assertReporterDefaults(new GangliaReporterProperties());
        assertReporterDefaults(new GraphiteReporterProperties());
        assertReporterDefaults(new InfluxdbReporterProperties());
        assertReporterDefaults(new JmxReporterProperties());
        assertReporterDefaults(new KafkaReporterProperties());
        assertReporterDefaults(new LibratoReporterProperties());
        assertReporterDefaults(new NewRelicReporterProperties());
        assertReporterDefaults(new RocketmqReporterProperties());
        assertReporterDefaults(new Slf4jReporterProperties());
        assertReporterDefaults(new ZabbixReporterProperties());
    }

    private void assertReporterDefaults(ReporterProperties props) {
        assertThat(props.getEnabled()).isFalse();
        assertThat(props.getPeriod()).isEqualTo("10s");
        assertThat(props.getPrefix()).isEmpty();
        assertThat(props.getDurationUnit()).isEqualTo(java.util.concurrent.TimeUnit.MILLISECONDS);
        assertThat(props.getRateUnit()).isEqualTo(java.util.concurrent.TimeUnit.SECONDS);
        assertThat(props.getLocale()).isEqualTo(java.util.Locale.SIMPLIFIED_CHINESE.toString());
        assertThat(props.getFilterType()).isNotNull();
        assertThat(props.getFilterValue()).isNull();
    }

    @Test
    void reporterPropertiesSettersAndGetters() {
        ReporterProperties props = new ConsoleReporterProperties();
        props.setEnabled(true);
        props.setPeriod("30s");
        props.setPrefix("test");
        props.setDurationUnit(java.util.concurrent.TimeUnit.SECONDS);
        props.setRateUnit(java.util.concurrent.TimeUnit.MINUTES);
        props.setLocale("en_US");
        props.setFilterValue("test");
        assertThat(props.getEnabled()).isTrue();
        assertThat(props.getPeriod()).isEqualTo("30s");
        assertThat(props.getPrefix()).isEqualTo("test");
        assertThat(props.getDurationUnit()).isEqualTo(java.util.concurrent.TimeUnit.SECONDS);
        assertThat(props.getRateUnit()).isEqualTo(java.util.concurrent.TimeUnit.MINUTES);
        assertThat(props.getLocale()).isEqualTo("en_US");
        assertThat(props.getFilterValue()).isEqualTo("test");
    }
}
