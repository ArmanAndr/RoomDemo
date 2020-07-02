package com.mobiquel.roomapp;

import android.app.Application;

import java.util.List;

import androidx.lifecycle.LiveData;

public class WordRepository {
    private DAO dao;
    private LiveData<List<Word>> mAllWords;

    WordRepository(Application application) {
        WordRoomDataBase dataBase = WordRoomDataBase.getDatabase(application);
        dao = dataBase.dao();
        mAllWords = dao.getAllWords();
    }

    LiveData<List<Word>> getmAllWords() {
        return mAllWords;
    }

    void insert(Word word) {
        WordRoomDataBase.databaseWriteExecutor.execute(() -> {
            dao.insert(word);
        });
    }
}
