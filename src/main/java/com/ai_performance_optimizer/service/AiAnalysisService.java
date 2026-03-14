package com.ai_performance_optimizer.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

@Service
public class AiAnalysisService {

    private final WebClient webClient;

    @Value("${OPEN_AI_API_KEY}")
    private String apiKey;

    public AiAnalysisService(WebClient.Builder builder) {
        this.webClient = builder.baseUrl("https://api.openai.com/v1").build();
    }

    public String analyzePerformance(String apiName, double responseTime) {

        try {

            String prompt = "Analyze API performance. API: " + apiName +
                    ", response time: " + responseTime +
                    " ms. Suggest optimization steps.";

            Map response = webClient.post()
                    .uri("/chat/completions")
                    .header("Authorization", "Bearer " + apiKey)
                    .bodyValue(Map.of(
                            "model", "gpt-4.1-mini",
                            "messages", new Object[]{
                                    Map.of("role", "user", "content", prompt)
                            }
                    ))
                    .retrieve()
                    .bodyToMono(Map.class)
                    .block();

            Map choice = (Map) ((List) response.get("choices")).get(0);
            Map message = (Map) choice.get("message");

            return message.get("content").toString();

        } catch (Exception e) {

            // fallback suggestion
            if(responseTime > 800) {
                return "Fallback: API is very slow. Consider caching or DB optimization.";
            }

            if(responseTime > 400) {
                return "Fallback: API latency moderate. Consider pagination or caching.";
            }

            return "Fallback: API performance looks good.";
        }
    }
}