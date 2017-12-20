package com.baeldung.jira;

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
import com.baeldung.jira.service.UserLimitService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = { JiraAutomationApplication.class })
public class JiraAutomationTest {

    @Autowired
    private UserLimitService userLimitService;

    @Autowired
    private MyJiraClient myJiraClient;

    @Test
    public void contextLoads() {
        Assert.assertNotNull(userLimitService);
        Assert.assertNotNull(myJiraClient);
    }

    @Test
    public void loadJiraUserTest() throws InterruptedException, ExecutionException {
        User jiraUser = userLimitService.getRestUser("rajaneeshm");
        Assert.assertEquals(jiraUser.getEmailAddress(), "rajaneesh72@gmail.com");
        userLimitService.notifyUsers();
    }

}
