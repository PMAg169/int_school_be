package com.wave.test.config;

import com.wave.test.service.InitiateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@EnableScheduling
public class Scheduler {
    private final static Logger log = LoggerFactory.getLogger(Scheduler.class);

    @Autowired
    private InitiateService initiateService;

    @Scheduled(cron = "0 0 0 * * *")
    public void runScheduler() {
        log.info("Inside Scheduler");
        this.initiateService.syncExternalData();
    }
}
