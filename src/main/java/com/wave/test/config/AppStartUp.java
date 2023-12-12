package com.wave.test.config;



import com.wave.test.service.InitiateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * author: PHONE MYINT AUNG
 * contact: +959963213600
 * email: yahiko169@gmail.com
 * */
@Component
public class AppStartUp implements ApplicationRunner {

    @Autowired
    private InitiateService initiateService;

    @Override
    public void run(ApplicationArguments args) {
        this.initiateService.initialize();
    }
}
