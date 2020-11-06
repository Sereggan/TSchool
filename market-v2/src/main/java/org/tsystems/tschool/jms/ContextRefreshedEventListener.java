package org.tsystems.tschool.jms;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class ContextRefreshedEventListener implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger log = LogManager.getLogger(ContextRefreshedEventListener.class);

    private final JmsProducer jmsProducer;

    public ContextRefreshedEventListener(JmsProducer jmsProducer) {
        this.jmsProducer = jmsProducer;
    }

    /**
     * Sends message to topic
     *
     * @param contextRefreshedEvent ContextRefreshedEvent
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        try {
            log.info("Sending message to topic on app start");
            jmsProducer.sendMessage("App1 launched");
        } catch (Exception e) {
            log.info("Failed sending message broker on startup");
        }
    }
}
