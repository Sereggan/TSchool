package org.tsystems.tschool.jms;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;

import javax.annotation.PostConstruct;
import javax.jms.ConnectionFactory;
import java.util.Collections;

/**
 * Created by Sergey Nikolaychuk on 31.10.2020
 */

/**
 * ActiveMq configuration
 */
@Configuration
public class ActiveMQConfig {

    @Value("${spring.activemq.url}")
    private String BROKER_URL;

    @PostConstruct
    @Bean
    public ConnectionFactory connectionFactory() {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
        activeMQConnectionFactory.setTrustedPackages(Collections.singletonList("org.tsysytems.tschool"));
        activeMQConnectionFactory.setBrokerURL(BROKER_URL);
        return activeMQConnectionFactory;
    }

    @Bean
    public JmsTemplate jmsTemplate() {
        JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setConnectionFactory(connectionFactory());
        jmsTemplate.setPubSubDomain(true);  // enable for Pub Sub to topic. Not Required for Queue.
        return jmsTemplate;
    }
}