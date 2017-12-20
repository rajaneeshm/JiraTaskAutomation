package com.baeldung.jira.spring;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataProvider {

    @Bean
    @Qualifier("userThreadsLimit")
    public HashMap<String, Double> getUserThreadsLimit() {
        return new HashMap<String, Double>() {
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
    }

    @Bean
    @Qualifier("statusRating")
    public HashMap<String, Double> getStatusRating() {
        return new HashMap<String, Double>() {
            {
                put("In Progress", 1.0);
                put("In Review", 1.0);
                put("Blocked", 0.5);

            }
        };
    }

}
