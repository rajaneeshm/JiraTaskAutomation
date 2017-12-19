package com.baeldung.jira.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_limit")
public class UserLimit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    String userName;

    double userLimit;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public double getUserLimit() {
        return userLimit;
    }

    public void setUserLimit(double userLimit) {
        this.userLimit = userLimit;
    }

}
