package com.ninecm.aa;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class CosmeticRepository {
    private CosmeticDao cosmeticDao;
    private LiveData<List<Cosmetic>> allCosmetics;

    public CosmeticRepository(Application application) {
        AppDatabase database = AppDatabase.getInstance(application);
        cosmeticDao = database.cosmeticDao();
        allCosmetics = cosmeticDao.getAll();
    }

    public void insert(Cosmetic cosmetic) {
        new InsertCosmeticAsyncTask(cosmeticDao).execute(cosmetic);
    }

    public void update(Cosmetic cosmetic) {
        new UpdateCosmeticAsyncTask(cosmeticDao).execute(cosmetic);
    }

    public void delete(Cosmetic cosmetic) {
        new DeleteCosmeticAsyncTask(cosmeticDao).execute(cosmetic);
    }

    public LiveData<List<Cosmetic>> getAll() {
        return allCosmetics;
    }

    private static class InsertCosmeticAsyncTask extends AsyncTask<Cosmetic, Void, Void> {
        private CosmeticDao cosmeticDao;

        public InsertCosmeticAsyncTask(CosmeticDao cosmeticDao) {
            this.cosmeticDao = cosmeticDao;
        }

        @Override
        protected Void doInBackground(Cosmetic... cosmetics) {
            cosmeticDao.insert(cosmetics[0]);
            return null;
        }
    }

    private static class UpdateCosmeticAsyncTask extends AsyncTask<Cosmetic, Void, Void> {
        private CosmeticDao cosmeticDao;

        public UpdateCosmeticAsyncTask(CosmeticDao cosmeticDao) {
            this.cosmeticDao = cosmeticDao;
        }

        @Override
        protected Void doInBackground(Cosmetic... cosmetics) {
            cosmeticDao.update(cosmetics[0]);
            return null;
        }
    }

    private static class DeleteCosmeticAsyncTask extends AsyncTask<Cosmetic, Void, Void> {
        private CosmeticDao cosmeticDao;

        public DeleteCosmeticAsyncTask(CosmeticDao cosmeticDao) {
            this.cosmeticDao = cosmeticDao;
        }

        @Override
        protected Void doInBackground(Cosmetic... cosmetics) {
            cosmeticDao.delete(cosmetics[0]);
            return null;
        }
    }
}
