package com.wave.test.model.tables;

import jakarta.persistence.*;

import java.util.List;

/**
 * author: PHONE MYINT AUNG
 * contact: +959963213600
 * email: yahiko169@gmail.com
 * */

@Entity
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String subjectName;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id")
    private List<ClassSubject> classSubjectList;
}
