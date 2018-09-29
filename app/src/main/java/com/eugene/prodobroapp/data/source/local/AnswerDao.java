package com.eugene.prodobroapp.data.source.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.eugene.prodobroapp.data.model.Answer;

import java.util.List;

/**
 * Created by eugene on 29.09.18.
 */

@Dao
public interface AnswerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAnswers(Answer... answers);

    @Query("SELECT * FROM answer")
    List<Answer> getAnswers();

    @Query("SELECT * FROM answer WHERE previousQuestionId = :previousQuestionId")
    List<Answer> getAnswersByQuestionId(int previousQuestionId);
}
