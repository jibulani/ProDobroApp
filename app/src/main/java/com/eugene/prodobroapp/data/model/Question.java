package com.eugene.prodobroapp.data.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by eugene on 29.09.18.
 */

@Entity
public class Question {

    @PrimaryKey
    private final int id;
    private final String text;
    private final boolean isFinal;

    public Question(int id, String text, boolean isFinal) {
        this.id = id;
        this.text = text;
        this.isFinal = isFinal;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public boolean isFinal() {
        return isFinal;
    }
}
