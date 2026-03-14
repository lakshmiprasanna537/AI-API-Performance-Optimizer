package com.ai_performance_optimizer.controller;

import com.ai_performance_optimizer.service.MetricsAnalyticsService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/metrics")
public class MetricsController {

    private final MetricsAnalyticsService service;

    public MetricsController(MetricsAnalyticsService service) {
        this.service = service;
    }

    @GetMapping("/slow")
    public Map<String, Double> getSlowApis() {

        return service.findSlowApis();
    }
}