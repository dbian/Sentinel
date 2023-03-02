package com.alibaba.csp.sentinel.dashboard.datasource.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface MetricsRepo extends JpaRepository<MetricEntity, Long> {
    // get resource names by app name
    List<String> findDistinctResourceByApp(String app);

    // get metric entities by app name and resource name,
    // between start timestamp and end timestamp
    List<MetricEntity> findByAppAndResourceAndTimestampBetween(String app, String resource, Date startTime, Date endTime);

    // delete metric timestamp before timestamp
    void deleteByTimestampBefore(Date timestamp);
}
