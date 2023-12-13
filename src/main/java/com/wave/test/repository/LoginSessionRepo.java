package com.wave.test.repository;

import com.wave.test.model.tables.LoginSession;
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

@Repository()
public interface LoginSessionRepo extends JpaRepository<LoginSession, String> {

    @Query(value = "select * from login_session where user_id=:userId and active = :status", nativeQuery = true)
    List<LoginSession> findByStatus(@Param("userId") Long userId, @Param("status") boolean status);
}
