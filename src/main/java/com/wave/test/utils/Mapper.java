package com.wave.test.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.wave.test.model.tables.LoginSession;
import com.wave.test.model.tables.User;

import java.util.List;

/**
 * author: PHONE MYINT AUNG
 * contact: +959963213600
 * email: yahiko169@gmail.com
 * */

public class Mapper {

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

