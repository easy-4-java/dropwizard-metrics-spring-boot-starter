package com.codahale.metrics.spring.boot.ext.health;

import com.codahale.metrics.health.HealthCheck;

/**
 * <p>Auto-configuration for DatabaseHealthCheck.</p>
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
public class DatabaseHealthCheck extends HealthCheck {

	private final Database database;

	public DatabaseHealthCheck(Database database) {
		this.database = database;
	}

	@Override
	/**
	 * <p>Check.</p>
	 * @return the result
	 */
	protected Result check() throws Exception {
		if (database.ping()) {
			return Result.healthy();
		}
		return Result.unhealthy("Can't ping database");
	}

	/**
	 * <p>Auto-configuration for Database.</p>
	 * @author <a href="https://github.com/loong10k">Loong Wan</a>
	 * @since 1.0.0
	 */
	public static interface Database {

		boolean ping();

	}

}