package com.mobiquel.roomapp;

import android.content.Context;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Word.class}, version = 1, exportSchema = false)
public abstract class WordRoomDataBase extends RoomDatabase {

    public abstract DAO dao();

    public static volatile WordRoomDataBase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    private static RoomDatabase.Callback sRoomDBCallBack = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            databaseWriteExecutor.execute(() -> {
                      //  DAO dao = INSTANCE.dao();
                       // dao.deleteAll();
                       /* Word word = new Word("Hello 1");
                        dao.insert(word);
                        word = new Word("Hello 2");
                        dao.insert(word);*/
                    }

            );
        }
    };

    static WordRoomDataBase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (WordRoomDataBase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), WordRoomDataBase.class, "word_database").addCallback(sRoomDBCallBack).build();
                }
            }
        }
        return INSTANCE;
    }


}
