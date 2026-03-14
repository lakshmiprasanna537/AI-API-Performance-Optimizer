package com.ai_performance_optimizer.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class ApiMetric {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String apiName;

	private long responseTime;

	private int statusCode;

	private LocalDateTime timestamp;

	public ApiMetric() {
	}

	public ApiMetric(String apiName, long responseTime, int statusCode, LocalDateTime timestamp) {
		this.apiName = apiName;
		this.responseTime = responseTime;
		this.statusCode = statusCode;
		this.timestamp = timestamp;
	}

	public Long getId() {
		return id;
	}

	public String getApiName() {
		return apiName;
	}

	public void setApiName(String apiName) {
		this.apiName = apiName;
	}

	public long getResponseTime() {
		return responseTime;
	}

	public void setResponseTime(long responseTime) {
		this.responseTime = responseTime;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
}
