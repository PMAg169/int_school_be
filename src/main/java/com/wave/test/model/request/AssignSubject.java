package com.wave.test.model.request;

import java.util.List;

/**
 * author: PHONE MYINT AUNG
 * contact: +959963213600
 * email: yahiko169@gmail.com
 * */
public class AssignSubject {
    private Long classId;
    private List<Long> subjectIdList;

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public List<Long> getSubjectIdList() {
        return subjectIdList;
    }

    public void setSubjectIdList(List<Long> subjectIdList) {
        this.subjectIdList = subjectIdList;
    }
}
