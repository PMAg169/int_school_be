package com.wave.test.service;

import com.wave.test.model.Response;
import com.wave.test.model.request.NewClass;
import com.wave.test.model.request.NewSubject;
import com.wave.test.model.tables.LoginSession;
import com.wave.test.model.tables.Subject;
import com.wave.test.repository.SubjectRepo;
import com.wave.test.utils.Mapper;
import com.wave.test.utils.Utils;
import org.apache.juli.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * author: PHONE MYINT AUNG
 * contact: +959963213600
 * email: yahiko169@gmail.com
 * */
@Service
public class SubjectService {
    private static final Logger log = LoggerFactory.getLogger(SubjectService.class);

    @Autowired
    private SubjectRepo subjectRepo;

    @Value("${config.usertype.teacher}")
    private String configTeacher;

    public Response add(LoginSession session, NewSubject request) {
        Response response = new Response();
        try {
            if(request.getSubject() == null || request.getSubject().isEmpty()) {
                response.setMessage("Subject cannot be blank");
                return response;
            }
            Subject subject = new Subject();
            subject.setSubjectName(request.getSubject());
            subject.setCreatedBy(session.getUser().getEmail());
            subject.setCreatedOn(Utils.getDateTime());
            subject = this.subjectRepo.save(subject);
            response.setStatus(Utils.success);
            return response;
        } catch (Exception e) {
            log.error(e.toString());
            response.setMessage("INTERNAL SERVER ERROR");
        }
        return response;
    }

    public Response edit(LoginSession session, NewSubject request) {
        Response response = new Response();
        try {
            Optional<Subject> subjectOptional = this.subjectRepo.findById(request.getId());

            if(!subjectOptional.isPresent()) {
                response.setMessage("Subject not found");
                return response;
            }
            if(request.getSubject() == null || request.getSubject().isEmpty()) {
                response.setMessage("Subject cannot be blank");
                return response;
            }
            Subject subject = subjectOptional.get();
            subject.setSubjectName(request.getSubject());
            subject.setUpdatedBy(session.getUser().getEmail());
            subject.setUpdatedOn(Utils.getDateTime());
            subject = this.subjectRepo.save(subject);
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
            response.setData(Mapper.subjectList(this.subjectRepo.findAll(),showDetail));
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
            Optional<Subject> subjectOptional = this.subjectRepo.findById(id);
            if(!subjectOptional.isPresent()) {
                response.setMessage("Subject not found");
                return response;
            }
            boolean showDetail = (session != null) && (session.getUser().getType().equals(this.configTeacher));
            response.setData(Mapper.subject(subjectOptional.get(), showDetail));
            response.setStatus(Utils.success);
        } catch (Exception e) {
            log.error(e.toString());
            response.setMessage("INTERNAL SERVER ERROR");
        }
        return response;
    }

}
