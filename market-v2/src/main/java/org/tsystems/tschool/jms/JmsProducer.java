package org.tsystems.tschool.jms;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.tsystems.tschool.dto.ArticleDto;
import org.tsystems.tschool.entity.Article;

import java.util.List;

/**
 * Created by Sergey Nikolaychuk on 31.10.2020
 */

@Service
@Slf4j
public class JmsProducer {

    @Autowired
    JmsTemplate jmsTemplate;

    private String topic = "testTopic";

    public void sendMessage(){
        try{
            log.info("Attempting Send message to Topic: "+ topic);
            jmsTemplate.convertAndSend(topic, "Updated Top");

        } catch(Exception e){
            log.error("Received Exception during send Message: ", e);
        }
    }
}