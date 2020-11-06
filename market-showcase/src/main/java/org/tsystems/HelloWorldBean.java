package org.tsystems;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Created by Sergey Nikolaychuk on 25.10.2020
 */

@Named("helloworld")
@SessionScoped
public class HelloWorldBean implements Serializable {

    private String text = "TEXT123";

    public String getMessage() {
        return "Hello World from Fuertefentura";
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}