package com.ai_performance_optimizer.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/orders")
    public String getOrders() throws InterruptedException {

        Thread.sleep(500); // simulate slow API

        return "Orders fetched successfully";
    }

}