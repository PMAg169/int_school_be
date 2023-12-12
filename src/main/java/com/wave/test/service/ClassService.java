package com.wave.test.service;

import com.wave.test.model.Response;
import com.wave.test.model.request.NewClass;
import com.wave.test.model.tables.LoginSession;
import com.wave.test.model.tables.TeachingClass;
import com.wave.test.repository.TeachingClassRepo;
import com.wave.test.utils.Mapper;
import com.wave.test.utils.Utils;
import org.apache.juli.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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

    @Value("${config.usertype.teacher}")
    private String configTeacher;

    public Response add(LoginSession session, NewClass request) {
        Response response = new Response();
        try {
            if(!session.getUser().getType().equals(this.configTeacher)) {
                response.setMessage("Permission not allowed");
                return response;
            }
            if(request.getClassName() == null || request.getClassName().isEmpty()) {
                response.setMessage("Class Name cannot be empty");
                return response;
            }
            TeachingClass teachingClass = new TeachingClass();
            teachingClass.setClassName(request.getClassName());
            teachingClass.setCreatedBy(session.getUser().getEmail());
            teachingClass.setCreatedOn(Utils.getDateTime());
            teachingClass = this.teachingClassRepo.save(teachingClass);
            response.setStatus(Utils.success);
        } catch (Exception e) {
            log.error(e.toString());
            response.setMessage("INTERNAL SERVER ERROR");
        }
        return response;
    }

    public Response edit(LoginSession session, NewClass request) {
        Response response = new Response();
        try {
            if(!session.getUser().getType().equals(this.configTeacher)) {
                response.setMessage("Permission not allowed");
                return response;
            }
            Optional<TeachingClass> teachingClassOptional = this.teachingClassRepo.findById(request.getId());
            if(!teachingClassOptional.isPresent()) {
                response.setMessage("Class not found");
                return response;
            }
            if(request.getClassName() == null || request.getClassName().isEmpty()) {
                response.setMessage("Class Name cannot be empty");
                return response;
            }
            TeachingClass teachingClass = teachingClassOptional.get();
            teachingClass.setClassName(request.getClassName());
            teachingClass.setUpdatedBy(session.getUser().getEmail());
            teachingClass.setUpdatedOn(Utils.getDateTime());
            teachingClass = this.teachingClassRepo.save(teachingClass);
            response.setStatus(Utils.success);
        } catch (Exception e) {
            log.error(e.toString());
            response.setMessage("INTERNAL SERVER ERROR");
        }
        return response;
    }

    public Response list(LoginSession session) {
        Response response = new Response();
        try {
            boolean showDetail = (session != null) && (session.getUser().getType().equals(this.configTeacher));
            response.setData(Mapper.teachingClassList(this.teachingClassRepo.findAll(), showDetail));
            response.setStatus(Utils.success);
        } catch (Exception e) {
            log.error(e.toString());
            response.setMessage("INTERNAL SERVER ERROR");
        }
        return response;
    }

    public Response search(LoginSession session, Long id) {
        Response response = new Response();
        try {
            Optional<TeachingClass> teachingClassOptional = this.teachingClassRepo.findById(id);
            if(!teachingClassOptional.isPresent()) {
                response.setMessage("Class not found");
                return response;
            }
            boolean showDetail = (session != null) && (session.getUser().getType().equals(this.configTeacher));
            response.setData(Mapper.teachingClass(teachingClassOptional.get(), showDetail));
            response.setStatus(Utils.success);
        } catch (Exception e) {
            log.error(e.toString());
            response.setMessage("INTERNAL SERVER ERROR");
        }
        return response;
    }
}
