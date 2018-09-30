package com.eugene.prodobroapp.data.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by eugene on 30.09.18.
 */

@Entity
public class LastMessage {

    @PrimaryKey
    @NonNull
    private String id;

    public LastMessage(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
