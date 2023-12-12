package com.wave.test.controller;

import com.wave.test.model.request.NewClass;
import com.wave.test.model.request.Register;
import com.wave.test.model.tables.LoginSession;
import com.wave.test.service.AuthService;
import com.wave.test.service.ClassService;
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
@RequestMapping("/class")
public class ClassController {
    private static final Logger log = LoggerFactory.getLogger(ClassController.class);

    @Autowired
    private AuthService authService;

    @Autowired
    private ClassService classService;

    @PostMapping(value = "/add", produces = "application/json")
    public ResponseEntity<?> add(@RequestBody() NewClass request) {
        LoginSession session = this.authService.authenticate();
        if(session == null) return Utils.noAuthReaponse(log);
        return Utils.response(this.classService.add(session, request), log);
    }

    @PostMapping(value = "/edit", produces = "application/json")
    public ResponseEntity<?> edit(@RequestBody() NewClass request) {
        LoginSession session = this.authService.authenticate();
        if(session == null) return Utils.noAuthReaponse(log);
        return Utils.response(this.classService.edit(session, request), log);
    }

    @GetMapping(value = "/list", produces = "application/json")
    public ResponseEntity<?> logout() {
        LoginSession session = this.authService.authenticate();
        return Utils.response(this.classService.list(session), log);
    }

    @GetMapping(value = "/search", produces = "application/json")
    public ResponseEntity<?> search(@RequestParam("id") Long id) {
        LoginSession session = this.authService.authenticate();
        return Utils.response(this.classService.search(session, id), log);
    }
}
