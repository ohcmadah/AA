package com.ninecm.aa;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Cosmetic.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract CosmeticDao cosmeticDao();
}
