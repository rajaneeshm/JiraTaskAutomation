package com.baeldung.JiraAutomation;

import java.util.concurrent.ExecutionException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.atlassian.jira.rest.client.api.domain.User;
import com.baeldung.jira.JiraAutomationApplication;
import com.baeldung.jira.MyJiraClient;
import com.baeldung.jira.model.TaskStatusRating;
import com.baeldung.jira.persistance.repository.TaskStatusRatingRepository;
import com.baeldung.jira.persistance.repository.UserLimitRepository;
import com.baeldung.jira.service.UserLimitService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = { JiraAutomationApplication.class, UserLimitService.class })
public class JiraAutomationApplicationTests {

    @Autowired
    UserLimitService userLimitService;

    @Autowired
    MyJiraClient myJiraClient;

    @Autowired
    TaskStatusRatingRepository taskStatusRatingRepository;
    
    @Autowired
    UserLimitRepository userLimitRepository;

    @Test
    public void contextLoads() {
        Assert.assertNotNull(userLimitService);
        Assert.assertNotNull(myJiraClient);
    }

    @Test
    public void loadJiraUserTest() {
        User jiraUser = userLimitService.getRestUser("rajaneeshm");
        Assert.assertEquals(jiraUser.getEmailAddress(), "rajaneesh72@gmail.com");
        TaskStatusRating taskStatusRating = taskStatusRatingRepository.findByTaskStage("In Progress");
        Assert.assertEquals("In Progress", taskStatusRating.getTaskStage());
        Assert.assertEquals(1,taskStatusRatingRepository.findAll().size());
        
        userLimitService.getRestUser("rajaneeshm");
        Assert.assertEquals(1,userLimitRepository.findAll().size());
        
        try {
            userLimitService.notifyUsers();
        } catch (InterruptedException | ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
