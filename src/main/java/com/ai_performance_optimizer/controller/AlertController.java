package com.ai_performance_optimizer.controller;

import com.ai_performance_optimizer.service.AlertService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AlertController {

    private final AlertService alertService;

    public AlertController(AlertService alertService) {
        this.alertService = alertService;
    }

    @GetMapping("/alerts")
    public String checkAlerts() {
        return alertService.checkSlowApis();
    }
}