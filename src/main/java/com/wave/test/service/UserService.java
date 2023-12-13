package com.wave.test.service;

import com.google.gson.JsonObject;
import com.wave.test.model.Response;
import com.wave.test.model.request.Login;
import com.wave.test.model.request.Register;
import com.wave.test.model.tables.LoginSession;
import com.wave.test.model.tables.User;
import com.wave.test.repository.LoginSessionRepo;
import com.wave.test.repository.UserRepo;
import com.wave.test.utils.Mapper;
import com.wave.test.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.security.cert.CertSelector;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * author: PHONE MYINT AUNG
 * contact: +959963213600
 * email: yahiko169@gmail.com
 * */
@Service
public class UserService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private LoginSessionRepo loginSessionRepo;

    @Value("${config.usertype.teacher}")
    private String configTeacher;
    @Value("${config.usertype.student.internal}")
    private String configStudentInternal;
    @Value("${config.usertype.student.external}")
    private String configStudentExternal;

    public Response list(LoginSession session) {
        Response response = new Response();
        try {
            if(!session.getUser().getType().equals(this.configTeacher)) {
                response.setMessage("Only teachers can access this feature");
                return response;
            }
            List<User> userList = this.userRepo.findAll();
            response.setData(Mapper.userList(userList));
            response.setStatus(Utils.success);
        } catch (Exception e) {
            response.setMessage("INTERNAL SERVER ERROR");
        }
        return response;
    }

    public Response register(LoginSession session, Register request) {
        Response response = new Response();
        try {
            if(!session.getUser().getType().equals(this.configTeacher)) {
                response.setMessage("Permission not allowed");
                return response;
            }
            if(request.getEmail() == null || request.getPassword() == null || request.getType() == null) {
                response.setMessage("Null value detected");
                return response;
            }
            if(!request.getType().equals(this.configTeacher) && !request.getType().equals(this.configStudentExternal) &&
                !request.getType().equals(this.configStudentInternal)) {
                response.setMessage("Invalid type in request body");
                return response;
            }
            if(request.getPassword().length() < 5) {
                response.setMessage("Password must be at least 5 chars");
                return response;
            }
            User existingUser = this.userRepo.getByEmail(request.getEmail());
            if(existingUser != null) {
                response.setMessage("Email already registered");
                return response;
            }
            User user = new User();
            user.setEmail(request.getEmail());
            user.setPassword(Utils.encryptPassword(request.getPassword()));
            user.setType(request.getType());
            user = this.userRepo.save(user);
            response.setStatus(Utils.success);
        } catch (Exception e) {
            log.error(e.toString());
            response.setMessage("INTERNAL SERVER ERROR");
        }
        return response;
    }

    public Response login(Login request) {
        Response response = new Response();
        try {
            if(request.getEmail() == null || request.getPassword() == null) {
                response.setMessage("Null value in request body");
                return response;
            }
            User user = this.userRepo.getByEmail(request.getEmail());
            if(user == null || !user.getPassword().equals(request.getPassword())) {
                response.setMessage("Invalid Credentials");
                return response;
            }
            String key = "";
            boolean valid = false;
            int loop = 0;
            int maxLoop = 10;
            while (!valid) {
                key = Utils.generateRandomString(20);
                Optional<LoginSession> loginSessionOptional = this.loginSessionRepo.findById(key);
                if(!loginSessionOptional.isPresent()) {
                    valid = true;
                }
                loop++;
                if(loop > maxLoop) {
                    response.setMessage("Error in generating login key. Please try again later");
                    return response;
                }
            }
            LoginSession newSession = new LoginSession();
            newSession.setSession(key);
            newSession.setUser(user);
            newSession.setLoginTime(Utils.getDateTime());
            newSession.setActive(true);
            newSession = this.loginSessionRepo.save(newSession);
            response.setData(Mapper.session(newSession));
            response.setStatus(Utils.success);
        } catch (Exception e) {
            log.error(e.toString());
            response.setMessage("INTERNAL SERVER ERROR");
        }
        return response;
    }

    public Response logout(LoginSession session) {
        Response response = new Response();
        try {
            session.setActive(false);
            session.setLogoutTime(Utils.getDateTime());
            session = this.loginSessionRepo.save(session);
            response.setStatus(Utils.success);
        } catch (Exception e) {
            log.error(e.toString());
            response.setMessage("INTERNAL SERVER ERROR");
        }
        return response;
    }

    public Response profile(LoginSession session) {
        Response response = new Response();
        try {
            response.setData(Mapper.session(session));
            response.setStatus(Utils.success);
        } catch (Exception e) {
            log.error(e.toString());
            response.setMessage("INTERNAL SERVER ERROR");
        }
        return response;
    }

    public Response activeLogin(LoginSession session) {
        Response response = new Response();
        try {
            List<LoginSession> loginSessions = this.loginSessionRepo.findByStatus(session.getUser().getId(), true);
            response.setData(Mapper.sessionList(loginSessions));
            response.setStatus(Utils.success);
        } catch (Exception e) {
            log.error(e.toString());
            response.setMessage("INTERNAL SERVER ERROR");
        }
        return response;
    }

    public Response endLogin(LoginSession session, String key) {
        Response response = new Response();
        try {
            Optional<LoginSession> loginSessionOptional = this.loginSessionRepo.findById(key);
            if(!loginSessionOptional.isPresent()) {
                response.setMessage("Session not found");
                return response;
            }
            LoginSession targetSession = loginSessionOptional.get();
            if(targetSession.getUser().getId() != session.getUser().getId()) {
                response.setMessage("Target session invalid");
                return response;
            }
            if(!targetSession.isActive()) {
                response.setMessage("Session has already been terminated");
                return response;
            }
            targetSession.setActive(false);
            targetSession= this.loginSessionRepo.save(targetSession);
            response.setStatus(Utils.success);
        } catch (Exception e) {
            log.error(e.toString());
            response.setMessage("INTERNAL SERVER ERROR");
        }
        return response;
    }
}
