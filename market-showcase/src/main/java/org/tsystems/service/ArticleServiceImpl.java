package org.tsystems.service;

import com.google.gson.Gson;
import org.tsystems.data.Article;
import org.tsystems.mdb.TopicMDB;

import javax.annotation.ManagedBean;
import java.io.IOException;
import java.net.URI;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Sergey Nikolaychuk on 31.10.2020
 */

@ManagedBean
public class ArticleServiceImpl implements ArticleService {

    private static final String URL = "http://localhost:8081/api";

    private final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .build();

    private static final Logger LOGGER = Logger.getLogger(TopicMDB.class.toString());

    @Override
    public List<Article> getTopArticles() {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(URL + "/articles/topArticles"))
                .build();

        HttpResponse<String> response = null;
        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            List<Article> articles = new Gson().fromJson(response.body(), ArrayList.class);
            LOGGER.info("Received articles from app1");
            return articles;
        } catch (IOException e) {
            LOGGER.info("Couldn't connect to server:");
            e.printStackTrace();
        } catch (Exception e) {
            LOGGER.info("ArticleServiceImpl Exception");
            return new ArrayList<>();
        }

        return new ArrayList<>();
    }
}
