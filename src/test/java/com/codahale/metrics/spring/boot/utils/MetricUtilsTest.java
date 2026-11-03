package com.codahale.metrics.spring.boot.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.Method;

import org.junit.jupiter.api.Test;

import com.codahale.metrics.annotation.Timed;

class MetricUtilsTest {

    @Test
    void defaultsToClassAndMethodName() throws Exception {
        Method method = Sample.class.getDeclaredMethod("defaultTimer");
        assertEquals(Sample.class.getCanonicalName() + ".defaultTimer",
                MetricUtils.forTimedMethod(Sample.class, method, method.getAnnotation(Timed.class)));
    }

    @Test
    void respectsAbsoluteMetricName() throws Exception {
        Method method = Sample.class.getDeclaredMethod("absoluteTimer");
        assertEquals("custom.timer",
                MetricUtils.forTimedMethod(Sample.class, method, method.getAnnotation(Timed.class)));
    }

    private static class Sample {
        @Timed
        void defaultTimer() {
        }

        @Timed(name = "custom.timer", absolute = true)
        void absoluteTimer() {
        }
    }
}
