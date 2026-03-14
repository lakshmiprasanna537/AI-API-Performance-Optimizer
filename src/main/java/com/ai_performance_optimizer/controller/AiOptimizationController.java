package com.ai_performance_optimizer.controller;

import com.ai_performance_optimizer.service.MetricsAnalyticsService;
import com.ai_performance_optimizer.service.AiAnalysisService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/ai")
public class AiOptimizationController {

    private final MetricsAnalyticsService analyticsService;
    private final AiAnalysisService aiService;

    public AiOptimizationController(MetricsAnalyticsService analyticsService,
                                    AiAnalysisService aiService) {
        this.analyticsService = analyticsService;
        this.aiService = aiService;
    }

    @GetMapping("/analyze")
    public Map<String,String> analyzeApis() {

        Map<String, Double> slowApis = analyticsService.findSlowApis();

        Map<String,String> result = new HashMap<>();

        slowApis.forEach((api,responseTime)->{
            String suggestion = aiService.analyzePerformance(api,responseTime);
            result.put(api,suggestion);
        });

        return result;
    }
}