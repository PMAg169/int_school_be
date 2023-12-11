package com.wave.test.model.tables;

import jakarta.persistence.*;

import java.util.List;


/**
 * author: PHONE MYINT AUNG
 * contact: +959963213600
 * email: yahiko169@gmail.com
 * */

@Entity
public class TeachingClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String className;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_id")
    private List<ClassSubject> classSubjectList;
}
