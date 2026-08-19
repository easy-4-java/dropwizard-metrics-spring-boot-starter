package com.codahale.metrics.spring.boot.ext.health;

import java.net.HttpURLConnection;
import java.net.URL;

import com.codahale.metrics.health.HealthCheck;
/*
 *	http://blog.csdn.net/paullmq/article/details/9032631
 */
public class HttpServerOnlineCheck extends HealthCheck {

	protected String httpURL;
	
	public HttpServerOnlineCheck() {
	}
	
	public HttpServerOnlineCheck(String httpURL) {
		this.httpURL = httpURL;
	}
	
	@Override
	/**
	 * <p>Check.</p>
	 * @return the result
	 */
	protected Result check() throws Exception {
		try {
			URL url = new URL(getHttpURL());
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			this.onPreHandle(conn);
			conn.connect();
			if (conn.getResponseCode() == 200) {
				return HealthCheck.Result.healthy();
			}
			return HealthCheck.Result.unhealthy(conn.getResponseMessage());
		} catch (Exception error) {
			return HealthCheck.Result.unhealthy(error);
		}
	}
 
	/**
	 * <p>On pre handle.</p>
	 * @param conn
	 */
	protected void onPreHandle(HttpURLConnection conn) throws Exception {
		conn.setConnectTimeout(5000);
		conn.setReadTimeout(3000);
		conn.setRequestMethod("GET");
		// 设置编码
		conn.setRequestProperty("Charset", "UTF-8"); 
	}
	
	/** @return return the http u r l. */
	public String getHttpURL() {
		return httpURL;
	}

	/** @param httpURL set the http u r l. */
	public void setHttpURL(String httpURL) {
		this.httpURL = httpURL;
	}

}
