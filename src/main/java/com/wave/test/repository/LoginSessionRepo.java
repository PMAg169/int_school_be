package com.wave.test.repository;

import com.wave.test.model.tables.LoginSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * author: PHONE MYINT AUNG
 * contact: +959963213600
 * email: yahiko169@gmail.com
 * */

@Repository()
public interface LoginSessionRepo extends JpaRepository<LoginSession, String> {
}