package com.wave.test.controller;

import com.wave.test.model.request.AssignSubject;
import com.wave.test.model.request.AssignUser;
import com.wave.test.model.tables.LoginSession;
import com.wave.test.service.AssignToClassService;
import com.wave.test.service.AuthService;
import com.wave.test.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private AssignToClassService assignToClassService;

    @GetMapping(value = "/user", produces = "application/json")
    public ResponseEntity<?> getUser(@RequestParam("id") Long id) {
        LoginSession session = this.authService.authenticate();
        if(session == null) return Utils.noAuthReaponse(log);
        return Utils.response(this.assignToClassService.classUser(session, id), log);
    }

    @GetMapping(value = "/subject", produces = "application/json")
    public ResponseEntity<?> getSubject(@RequestParam("id") Long id) {
        LoginSession session = this.authService.authenticate();
        return Utils.response(this.assignToClassService.classSubject(session, id), log);
    }

    @PostMapping(value = "/user", produces = "application/json")
    public ResponseEntity<?> addUser(@RequestBody()AssignUser request) {
        LoginSession session = this.authService.authenticate();
        if(session == null) return Utils.noAuthReaponse(log);
        return Utils.response(this.assignToClassService.addUser(session, request),log);
    }

    @PostMapping(value = "/user", produces = "application/json")
    public ResponseEntity<?> addSubject(@RequestBody() AssignSubject request) {
        LoginSession session = this.authService.authenticate();
        if(session == null) return Utils.noAuthReaponse(log);
        return Utils.response(this.assignToClassService.addSubject(session, request),log);
    }

    @DeleteMapping(value = "/user", produces = "application/json")
    public ResponseEntity<?> deleteUser(@RequestParam("id") Long id) {
        LoginSession session = this.authService.authenticate();
        return Utils.response(this.assignToClassService.removeUser(session, id), log);
    }

    @DeleteMapping(value = "/subject", produces = "application/json")
    public ResponseEntity<?> deleteSubject(@RequestParam("id") Long id) {
        LoginSession session = this.authService.authenticate();
        return Utils.response(this.assignToClassService.removeSubject(session, id), log);
    }

}
