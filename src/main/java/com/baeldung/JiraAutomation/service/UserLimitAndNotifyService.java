package com.baeldung.JiraAutomation.service;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.SearchRestClient;
import com.atlassian.jira.rest.client.api.UserRestClient;
import com.atlassian.jira.rest.client.api.domain.SearchResult;
import com.atlassian.jira.rest.client.api.domain.User;
import com.atlassian.util.concurrent.Promise;
import com.baeldung.JiraAutomation.repositories.UserLimitRepository;

@Service("userLimitAndNotifyService")
public class UserLimitAndNotifyService {

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
		 * TODO.
		 * Get results from REST Client 
		 * Iterate and get priorities 
		 * based on priorties from H2 get rating and sum for the user and notify it exceeds limit.
		 */
		System.out.println("Total number of Users registered :" + userLimitRepository.findAll().size());

		searchByJQL(env.getProperty("query.assaigned.admin"));

		User user = getRestUser("rajaneeshm");

		System.out.println(user.getName() + "    " + user.getEmailAddress());

		System.out.println("Total number of Users registered :" + userLimitRepository.count());
	}

	public void searchByJQL(String jql) throws InterruptedException, ExecutionException {

		Promise<SearchResult> searchResults = searchRestClient.searchJql(jql);
		System.out.println("search results" + searchResults.get().getTotal());

	}

	public User getRestUser(String userName) {

		Promise<User> restUser = userRestClient.getUser(userName);

		return restUser.claim();

	}

}
