package com.codahale.metrics.spring.boot;

import static org.assertj.core.api.Assertions.assertThat;

import com.codahale.metrics.spring.boot.utils.MetricUtils;
import com.codahale.metrics.spring.boot.utils.SystemClock;
import com.codahale.metrics.Clock;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * Tests for utility classes.
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 */
class UtilsTest {

    @Test
    void systemClockShouldReturnSingleton() {
        Clock c1 = SystemClock.instance();
        Clock c2 = SystemClock.instance();
        assertThat(c1).isSameAs(c2);
    }

    @Test
    void systemClockShouldReturnPositiveTime() {
        assertThat(SystemClock.instance().getTime()).isPositive();
    }

    @Test
    void metricUtilsChooseNameShouldHandleExplicitAbsoluteName() throws Exception {
        Method method = SampleClass.class.getMethod("timedMethod");
        com.codahale.metrics.annotation.Timed annotation = method.getAnnotation(com.codahale.metrics.annotation.Timed.class);
        String name = MetricUtils.forTimedMethod(SampleClass.class, method, annotation);
        assertThat(name).contains("timedMethod");
    }

    @Test
    void metricUtilsForMeteredMethod() throws Exception {
        Method method = SampleClass.class.getMethod("meteredMethod");
        com.codahale.metrics.annotation.Metered annotation = method.getAnnotation(com.codahale.metrics.annotation.Metered.class);
        String name = MetricUtils.forMeteredMethod(SampleClass.class, method, annotation);
        assertThat(name).contains("meteredMethod");
    }

    @Test
    void metricUtilsForExceptionMeteredMethod() throws Exception {
        Method method = SampleClass.class.getMethod("exceptionMethod");
        com.codahale.metrics.annotation.ExceptionMetered annotation = method.getAnnotation(com.codahale.metrics.annotation.ExceptionMetered.class);
        String name = MetricUtils.forExceptionMeteredMethod(SampleClass.class, method, annotation);
        assertThat(name).contains("exceptionMethod");
    }

    @Test
    void metricUtilsForCountedMethod() throws Exception {
        Method method = SampleClass.class.getMethod("countedMethod");
        com.codahale.metrics.annotation.Counted annotation = method.getAnnotation(com.codahale.metrics.annotation.Counted.class);
        String name = MetricUtils.forCountedMethod(SampleClass.class, method, annotation);
        assertThat(name).contains("countedMethod");
    }

    @Test
    void systemClockNowShouldReturnPositiveTime() {
        assertThat(SystemClock.now()).isPositive();
    }

    @Test
    void systemClockNowDateShouldReturnNonNull() {
        assertThat(SystemClock.nowDate()).isNotNull();
    }

    @Test
    void systemClockGetTickShouldReturnPositive() {
        assertThat(SystemClock.instance().getTick()).isPositive();
    }

    public static class SampleClass {
        @com.codahale.metrics.annotation.Timed
        public void timedMethod() {}

        @com.codahale.metrics.annotation.Metered
        public void meteredMethod() {}

        @com.codahale.metrics.annotation.ExceptionMetered
        public void exceptionMethod() {}

        @com.codahale.metrics.annotation.Counted
        public void countedMethod() {}
    }
}
