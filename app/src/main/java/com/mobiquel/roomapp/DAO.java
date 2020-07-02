package com.mobiquel.roomapp;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface DAO {


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Void insert(Word word);

    @Query("Delete from word_table")
    void deleteAll();

    @Query("Select *from word_table ORDER BY word ASC")
    LiveData<List<Word>> getAllWords();

}
