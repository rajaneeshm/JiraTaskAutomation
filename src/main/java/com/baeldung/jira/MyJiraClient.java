package com.baeldung.jira;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.SearchRestClient;
import com.atlassian.jira.rest.client.api.UserRestClient;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;

@Configuration
public class MyJiraClient {

    @Autowired
    Environment env;

    JiraRestClient jiraRestClient;

    @Bean
    public JiraRestClient jiraRestClient() {
        this.jiraRestClient = new AsynchronousJiraRestClientFactory().createWithBasicHttpAuthentication(URI.create(env.getProperty("jira.url")), env.getProperty("jira.username"), env.getProperty("jira.password"));
        return jiraRestClient;
    }

    @Bean
    SearchRestClient searchRestClient() {
        return jiraRestClient.getSearchClient();
    }

    @Bean
    UserRestClient userRestClient() {
        return jiraRestClient.getUserClient();
    }

}
