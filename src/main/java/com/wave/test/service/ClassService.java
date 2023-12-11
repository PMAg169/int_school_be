package com.wave.test.service;

import com.wave.test.model.Response;
import com.wave.test.model.request.NewClass;
import com.wave.test.model.tables.LoginSession;
import com.wave.test.repository.TeachingClassRepo;
import org.apache.juli.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ResourceBundle;

/**
 * author: PHONE MYINT AUNG
 * contact: +959963213600
 * email: yahiko169@gmail.com
 * */
@Service
public class ClassService {
    private static final Logger log = LoggerFactory.getLogger(ClassService.class);

    @Autowired
    private TeachingClassRepo teachingClassRepo;

    public Response add(LoginSession session, NewClass request) {
        Response response = new Response();
        try {

        } catch (Exception e) {
            response.setMessage("INTERNAL SERVER ERROR");
        }
        return response;
    }

    public Response edit(LoginSession session, NewClass request) {
        Response response = new Response();
        try {

        } catch (Exception e) {
            response.setMessage("INTERNAL SERVER ERROR");
        }
        return response;
    }

    public Response list(LoginSession session) {
        Response response = new Response();
        try {

        } catch (Exception e) {
            response.setMessage("INTERNAL SERVER ERROR");
        }
        return response;
    }

    public Response search(Long id) {
        Response response = new Response();
        try {

        } catch (Exception e) {
            response.setMessage("INTERNAL SERVER ERROR");
        }
        return response;
    }
}
