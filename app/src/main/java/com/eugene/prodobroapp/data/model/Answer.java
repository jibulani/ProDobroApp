package com.eugene.prodobroapp.data.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by eugene on 29.09.18.
 */

@Entity(foreignKeys = {@ForeignKey(entity = Question.class,
                                   parentColumns = "id",
                                   childColumns = "previousQuestionId",
                                   onDelete = ForeignKey.CASCADE),
                       @ForeignKey(entity = Question.class,
                                   parentColumns = "id",
                                   childColumns = "nextQuestionId",
                                   onDelete = ForeignKey.CASCADE)})
public class Answer {

    @PrimaryKey
    private final int id;
    private final String answerText;
    private final int previousQuestionId;
    private final int nextQuestionId;

    public Answer(int id, String answerText, int previousQuestionId, int nextQuestionId) {
        this.id = id;
        this.answerText = answerText;
        this.previousQuestionId = previousQuestionId;
        this.nextQuestionId = nextQuestionId;
    }

    public int getId() {
        return id;
    }

    public String getAnswerText() {
        return answerText;
    }

    public int getPreviousQuestionId() {
        return previousQuestionId;
    }

    public int getNextQuestionId() {
        return nextQuestionId;
    }
}
