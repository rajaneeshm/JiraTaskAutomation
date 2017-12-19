package com.baeldung.jira.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserLimit {

    @Id
    long id;

    String userName;

    double userLimit;
}
