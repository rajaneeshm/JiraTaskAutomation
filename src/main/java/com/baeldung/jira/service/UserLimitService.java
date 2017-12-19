package com.baeldung.jira.service;

import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.SearchRestClient;
import com.atlassian.jira.rest.client.api.UserRestClient;
import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.api.domain.SearchResult;
import com.atlassian.jira.rest.client.api.domain.User;
import com.atlassian.util.concurrent.Promise;

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

    public void notifyUsers() throws InterruptedException, ExecutionException {

        /**
        * TODO
        * Read all JQL queries 
        * for each query compute the rating (get ratings from StatusRating table)
        * based on the user get his limit and compare.
        * notify if it exceeds
        */
        System.out.println(env.getProperty("jql.assaigned.admin"));

        ResourceBundle resourceBundle = ResourceBundle.getBundle("jqlquery");
        for (String jqlQuery : resourceBundle.keySet()) {
            Promise<SearchResult> searchResults = searchByJQL(env.getProperty(jqlQuery));

            for (Issue issue : searchResults.get()
                .getIssues()) {
                String issueStatus = issue.getStatus()
                    .getName();
                System.out.println("Issues assinned to " + issue.getAssignee()
                    .getName() + "  in state " + issueStatus);
            }
            System.out.println();

        }

    }

    public Promise<SearchResult> searchByJQL(String jql) throws InterruptedException, ExecutionException {

        return searchRestClient.searchJql(jql);

    }

    public User getRestUser(String userName) {

        Promise<User> restUser = userRestClient.getUser(userName);

        return restUser.claim();

    }

}
