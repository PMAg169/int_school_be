package com.wave.test.repository;

import com.wave.test.model.tables.ClassSubject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * author: PHONE MYINT AUNG
 * contact: +959963213600
 * email: yahiko169@gmail.com
 * */
@Repository
public interface ClassSubjectRepo extends JpaRepository<ClassSubject, Long> {
    @Query(value = "select * from class_subject where class_id = :classId and subject_id = :subjectId", nativeQuery = true)
    List<ClassSubject> getByClassAndSubject(@Param("classId") Long classId, @Param("subjectId") Long subjectId);
}
