package com.baeldung.jira.service;

import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
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

	@Autowired
	private JavaMailSender mailSender;

	HashMap<String, Double> userLimit = new HashMap<String, Double>() {
		{
			put("rajaneeshm", 0.0);
			put("jacek", 1.0);
			put("monica", 2.0);
			put("mariia", 4.0);
			put("grzegorz", 3.0);
			put("haitham", 2.0);
			put("roman", 4.5);
			put("grant", 7.0);
			put("bogdan", 4.0);
			put("darmen", 5.0);
			put("asif", 2.0);
			put("admin", 5.0);

		}
	};

	HashMap<String, Double> statusRating = new HashMap<String, Double>() {
		{
			put("In Progress", 1.0);
			put("In Review", 1.0);
			put("Blocked", 0.5);

		}
	};

	public void notifyUsers() throws InterruptedException, ExecutionException {

		/**
		 * TODO Read all JQL queries for each query compute the rating (get
		 * ratings from StatusRating table) based on the user get his limit and
		 * compare. notify if it exceeds
		 */
		System.out.println(env.getProperty("jql.assaigned.admin"));

		ResourceBundle resourceBundle = ResourceBundle.getBundle("jqlquery");
		for (String jqlQuery : resourceBundle.keySet()) {
			Promise<SearchResult> searchResults = searchByJQL(env.getProperty(jqlQuery));
			double totalpoints = 0;
			String assaignedToUser = "";
			String userEmail = "";
			for (Issue issue : searchResults.get().getIssues()) {
				issue.getAssignee();
				String issueStatus = issue.getStatus().getName();
				totalpoints += statusRating.get(issueStatus);
				assaignedToUser = issue.getAssignee().getName();
				userEmail = issue.getAssignee().getEmailAddress();
				System.out.println(
						issue.getKey() + " assinned to " + issue.getAssignee().getName() + "  in state " + issueStatus);
			}

			System.out.println("Total points to Assaigned user " + assaignedToUser + " is " + totalpoints
					+ "   and His limt is : " + userLimit.get(assaignedToUser));
			if (totalpoints > userLimit.get(assaignedToUser)) {
				// Notify User

				mailSender.send(constructResetTokenEmail(userEmail,assaignedToUser));
			}
		}

	}

	private SimpleMailMessage constructResetTokenEmail(String userEmail,String userName) {
		final String message = "Dear " + userName
				+ " \n Complete your issues as soon as possible we have observed you have more issues";
		return constructEmail("Alert Mail for your JIRA Tasks", message + " \r\n", userName);
	}

	private SimpleMailMessage constructEmail(String subject, String body, String userEmail) {
		final SimpleMailMessage email = new SimpleMailMessage();
		email.setSubject(subject);
		email.setText(body);
		email.setTo("rajaneeshtv@gmail.com");// (userEmail);
		email.setFrom(env.getProperty("support.email"));
		return email;
	}

	public Promise<SearchResult> searchByJQL(String jql) throws InterruptedException, ExecutionException {

		return searchRestClient.searchJql(jql);

	}

	public User getRestUser(String userName) {

		Promise<User> restUser = userRestClient.getUser(userName);

		return restUser.claim();

	}

}
