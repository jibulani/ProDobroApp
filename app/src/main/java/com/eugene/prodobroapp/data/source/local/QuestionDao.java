package com.eugene.prodobroapp.data.source.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.eugene.prodobroapp.data.model.Question;

import java.util.List;

/**
 * Created by eugene on 29.09.18.
 */

@Dao
public interface QuestionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertQuestions(Question... questions);

    @Query("SELECT * FROM question")
    List<Question> getQuestions();

    @Query("SELECT * FROM question WHERE id = :id LIMIT 1")
    Question getQuestionById(int id);
}
