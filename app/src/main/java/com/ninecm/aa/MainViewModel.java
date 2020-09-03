package com.ninecm.aa;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MainViewModel extends AndroidViewModel {
    private AppDatabase db;

    public MainViewModel(@NonNull Application application) {
        super(application);

        db = AppDatabase.getInstance(application);
    }

    public LiveData<List<Cosmetic>> getAll() {
        return db.cosmeticDao().getAll();
    }

    public LiveData<Integer> getDataCount() {
        return db.cosmeticDao().getDataCount();
    }

    public void insert(Cosmetic cosmetic) {
        new InsertAsyncTask(db.cosmeticDao()).execute(cosmetic);
    }

    private static class InsertAsyncTask extends AsyncTask<Cosmetic, Void, Void> {
        private CosmeticDao mCosmeticDao;

        public InsertAsyncTask(CosmeticDao cosmeticDao) {
            this.mCosmeticDao = cosmeticDao;
        }

        @Override
        protected Void doInBackground(Cosmetic... cosmetics) {
            mCosmeticDao.insert(cosmetics[0]);
            return null;
        }
    }
}
