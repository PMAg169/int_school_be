package com.wave.test.service;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.JsonObject;
import com.wave.test.model.tables.*;
import com.wave.test.repository.*;
import com.wave.test.utils.Mapper;
import com.wave.test.utils.Utils;
import jdk.jshell.execution.Util;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * author: PHONE MYINT AUNG
 * contact: +959963213600
 * email: yahiko169@gmail.com
 * */

@Service
public class InitiateService {
    private static final Logger log = LoggerFactory.getLogger(InitiateService.class);

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private TeachingClassRepo teachingClassRepo;

    @Autowired
    private SubjectRepo subjectRepo;

    @Autowired
    private ClassSubjectRepo classSubjectRepo;

    @Autowired
    private ClassUserRepo classUserRepo;

    @Value("${config.usertype.teacher}")
    private String configTeacher;

    @Autowired
    private RestTemplate restTemplate;

    public void initialize() {
        try {
            log.info("Checking user list");
            List<User> teachers = this.userRepo.getByType(this.configTeacher);
            User teacher = null;
            if(teachers.size() == 0) {
                log.info("Teacher not found. Creating new teacher user");
                teacher = new User();
                teacher.setEmail("phonemyintaung@pma.com");
                teacher.setPassword("12345");
                teacher.setType(this.configTeacher);
                teacher = this.userRepo.save(teacher);
            } else {
                log.info("Teacher found in db");
                teacher = teachers.get(0);
            }
            log.info(Mapper.user(teacher).toString());
            log.info("Checking class list");
            TeachingClass teachingClass = null;
            if(this.teachingClassRepo.count() == 0) {
                log.info("Class Not found. Creating new Class");
                teachingClass = new TeachingClass();
                teachingClass.setClassName("Initialize Class - A");
                teachingClass.setCreatedBy(teacher.getEmail());
                teachingClass.setCreatedOn(Utils.getDateTime());
                teachingClass = this.teachingClassRepo.save(teachingClass);
                log.info(Mapper.teachingClass(teachingClass, true).toString());
            } else {
                teachingClass = this.teachingClassRepo.findAll().get(0);
                log.info(Mapper.teachingClass(teachingClass, true).toString());
            }
            log.info("Checking Teacher is in class");
            List<ClassUser> classUserList = this.classUserRepo.getByClassAndUser(teachingClass.getId(), teacher.getId());
            ClassUser classUser = null;
            if(classUserList.size() == 0) {
                log.info("Adding teacher to class");
                classUser = new ClassUser();
                classUser.setTeachingClass(teachingClass);
                classUser.setUser(teacher);
                classUser.setCreatedBy(teacher.getEmail());
                classUser.setCreatedOn(Utils.getDateTime());
                classUser = this.classUserRepo.save(classUser);
            } else {
                classUser = classUserList.get(0);
            }
            log.info("Checking Subject list");
            List<Subject> subjectList = this.subjectRepo.findAll();
            if(subjectList.size() == 0) {
                log.info("Subject not found. Creating new Subject");
                Subject subject = new Subject();
                subject.setSubjectName("Maths");
                subject.setCreatedOn(Utils.getDateTime());
                subject.setCreatedBy(teacher.getEmail());
                subjectList.add(this.subjectRepo.save(subject));
                Subject subject2 = new Subject();
                subject2.setSubjectName("Panio");
                subject2.setCreatedOn(Utils.getDateTime());
                subject2.setCreatedBy(teacher.getEmail());
                subjectList.add(this.subjectRepo.save(subject2));
                Subject subject3 = new Subject();
                subject3.setSubjectName("Programming");
                subject3.setCreatedOn(Utils.getDateTime());
                subject3.setCreatedBy(teacher.getEmail());
                subjectList.add(this.subjectRepo.save(subject3));
                Subject subject4 = new Subject();
                subject4.setSubjectName("Social Science");
                subject4.setCreatedOn(Utils.getDateTime());
                subject4.setCreatedBy(teacher.getEmail());
                subjectList.add(this.subjectRepo.save(subject4));
                for(Subject sub : subjectList) {
                    log.info("Checking subject " + Mapper.subject(sub, true).toString());
                    if(this.classSubjectRepo.getByClassAndSubject(teachingClass.getId(), sub.getId()).size() == 0) {
                        log.info("Subject not in class. Initiating saving process");
                        ClassSubject classSubject = new ClassSubject();
                        classSubject.setSubject(sub);
                        classSubject.setTeachingClass(teachingClass);
                        classSubject.setCreatedOn(Utils.getDateTime());
                        classSubject.setCreatedBy(teacher.getEmail());
                        classSubject = this.classSubjectRepo.save(classSubject);
                    }
                }
            }

        } catch (Exception e) {
            log.error(e.toString());
        }

    }

    public void syncExternalData() {
        try {
            ResponseEntity<String> response = this.restTemplate.getForEntity("http://getexternalstudent.com?fromSchool=phonemyintaung", String.class);
            if(!response.getStatusCode().equals(200)) {
                log.error("Error in syncing data");
                return;
            }

            String body = response.getBody();
            JSONArray jsonArray = new JSONArray(body);
            for(int i=0; i<jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

            }
        } catch (Exception e) {
            log.error(e.toString());
        }

    }
}
