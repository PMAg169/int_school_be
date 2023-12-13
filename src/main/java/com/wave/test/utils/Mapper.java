package com.wave.test.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.wave.test.model.Response;
import com.wave.test.model.request.Login;
import com.wave.test.model.tables.*;
import org.apache.juli.logging.Log;
import org.hibernate.Session;

import java.util.List;

/**
 * author: PHONE MYINT AUNG
 * contact: +959963213600
 * email: yahiko169@gmail.com
 * */

public class Mapper {

    public static JsonObject response(Response response) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("status", response.getStatus());
        jsonObject.addProperty("message", response.getMessage());
        jsonObject.addProperty("data", (response.getData() == null) ? "" : response.getData().toString());
        return jsonObject;
    }

    public static JsonArray sessionList(List<LoginSession> sessions) {
        JsonArray jsonArray = new JsonArray();
        for(LoginSession session : sessions) {
            jsonArray.add(session(session));
        }
        return jsonArray;
    }

    public static JsonArray userList(List<User> userList) {
        JsonArray jsonArray = new JsonArray();
        for(User user : userList) {
            jsonArray.add(user(user));
        }
        return jsonArray;
    }

    public static JsonArray teachingClassList(List<TeachingClass> teachingClasses, boolean detail) {
        JsonArray jsonArray = new JsonArray();
        for(TeachingClass teachingClass : teachingClasses) {
            jsonArray.add(teachingClass(teachingClass, detail));
        }
        return jsonArray;
    }

    public static JsonArray subjectList(List<Subject> subjects, boolean detail) {
        JsonArray jsonArray = new JsonArray();
        for(Subject subject : subjects) {
            jsonArray.add(subject(subject, detail));
        }
        return jsonArray;
    }

    public static JsonArray classUserList(List<ClassUser> classUsers, boolean detail) {
        JsonArray jsonArray = new JsonArray();
        for(ClassUser classUser : classUsers) {
            jsonArray.add(classUser(classUser, detail));
        }
        return jsonArray;
    }

    public static JsonArray classSubjectList(List<ClassSubject> classSubjectList, boolean detail) {
        JsonArray jsonArray = new JsonArray();
        for(ClassSubject classSubject : classSubjectList) {
            jsonArray.add(classSubject(classSubject, detail));
        }
        return jsonArray;
    }



    public static JsonObject session(LoginSession session) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("session", session.getSession());;
        jsonObject.addProperty("loginTime", session.getLoginTime());
        JsonObject userObj = user(session.getUser());
        userObj.remove("id");
        jsonObject.add("user", userObj);
        return jsonObject;
    }

    public static JsonObject user(User user) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", user.getId());
        jsonObject.addProperty("email", user.getEmail());
        jsonObject.addProperty("type", user.getType());
        return jsonObject;
    }

    public static JsonObject teachingClass(TeachingClass teachingClass, boolean detail) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", teachingClass.getId());
        jsonObject.addProperty("className", teachingClass.getClassName());
        if(detail) {
            jsonObject.addProperty("createdBy", teachingClass.getCreatedBy());
            jsonObject.addProperty("createdOn", teachingClass.getCreatedOn());
            jsonObject.addProperty("updatedOn", teachingClass.getUpdatedBy());
            jsonObject.addProperty("updatedBy", teachingClass.getUpdatedOn());
        }

        return jsonObject;
    }

    public static JsonObject subject(Subject subject, boolean detail) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", subject.getId());
        jsonObject.addProperty("subjectName", subject.getSubjectName());
        if(detail) {
            jsonObject.addProperty("createdBy", subject.getCreatedBy());
            jsonObject.addProperty("createdOn", subject.getCreatedOn());
            jsonObject.addProperty("updatedOn", subject.getUpdatedBy());
            jsonObject.addProperty("updatedBy", subject.getUpdatedOn());
        }

        return jsonObject;
    }

    public static JsonObject classUser(ClassUser classUser, boolean detail) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", classUser.getId());
        jsonObject.add("user", user(classUser.getUser()));
//        jsonObject.add("class", teachingClass(classUser.getTeachingClass(), detail));
        if(detail) {
            jsonObject.addProperty("createdBy", classUser.getCreatedBy());
            jsonObject.addProperty("createdOn", classUser.getCreatedOn());
        }

        return jsonObject;
    }

    public static JsonObject classSubject(ClassSubject classSubject, boolean detail) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", classSubject.getId());
//        jsonObject.add("class", teachingClass(classSubject.getTeachingClass(), detail));
        jsonObject.add("subject", subject(classSubject.getSubject(), detail));
        if(detail) {
            jsonObject.addProperty("createdBy", classSubject.getCreatedBy());
            jsonObject.addProperty("createdOn", classSubject.getCreatedOn());
        }

        return jsonObject;
    }

}

