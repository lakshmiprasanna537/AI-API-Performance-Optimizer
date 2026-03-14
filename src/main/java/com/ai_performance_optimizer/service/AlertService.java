package com.ai_performance_optimizer.service;

import com.ai_performance_optimizer.model.ApiMetric;
import com.ai_performance_optimizer.repository.ApiMetricRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlertService {

    private final ApiMetricRepository repository;

    public AlertService(ApiMetricRepository repository) {
        this.repository = repository;
    }

    public String checkSlowApis() {

        List<ApiMetric> metrics = repository.findAll();

        for(ApiMetric metric : metrics) {

            if(metric.getResponseTime() > 800) {
                return "⚠ ALERT: API " + metric.getApiName() +
                        " is slow (" + metric.getResponseTime() + " ms)";
            }
        }

        return "All APIs performing normally";
    }
}