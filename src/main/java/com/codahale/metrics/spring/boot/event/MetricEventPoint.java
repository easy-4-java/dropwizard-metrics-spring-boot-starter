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
package com.codahale.metrics.spring.boot.event;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.codahale.metrics.spring.boot.utils.SystemClock;

/**
 * <p>Auto-configuration for MetricEventPoint.</p>
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
public class MetricEventPoint {
	
	public static final MetricEventPoint ROOT = new MetricEventPoint("root" , "Event Source");
	
	/**
	 * 上一个事件
	 */
	protected MetricEventPoint prev;
	/**
	 * 当前事件UID
	 */
	protected String uid;
	/**
	 * 度量名称
	 */
	protected String name;
	/**
	 * 当前事件发生时间
	 */
	protected long timestamp;
	/**
	 * 当前事件消息
	 */
	protected String message;
	/**
	 * 当前峰值
	 */
	protected Long value;
	/**
	 * 当前事件绑定数据
	 */
	protected Map<String, Object> data;
	
	public MetricEventPoint(String name, String message) {
		this(null, name, message);
	}
	
	public MetricEventPoint(String name, String message, Long value) {
		this(null, name, message);
		this.value = value;
	}
	
	public MetricEventPoint(MetricEventPoint prev, String name, String message) {
		this(prev, name, SystemClock.now(), message);
	}
	
	public MetricEventPoint(String name, long timestamp, String message) {
		this(null, name, timestamp, message);
	}
	
	public MetricEventPoint(MetricEventPoint prev, String name, long timestamp, String message) {
		this(prev, name, timestamp, message, null);
	}
	
	public MetricEventPoint(String name, String message, Map<String, Object> data) {
		this(null, name, message, data);
	}
	
	public MetricEventPoint(MetricEventPoint prev, String name, String message, Map<String, Object> data) {
		this(prev, name, SystemClock.now(), message, data);
	}
	
	public MetricEventPoint(String name, long timestamp, String message, Map<String, Object> data) {
		this(null, name, SystemClock.now(), message, data);
	}
	
	public MetricEventPoint(MetricEventPoint prev, String name, long timestamp, String message, Map<String, Object> data) {
		this.prev = prev;
		this.name = name;
		this.uid = UUID.randomUUID().toString();
		this.timestamp = timestamp;
		this.message = message;
		this.data = data == null ? new HashMap<String, Object>() : data;
	}

	/** @return return the prev. */
	public MetricEventPoint getPrev() {
		return prev;
	}

	/** @param prev set the prev. */
	public void setPrev(MetricEventPoint prev) {
		this.prev = prev;
	}

	/** @return return the uid. */
	public String getUid() {
		return uid;
	}

	/** @param uid set the uid. */
	public void setUid(String uid) {
		this.uid = uid;
	}
	
	/** @return return the name. */
	public String getName() {
		return name;
	}

	/** @param name set the name. */
	public void setName(String name) {
		this.name = name;
	}

	/** @return return the timestamp. */
	public long getTimestamp() {
		return timestamp;
	}

	/** @param timestamp set the timestamp. */
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	/** @return return the message. */
	public String getMessage() {
		return message;
	}

	/** @param message set the message. */
	public void setMessage(String message) {
		this.message = message;
	}

	/** @return return the value. */
	public Long getValue() {
		return value;
	}

	/** @param value set the value. */
	public void setValue(Long value) {
		this.value = value;
	}
	
	/** @return return the data. */
	public Map<String, Object> getData() {
		return data;
	}

	/** @param data set the data. */
	public void setData(Map<String, Object> data) {
		this.data = data;
	}
	
	/**
	 * <p>Put.</p>
	 * @param key
	 * @param value
	 */
	public void put(String key, Object value) {
		getData().put(key, value);
	}
	
}
