package com.eugene.prodobroapp.data.source.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.eugene.prodobroapp.data.model.LastMessage;

/**
 * Created by eugene on 30.09.18.
 */

@Dao
public interface LastMessageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertLastMessage(LastMessage... lastMessages);

    @Query("SELECT * FROM lastmessage LIMIT 1")
    LastMessage getLastMessage();

    @Delete
    int deleteLastMessage(LastMessage lastMessage);
}
