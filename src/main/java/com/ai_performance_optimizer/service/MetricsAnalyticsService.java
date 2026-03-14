package com.ai_performance_optimizer.service;

import com.ai_performance_optimizer.model.ApiMetric;
import com.ai_performance_optimizer.repository.ApiMetricRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MetricsAnalyticsService {

	private final ApiMetricRepository repository;

	public MetricsAnalyticsService(ApiMetricRepository repository) {
		this.repository = repository;
	}

	public Map<String, Double> findSlowApis() {

		List<ApiMetric> metrics = repository.findAll();

		Map<String, Double> avgResponseTime = metrics.stream().collect(
				Collectors.groupingBy(ApiMetric::getApiName, Collectors.averagingDouble(ApiMetric::getResponseTime)));

		return avgResponseTime;
	}

	public void saveMetric(String requestURI, long duration, int status) {

		ApiMetric metric = new ApiMetric();

		metric.setApiName(requestURI);
		metric.setResponseTime(duration);
		metric.setStatusCode(status);
		metric.setTimestamp(LocalDateTime.now());

		repository.save(metric);
	}
}