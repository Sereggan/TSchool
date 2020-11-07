package org.tsystems.tschool.jms;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by Sergey Nikolaychuk on 31.10.2020
 */

@Service
@Slf4j
public class JmsProducer {

    private final JmsTemplate jmsTemplate;

    private static final String TOPIC_NAME = "articleTopic";

    public JmsProducer(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void sendMessage(String message) {
        try {
            log.info("Attempting Send message to Topic: " + TOPIC_NAME);
            jmsTemplate.convertAndSend(TOPIC_NAME, message);
        } catch (Exception e) {
            log.error("Received Exception while sending Message: ", e);
        }
    }
}