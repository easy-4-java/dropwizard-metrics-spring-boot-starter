package com.codahale.metrics.spring.boot.factory;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.codahale.metrics.Clock;
import com.codahale.metrics.MetricFilter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.spring.boot.ext.filter.FilterType;
import com.codahale.metrics.spring.boot.ext.filter.PatternFilter;
import com.codahale.metrics.spring.boot.factory.support.MetricPrefixSupplier;
import com.codahale.metrics.spring.boot.property.ReporterProperties;

/**
 * <p>Auto-configuration for AbstractReporterFactoryBean.</p>
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
public abstract class AbstractReporterFactoryBean<T,P extends ReporterProperties> implements FactoryBean<T>, InitializingBean, BeanFactoryAware {

	private BeanFactory beanFactory;
	private ConversionService conversionService;
	
	private MetricRegistry metricRegistry;
	private P properties;
	private T instance;

	private boolean enabled = true;
	private boolean initialized = false;
	
	private Clock clock;
	private MetricFilter metricFilter;
	private MetricPrefixSupplier prefixSupplier;
	

	public AbstractReporterFactoryBean(P properties){
		this.properties = properties;
	}
	
	@Override
	/** @return return the object type. */
	public abstract Class<? extends T> getObjectType();

	@Override
	/** @return return whether singleton is enabled. */
	public boolean isSingleton() {
		return true;
	}

	@Override
	/** @return return the object. */
	public T getObject() {
		if (!this.enabled) {
			return null;
		}
		if (!this.initialized) {
			throw new IllegalStateException("Singleton instance not initialized yet");
		}
		return this.instance;
	}

	@Override
	/**
	 * <p>After properties set.</p>
	 */
	public void afterPropertiesSet() throws Exception {
		this.instance = createInstance(properties);
		this.initialized = true;
	}

	/**
	 * <p>Create instance.</p>
	 * @param properties
	 * @return the result
	 */
	protected abstract T createInstance(P properties) throws Exception;

	/** @return return the properties. */
	public P getProperties() {
		return properties;
	}

	/** @return return the property. */
	protected String getProperty(String value, String defaultValue) {
		if (value == null) {
			return defaultValue;
		}
		return value;
	}
	
	/** @return return the property. */
	public <V> V getProperty(String value, Class<V> requiredType) {
		return getProperty(value, requiredType, null);
	}

	@SuppressWarnings("unchecked")
	/** @return return the property. */
	public <V> V getProperty(String value, Class<V> requiredType, V defaultValue) {
		if (value == null) {
			return defaultValue;
		}
		return (V) getConversionService().convert(value, TypeDescriptor.forObject(value), TypeDescriptor.valueOf(requiredType));
	}

	/** @return return the metric filter. */
	public MetricFilter getMetricFilter() {
		if (!ObjectUtils.isEmpty(metricFilter)) {
			return metricFilter;
		} else if (StringUtils.hasText(properties.getFilterValue())) {
			if(FilterType.PATTERN.compareTo(properties.getFilterType()) == 0) {
				return new PatternFilter(properties.getFilterValue());
			} else if(FilterType.PATTERN.compareTo(properties.getFilterType()) == 0) {
				return new PatternFilter(properties.getFilterValue());
			} else if(FilterType.PATTERN.compareTo(properties.getFilterType()) == 0) {
				return new PatternFilter(properties.getFilterValue());
			}
		}
		return MetricFilter.ALL;
	}

	/** @return return the prefix. */
	public String getPrefix() {
		if (StringUtils.hasText(properties.getPrefix())) {
			return properties.getPrefix();
		}
		else if (!ObjectUtils.isEmpty(prefixSupplier)) { 
			return prefixSupplier.getPrefix();
		}
		return null;
	}

	/** @param metricRegistry set the metric registry. */
	public void setMetricRegistry(final MetricRegistry metricRegistry) {
		this.metricRegistry = metricRegistry;
	}

	/** @return return the metric registry. */
	public MetricRegistry getMetricRegistry() {
		return metricRegistry;
	}

	/** @param enabled set the enabled. */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	/** @return return whether enabled is enabled. */
	public boolean isEnabled() {
		return this.enabled;
	}

	/** @return return the clock. */
	public Clock getClock() {
		return clock;
	}

	/** @param clock set the clock. */
	public void setClock(Clock clock) {
		this.clock = clock;
	}

	/** @return return the prefix supplier. */
	public MetricPrefixSupplier getPrefixSupplier() {
		return prefixSupplier;
	}

	/** @param prefixSupplier set the prefix supplier. */
	public void setPrefixSupplier(MetricPrefixSupplier prefixSupplier) {
		this.prefixSupplier = prefixSupplier;
	}

	/** @param metricFilter set the metric filter. */
	public void setMetricFilter(MetricFilter metricFilter) {
		this.metricFilter = metricFilter;
	}
	
	@Override
	/** @param beanFactory set the bean factory. */
	public void setBeanFactory(final BeanFactory beanFactory) {
		this.beanFactory = beanFactory;
		if (beanFactory instanceof ConfigurableBeanFactory) {
			this.conversionService = ((ConfigurableBeanFactory) beanFactory).getConversionService();
		}
	}

	/** @return return the bean factory. */
	public BeanFactory getBeanFactory() {
		return this.beanFactory;
	}

	/** @return return the conversion service. */
	public ConversionService getConversionService() {
		if (this.conversionService == null) {
			this.conversionService = new DefaultConversionService();
		}
		return this.conversionService;
	}
	

}
