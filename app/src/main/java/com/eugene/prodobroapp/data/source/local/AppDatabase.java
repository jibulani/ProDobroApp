package com.eugene.prodobroapp.data.source.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.eugene.prodobroapp.data.model.Answer;
import com.eugene.prodobroapp.data.model.LastMessage;
import com.eugene.prodobroapp.data.model.Question;

/**
 * Created by eugene on 29.09.18.
 */

@Database(entities = {Answer.class, Question.class, LastMessage.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract AnswerDao getAnswerDao();

    public abstract QuestionDao getQuestionDao();

    public abstract LastMessageDao getLastMessageDao();
}
