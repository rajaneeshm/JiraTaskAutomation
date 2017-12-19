package com.baeldung.jira.persistance.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.baeldung.jira.model.UserLimit;

@Repository("userLimitRepository")
public interface UserLimitRepository extends JpaRepository<UserLimit, Serializable> {

}
