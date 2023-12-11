package com.wave.test.repository;


import com.wave.test.model.tables.User;
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
public interface UserRepo extends JpaRepository<User, Long> {

    @Query(value = "SELECT u FROM User where u.email = :email")
    User getByEmail(@Param("email") String email);

    @Query(value = "SELECT u FROM User where u.type = :type")
    List<User> getByType(@Param("type") String type);
}
