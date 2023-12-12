package com.wave.test.controller;

import com.wave.test.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * author: PHONE MYINT AUNG
 * contact: +959963213600
 * email: yahiko169@gmail.com
 * */

@Controller
@RestController("/assign")
public class AssignToClassController {
    private static final Logger log = LoggerFactory.getLogger(AssignToClassController.class);

    @Autowired
    private AuthService authService;

}
