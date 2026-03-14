package com.ai_performance_optimizer.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.ai_performance_optimizer.service.MetricsAnalyticsService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class MetricsInterceptor implements HandlerInterceptor {

    private final MetricsAnalyticsService metricsService;
    private long startTime;

    public MetricsInterceptor(MetricsAnalyticsService metricsService) {
        this.metricsService = metricsService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        startTime = System.currentTimeMillis();
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        long duration = System.currentTimeMillis() - startTime;
        String uri = request.getRequestURI();

        
     // Ignore internal APIs
        if(uri.startsWith("/metrics") ||
           uri.startsWith("/alerts") ||
           uri.startsWith("/ai") ||
           uri.startsWith("/actuator")) {
            return;
        }

        metricsService.saveMetric(
                request.getRequestURI(),
                duration,
                response.getStatus()
        );
    }
}