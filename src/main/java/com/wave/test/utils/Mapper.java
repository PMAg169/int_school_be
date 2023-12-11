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
        return jsonObject;
    }

    public static JsonObject user(User user) {
        JsonObject jsonObject = new JsonObject();

        return jsonObject;
    }
    /*public static JsonArray couponList(List<Coupon> couponList) {
        JsonArray jsonArray = new JsonArray();
        for(Coupon c : coupo*List) {
            jsonArray.add(coupon(c));
        }
        return jsonArray;
    }
    public static JsonObject coupon(Coupon coupon) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", coupon.getId());
        jsonObject.addProperty("coupon_name", coupon.getCoupon_name());
        jsonObject.addProperty("company", coupon.getCompany());
        jsonObject.addProperty("count", coupon.getCount());
        jsonObject.addProperty("original", coupon.getOriginal());
        jsonObject.addProperty("discount", coupon.getDiscount());
        jsonObject.addProperty("description", coupon.getDescription());
        jsonObject.addProperty("rules", coupon.getRules());
        jsonObject.addProperty("image", coupon.getImage());
        jsonObject.addProperty("start_date", coupon.getStart_date());
        jsonObject.addProperty("end_date", coupon.getEnd_date());
        return jsonObject;
    }

    public static JsonArray myCouponList(List<MyCoupon> myCouponList) {
        JsonArray jsonArray = new JsonArray();
        for(MyCoupon my : myCouponList) {

        }
        return jsonArray;
    }

    public static JsonObject myCoupon(MyCoupon myCoupon) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", myCoupon.getId());
        jsonObject.add("coupon", coupon(myCoupon.getCoupon()));
        jsonObject.addProperty("date_time", myCoupon.getDate_time());
        jsonObject.addProperty("from_msisdn", myCoupon.getFrom_msisdn());
        jsonObject.addProperty("from", myCoupon.getFrom());
        jsonObject.addProperty("message", myCoupon.getMessage());
        jsonObject.addProperty("gift", myCoupon.isGift());
        jsonObject.addProperty("used", myCoupon.isUsed());
        jsonObject.addProperty("used_time", myCoupon.getUsed_time());
        jsonObject.add("msisdn", msisdn(myCoupon.getMsisdn()));

        return jsonObject;
    }

    public static JsonArray msisdnList(List<Msisdn> msisdnList) {
        JsonArray jsonArray = new JsonArray();
        for(Msisdn m : msisdnList) {
            jsonArray.add(msisdn(m));
        }
        return jsonArray;
    }

    public static JsonObject msisdn(Msisdn msisdn) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("msisdn", msisdn.getMsisdn());
        jsonObject.addProperty("name", msisdn.getName());
        jsonObject.addProperty("imerchant", msisdn.isMerchant());
        return jsonObject;
    }*/
}

