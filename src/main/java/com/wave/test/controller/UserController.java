package com.wave.test.controller;

import com.wave.test.model.request.Register;
import com.wave.test.service.AuthService;
import com.wave.test.service.UserService;
import com.wave.test.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;
    @PostMapping(value = "/register", produces = "application/json")
    public ResponseEntity<?> register(@RequestBody()Register request) {
        return Utils.response(request, log);
    }

}
