package com.wave.test.utils;

import com.wave.test.model.Response;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.Random;
import org.slf4j.Logger;

public class Utils {

    public static final String success = "SUCCESS";
    public static final String fail = "FAIL";

    public static String getDateTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    public static String encryptPassword(String password) {
        // Encryption process to be done
        return password;
    }

    public static ResponseEntity<?> response(Response response, Logger log) {
        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.add("Author", "PHONE MYINT AUNG");
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        if(response.getStatus().equals(status)) {
            status = HttpStatus.OK;
        }
        return new ResponseEntity<>(response, responseHeader, status);
    }

    public static String generateRandomString(int length) {
        int leftLimit = 48;
        int rightLimit = 122;
        Random random = new Random();
        String generatedString = random.ints(leftLimit, rightLimit+1).
                filter(i -> (i<=57 || i>=65) && (i <= 90 || i>=97)).limit(length).
                collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
        return generatedString;
    }
}
