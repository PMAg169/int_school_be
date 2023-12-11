package com.wave.test.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.wave.test.model.request.Login;
import com.wave.test.model.tables.LoginSession;
import com.wave.test.model.tables.User;
import org.apache.juli.logging.Log;
import org.hibernate.Session;

import java.util.List;

/**
 * author: PHONE MYINT AUNG
 * contact: +959963213600
 * email: yahiko169@gmail.com
 * */

public class Mapper {

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

    public static JsonObject session(LoginSession session) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("session", session.getSession());;
        jsonObject.addProperty("loginTime", session.getLoginTime());
        jsonObject.add("user", user(session.getUser()));
        return jsonObject;
    }

    public static JsonObject user(User user) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", user.getId());
        jsonObject.addProperty("email", user.getEmail());
        jsonObject.addProperty("type", user.getType());
        return jsonObject;
    }

}

