package com.wave.test.service;

import com.wave.test.model.tables.LoginSession;
import com.wave.test.repository.LoginSessionRepo;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * author: PHONE MYINT AUNG
 * contact: +959963213600
 * email: yahiko169@gmail.com
 * */

@Service
public class AuthService {
    private static final Logger log = LoggerFactory.getLogger(AuthService.class);

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Autowired
    private LoginSessionRepo loginSessionRepo;

    public LoginSession authenticate() {
        LoginSession session = null;
        try {
            String auth = this.httpServletRequest.getHeader("authentication");
            log.info("Auth: " + auth);
            if(auth != null) {
                log.info("Authenticating login data");
                Optional<LoginSession> loginSession = this.loginSessionRepo.findById(auth);
                if(loginSession.isPresent()) {
                    log.info("Login info valid");
                    session = loginSession.get();
                }
            }
        } catch (Exception e) {
            log.error(e.toString());
        }
        return session;
    }
}
