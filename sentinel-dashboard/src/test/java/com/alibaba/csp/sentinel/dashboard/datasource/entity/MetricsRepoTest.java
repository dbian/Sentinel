package com.alibaba.csp.sentinel.dashboard.datasource.entity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class MetricsRepoTest {

    @Autowired
    MetricsRepo metricsRepo;

    @Test
    void deleteByTimestampBefore() {
        metricsRepo.findAll().forEach(System.out::println);
        // add new metric and test deleteByTimestampBefore
        MetricEntity metricEntity = new MetricEntity();
        metricEntity.setApp("test");
        metricEntity.setResource("test");
        // get date of now
        Date date = new Date();
        metricEntity.setTimestamp(date);
        metricEntity.setRt(10);
        metricsRepo.save(metricEntity);
        metricsRepo.findAll().forEach(System.out::println);
        // delete old data before 180 days
        long time = date.getTime() + 100000;
        Date d1 = new Date(time);
        metricsRepo.deleteByTimestampBefore(d1);
        metricsRepo.findAll().forEach(System.out::println);
        assertEquals(0, metricsRepo.findAll().size());


    }
}