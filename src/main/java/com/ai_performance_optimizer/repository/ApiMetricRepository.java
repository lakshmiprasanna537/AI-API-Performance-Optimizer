package com.ai_performance_optimizer.repository;

import com.ai_performance_optimizer.model.ApiMetric;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApiMetricRepository extends JpaRepository<ApiMetric, Long> {

}