package com.wave.test.service;


import com.wave.test.model.Response;
import com.wave.test.model.request.AssignSubject;
import com.wave.test.model.request.AssignUser;
import com.wave.test.model.request.Login;
import com.wave.test.model.tables.*;
import com.wave.test.repository.*;
import com.wave.test.utils.Mapper;
import com.wave.test.utils.Utils;
import org.apache.juli.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * author: PHONE MYINT AUNG
 * contact: +959963213600
 * email: yahiko169@gmail.com
 * */

@Service
public class AssignToClassService {
    private static final Logger log = LoggerFactory.getLogger(AssignToClassService.class);

    @Autowired
    private TeachingClassRepo teachingClassRepo;

    @Autowired
    private SubjectRepo subjectRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ClassUserRepo classUserRepo;

    @Autowired
    private ClassSubjectRepo classSubjectRepo;

    @Value("${config.usertype.teacher}")
    private String configTeacher;

    public Response classSubject(LoginSession session, Long id) {
        Response response = new Response();
        try {
            List<ClassSubject> classSubjectList = this.classSubjectRepo.getByClass(id);
            boolean showDetail = (session != null) && (session.getUser().getType().equals(this.configTeacher));
            response.setData(Mapper.classSubjectList(classSubjectList, showDetail));
            response.setStatus(Utils.success);
        } catch (Exception e) {
            log.error(e.toString());
            response.setMessage("INTERNAL SERVER ERROR");
        }
        return response;
    }

    public Response classUser(LoginSession session, Long id) {
        Response response = new Response();
        try {
            List<ClassUser> classUserList = this.classUserRepo.getByClass(id);
            boolean showDetail = (session != null) && (session.getUser().getType().equals(this.configTeacher));
            response.setData(Mapper.classUserList(classUserList, showDetail));
            response.setStatus(Utils.success);
        } catch (Exception e) {
            log.error(e.toString());
            response.setMessage("INTERNAL SERVER ERROR");
        }
        return response;
    }

    public Response addSubject(LoginSession session, AssignSubject request) {
        Response response = new Response();
        try {
            if(!session.getUser().getType().equals(this.configTeacher)) {
                response.setMessage("Permission not allowed");
                return response;
            }
            Optional<TeachingClass> teachingClassOptional = this.teachingClassRepo.findById(request.getClassId());
            if(!teachingClassOptional.isPresent()) {
                response.setMessage("Class not found");
                return response;
            }
            TeachingClass teachingClass = teachingClassOptional.get();
            List<ClassSubject> subjectList = new ArrayList<>();
            for(Long id : request.getSubjectIdList()) {
                Optional<Subject> subjectOptional = subjectRepo.findById(id);
                if(subjectOptional.isPresent()) {
                    Subject subject = subjectOptional.get();
                    List<ClassSubject> classSubjectList = this.classSubjectRepo.getByClassAndSubject(request.getClassId(), id);
                    if(classSubjectList.size() == 0) {
                        ClassSubject classSubject = new ClassSubject();
                        classSubject.setSubject(subject);
                        classSubject.setTeachingClass(teachingClass);
                        classSubject.setCreatedBy(session.getUser().getEmail());
                        classSubject.setCreatedOn(Utils.getDateTime());
                        subjectList.add(classSubject);
                    }
                }
            }
            subjectList = this.classSubjectRepo.saveAll(subjectList);
            response.setStatus(Utils.success);
        } catch (Exception e) {
            log.error(e.toString());
            response.setMessage("INTERNAL SERVER ERROR");
        }
        return response;
    }

    public Response addUser(LoginSession session, AssignUser request) {
        Response response = new Response();
        try {
            if(!session.getUser().getType().equals(this.configTeacher)) {
                response.setMessage("Permission not allowed");
                return response;
            }
            Optional<TeachingClass> teachingClassOptional = this.teachingClassRepo.findById(request.getClassId());
            if(!teachingClassOptional.isPresent()) {
                response.setMessage("Class not found");
                return response;
            }
            TeachingClass teachingClass = teachingClassOptional.get();
            List<ClassUser> classUserList = new ArrayList<>();
            for(Long id: request.getUserIdList()) {
                Optional<User> userOptional = this.userRepo.findById(id);
                if(userOptional.isPresent()) {
                    User user = userOptional.get();
                    List<ClassUser> classUsers = this.classUserRepo.getByClassAndUser(request.getClassId(), id);
                    if(classUsers.size() == 0) {
                        ClassUser classUser = new ClassUser();
                        classUser.setUser(user);
                        classUser.setTeachingClass(teachingClass);
                        classUser.setCreatedOn(Utils.getDateTime());
                        classUser.setCreatedBy(session.getUser().getEmail());
                        classUserList.add(classUser);
                    }
                }
            }
            classUserList = this.classUserRepo.saveAll(classUserList);
            response.setStatus(Utils.success);
        } catch (Exception e) {
            log.error(e.toString());
            response.setMessage("INTERNAL SERVER ERROR");
        }
        return response;
    }

    public Response removeSubject(LoginSession session, Long id) {
        Response response = new Response();
        try {
            if(!session.getUser().getType().equals(this.configTeacher)) {
                response.setMessage("Permission not allowed");
                return response;
            }
            Optional<ClassSubject> classSubjectOptional = this.classSubjectRepo.findById(id);
            if(!classSubjectOptional.isPresent()) {
                response.setMessage("Subject not found in class");
                return response;
            }
            ClassSubject classSubject = classSubjectOptional.get();
            this.classSubjectRepo.delete(classSubject);
            response.setStatus(Utils.success);
        } catch (Exception e) {
            log.error(e.toString());
            response.setMessage("INTERNAL SERVER ERROR");
        }
        return response;
    }

    public Response removeUser(LoginSession session, Long id) {
        Response response = new Response();
        try {
            if(!session.getUser().getType().equals(this.configTeacher)) {
                response.setMessage("Permission not allowed");
                return response;
            }
            Optional<ClassUser> classUserOptional = this.classUserRepo.findById(id);
            if(!classUserOptional.isPresent()) {
                response.setMessage("User not found in class");
                return response;
            }
            ClassUser classUser = classUserOptional.get();
            this.classUserRepo.delete(classUser);
            response.setStatus(Utils.success);
        } catch (Exception e) {
            log.error(e.toString());
            response.setMessage("INTERNAL SERVER ERROR");
        }
        return response;
    }


}
