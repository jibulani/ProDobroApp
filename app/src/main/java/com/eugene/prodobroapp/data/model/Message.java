package com.eugene.prodobroapp.data.model;

import java.util.UUID;

/**
 * Created by eugene on 30.09.18.
 */

public class Message {

    private String id;
    private String text;

    public Message(String text) {
        this.id = UUID.randomUUID().toString();
        this.text = text;
    }

    public String getId() {
        return id;
    }

    public String getText() {
        return text;
    }
}
