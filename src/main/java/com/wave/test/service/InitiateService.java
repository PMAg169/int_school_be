package com.wave.test.service;

import com.wave.test.repository.ClassSubjectRepo;
import com.wave.test.repository.SubjectRepo;
import com.wave.test.repository.TeachingClassRepo;
import com.wave.test.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * author: PHONE MYINT AUNG
 * contact: +959963213600
 * email: yahiko169@gmail.com
 * */

@Service
public class InitiateService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private TeachingClassRepo teachingClassRepo;

    @Autowired
    private SubjectRepo subjectRepo;

    @Autowired
    private ClassSubjectRepo classSubjectRepo;


    public void initialize() {

    }

    public void syncExternalData() {

    }
}
