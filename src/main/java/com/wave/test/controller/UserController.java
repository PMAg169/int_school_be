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
        LoginSession session = this.authService.authenticate();
        if(session == null) return Utils.noAuthReaponse(log);
        return Utils.response(this.userService.login(request), log);
    }

    @GetMapping(value = "/logout", produces = "application/json")
    public ResponseEntity<?> logout() {
        LoginSession session = this.authService.authenticate();
        if(session == null) return Utils.noAuthReaponse(log);
        return Utils.response(this.userService.logout(session), log);
    }

    @GetMapping(value = "/list", produces = "application/json")
    public ResponseEntity<?> list() {
        LoginSession session = this.authService.authenticate();
        if(session == null) return Utils.noAuthReaponse(log);
        return Utils.response(this.userService.list(session), log);
    }

}
