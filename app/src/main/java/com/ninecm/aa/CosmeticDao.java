package com.ninecm.aa;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CosmeticDao {
    @Query("SELECT * FROM cosmetic_table")
    LiveData<List<Cosmetic>> getAll();

    @Query("SELECT * FROM cosmetic_table WHERE id=:id")
    Cosmetic getById(int id);

    @Insert
    void insert(Cosmetic cosmetic);

    @Update
    void update(Cosmetic cosmetic);

    @Delete
    void delete(Cosmetic cosmetic);
}
