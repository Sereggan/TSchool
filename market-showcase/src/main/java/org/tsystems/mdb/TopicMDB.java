/*
 * JBoss, Home of Professional Open Source
 * Copyright 2015, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.tsystems.mdb;

import org.tsystems.data.Article;
import org.tsystems.data.ArticlesRepository;
import org.tsystems.service.ArticleService;

import java.util.List;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.faces.push.Push;
import javax.faces.push.PushContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.*;

@Named
@MessageDriven(name = "article-listener", activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "articleTopic"),
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge")})
public class TopicMDB implements MessageListener {

    @Inject
    ArticleService articleService;

    @Inject
    ArticlesRepository articlesRepository;

    @Inject
    @Push(channel = "push")
    PushContext push;

    private static final Logger LOGGER = Logger.getLogger(TopicMDB.class.toString());

    /**
     * @see MessageListener#onMessage(Message)
     */
    public void onMessage(Message message) {
        try {
            LOGGER.info("Received Message from topic:" + ((TextMessage) message).getText());
            List<Article> articles = articleService.getTopArticles();
            articlesRepository.setArticles(articles);
            push.send("pushed");
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
