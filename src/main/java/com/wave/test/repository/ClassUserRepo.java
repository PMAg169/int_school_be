package com.wave.test.repository;

import com.wave.test.model.tables.ClassUser;
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
public interface ClassUserRepo extends JpaRepository<ClassUser, Long> {
    @Query(value = "select * from class_user where class_id=:classId and user_id = :userId", nativeQuery = true)
    List<ClassUser> getByClassAndUser(@Param("classId") Long classId, @Param("userId") Long userId);

    @Query(value = "select * from class_user where class_id=:classId", nativeQuery = true)
    List<ClassUser> getByClass(@Param("classId") Long classId);

}
