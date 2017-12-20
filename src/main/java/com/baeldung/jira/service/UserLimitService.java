package com.baeldung.jira.service;

import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.atlassian.jira.rest.client.api.SearchRestClient;
import com.atlassian.jira.rest.client.api.UserRestClient;
import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.api.domain.SearchResult;
import com.atlassian.jira.rest.client.api.domain.User;
import com.atlassian.util.concurrent.Promise;

@Service("userLimitAndNotifyService")
@PropertySource({ "classpath:jqlquery.properties" })
public class UserLimitService {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    private Environment env;

    @Autowired
    private SearchRestClient searchRestClient;

    @Autowired
    private UserRestClient userRestClient;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    @Qualifier("userThreadsLimit")
    private HashMap<String, Double> userThreadsLimit;

    @Autowired
    @Qualifier("statusRating")
    private HashMap<String, Double> statusRating;

    public void notifyUsers() throws InterruptedException, ExecutionException {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("jqlquery");
        for (String jqlQuery : resourceBundle.keySet()) {
            Promise<SearchResult> searchResults = searchByJQL(env.getProperty(jqlQuery));
            double openThreads = 0;
            String jiraUser = "";
            String userEmail = "";
            for (Issue issue : searchResults.get().getIssues()) {
                issue.getAssignee();
                String issueStatus = issue.getStatus().getName();
                openThreads += statusRating.get(issueStatus);
                /**
                 * TODO , Need to check whether assingnee might be null for any issue?
                 * in case should I consider assingee as ADMIN?
                 */
                jiraUser = issue.getAssignee().getName();
                userEmail = issue.getAssignee().getEmailAddress();
                LOGGER.info(issue.getKey() + " assinned to " + issue.getAssignee().getName() + "  in state " + issueStatus);
            }

            LOGGER.info("Total points to Assaigned user " + jiraUser + " is " + openThreads + "   and His limt is : " + userThreadsLimit.get(jiraUser));
            if (openThreads > userThreadsLimit.get(jiraUser)) {
                notificationService.notifyUser(userEmail, jiraUser, getRestUser("admin").getEmailAddress());
            }
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
