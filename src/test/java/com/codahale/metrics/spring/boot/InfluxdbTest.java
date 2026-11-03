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

import java.util.concurrent.TimeUnit;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDB.ConsistencyLevel;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.BatchPoints;
import org.influxdb.dto.Point;
import org.influxdb.dto.Query;
import org.junit.Assume;
import org.junit.Test;

/**
 * 
 * @className	： InfluxdbTest
 * @description	：https://github.com/influxdata/influxdb-java
 * @author 		： <a href="https://github.com/hiwepy">hiwepy</a>
 * @date		： 2017年9月12日 下午11:33:12
 * @version 	V1.0
 */
public class InfluxdbTest {
	
	@Test
	public void testName() throws Exception {
		String url = System.getenv("INFLUXDB_URL");
		String user = System.getenv("INFLUXDB_USER");
		String password = System.getenv("INFLUXDB_PASSWORD");
		Assume.assumeTrue("InfluxDB integration test requires connection settings",
				url != null && user != null && password != null);
		InfluxDB influxDB = InfluxDBFactory.connect(url, user, password);
		String dbName = "aTimeSeries";
		influxDB.createDatabase(dbName);

		BatchPoints batchPoints = BatchPoints
						.database(dbName)
						.tag("async", "true")
						.retentionPolicy("autogen")
						.consistency(ConsistencyLevel.ALL)
						.build();
		Point point1 = Point.measurement("cpu")
							.time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
							.addField("idle", 90L)
							.addField("user", 9L)
							.addField("system", 1L)
							.build();
		Point point2 = Point.measurement("disk")
							.time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
							.addField("used", 80L)
							.addField("free", 1L)
							.build();
		batchPoints.point(point1);
		batchPoints.point(point2);
		influxDB.write(batchPoints);
		Query query = new Query("SELECT idle FROM cpu", dbName);
		influxDB.query(query);
		influxDB.deleteDatabase(dbName);
	}
	
}
