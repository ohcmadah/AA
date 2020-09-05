package com.ninecm.aa;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Cosmetic.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract CosmeticDao cosmeticDao();
    private static volatile AppDatabase INSTANCE;

    public static synchronized AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "cosmetic-db")
                    .fallbackToDestructiveMigration()
//                    .addCallback(roomCallback)
                    .build();
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(INSTANCE).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private CosmeticDao cosmeticDao;
        private PopulateDbAsyncTask(AppDatabase db) {
            cosmeticDao = db.cosmeticDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            cosmeticDao.insert(new Cosmetic("Cosmetic 1", "20200930", 3, "hi!"));
            cosmeticDao.insert(new Cosmetic("Cosmetic 2", "20201002", 3, "oh!"));
            cosmeticDao.insert(new Cosmetic("Cosmetic 3", "20201203", 2, "my!"));
            return null;
        }
    }
}
