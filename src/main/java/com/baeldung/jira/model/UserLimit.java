package com.baeldung.jira.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "User_Limit")
public class UserLimit {

    @Id
    long id;

    String userName;

    double userLimit;
}
