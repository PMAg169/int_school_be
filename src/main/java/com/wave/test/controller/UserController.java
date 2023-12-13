package com.wave.test.controller;

import com.wave.test.model.request.Login;
import com.wave.test.model.request.Register;
import com.wave.test.model.tables.LoginSession;
import com.wave.test.service.AuthService;
import com.wave.test.service.UserService;
import com.wave.test.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * author: PHONE MYINT AUNG
 * contact: +959963213600
 * email: yahiko169@gmail.com
 * */

@Controller
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;

    @PostMapping(value = "/register", produces = "application/json")
    public ResponseEntity<?> register(@RequestBody()Register request) {
        LoginSession session = this.authService.authenticate();
        if(session == null) return Utils.noAuthReaponse(log);
        return Utils.response(this.userService.register(session, request), log);
    }

    @PostMapping(value = "/login", produces = "application/json")
    public ResponseEntity<?> login(@RequestBody() Login request) {
        return Utils.response(this.userService.login(request), log);
    }

    @GetMapping(value = "/logout", produces = "application/json")
    public ResponseEntity<?> logout() {
        LoginSession session = this.authService.authenticate();
        if(session == null) return Utils.noAuthReaponse(log);
        return Utils.response(this.userService.logout(session), log);
    }

    @GetMapping(value = "/user/list", produces = "application/json")
    public ResponseEntity<?> list() {
        LoginSession session = this.authService.authenticate();
        if(session == null) return Utils.noAuthReaponse(log);
        return Utils.response(this.userService.list(session), log);
    }

    @GetMapping(value = "/profile", produces = "application/json")
    public ResponseEntity<?> profile() {
        LoginSession session = this.authService.authenticate();
        if(session == null) return Utils.noAuthReaponse(log);
        return Utils.response(this.userService.profile(session), log);
    }

    @GetMapping(value = "/activeLogin", produces = "application/json")
    public ResponseEntity<?> activeLogin() {
        LoginSession session = this.authService.authenticate();
        if(session == null) return Utils.noAuthReaponse(log);
        return Utils.response(this.userService.activeLogin(session), log);
    }

    @GetMapping(value = "/endLogin", produces = "application/json")
    public ResponseEntity<?> endLogin(@RequestParam("id") String id) {
        LoginSession session = this.authService.authenticate();
        if(session == null) return Utils.noAuthReaponse(log);
        return Utils.response(this.userService.endLogin(session, id), log);
    }

}
