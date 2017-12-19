package com.baeldung.jira.service;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.SearchRestClient;
import com.atlassian.jira.rest.client.api.UserRestClient;
import com.atlassian.jira.rest.client.api.domain.SearchResult;
import com.atlassian.jira.rest.client.api.domain.User;
import com.atlassian.util.concurrent.Promise;
import com.baeldung.jira.persistance.repository.UserLimitRepository;

@Service("userLimitAndNotifyService")
@PropertySource({ "classpath:jqlquery.properties" })
public class UserLimitService {

    @Autowired
    Environment env;

    @Autowired
    JiraRestClient jiraRestClient;

    @Autowired
    SearchRestClient searchRestClient;

    @Autowired
    UserRestClient userRestClient;

    @Autowired
    UserLimitRepository userLimitRepository;

    public void notifyUsers() throws InterruptedException, ExecutionException {

       /**
        * TODO
        * Read all JQL queries 
        * for each query compute the rating (get ratings from StatusRating table)
        * based on the user get his limit and compare.
        * notify if it exceeds
        */
        System.out.println(env.getProperty("jql.assaigned.admin"));
        searchByJQL(env.getProperty("jql.assaigned.admin"));


    }

    public void searchByJQL(String jql) throws InterruptedException, ExecutionException {

        Promise<SearchResult> searchResults = searchRestClient.searchJql(jql);
        System.out.println("search results" + searchResults.get()
            .getTotal());

    }

    public User getRestUser(String userName) {

        Promise<User> restUser = userRestClient.getUser(userName);

        return restUser.claim();

    }

}
