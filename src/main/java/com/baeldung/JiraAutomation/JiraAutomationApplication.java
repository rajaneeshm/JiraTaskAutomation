package com.baeldung.JiraAutomation;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.baeldung.JiraAutomation.service.UserLimitAndNotifyService;

@SpringBootApplication
@EnableJpaRepositories(basePackages={"com.baeldung.JiraAutomation.repositories"})
public class JiraAutomationApplication {
	
	@Autowired
	JiraRestClient jiraRestClient;
	
	@Autowired
	UserLimitAndNotifyService userLimitAndNotifyService;

	public static void main(String[] args) {

		ConfigurableApplicationContext ctx = SpringApplication.run(JiraAutomationApplication.class,	args);

		JiraAutomationApplication application = ctx.getBean(JiraAutomationApplication.class);

		try {
			application.init();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		application.close();
		
	}



	public void init() throws InterruptedException, ExecutionException {
		
		userLimitAndNotifyService.notifyUsers();
    	
	}
	
	
	private void close() {
		try {
			jiraRestClient.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}