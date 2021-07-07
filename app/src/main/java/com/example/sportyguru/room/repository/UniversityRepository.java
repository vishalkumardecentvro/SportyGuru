package com.example.sportyguru.room.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.sportyguru.room.dao.UniversityDao;
import com.example.sportyguru.room.database.Database;
import com.example.sportyguru.room.entity.UniversityEntity;

import java.util.List;

public class UniversityRepository {

  private UniversityDao universityDao;
  private LiveData<List<UniversityEntity>> countryInformation;

  public UniversityRepository(Application application) {
    Database database = Database.getDatabase(application);
    this.universityDao = database.universityDao();
    this.countryInformation = universityDao.getAllUniversities();
  }

  public void insertUniversity(UniversityEntity universityEntity) {
    new InsertAsyncTask(universityDao).execute(universityEntity);
  }

  public void deleteAllUniversities() {

    new DeleteAllAsyncCountries(universityDao).execute();
  }

  public LiveData<List<UniversityEntity>> getUniversity() {
    return countryInformation;
  }

  private static class InsertAsyncTask extends AsyncTask<UniversityEntity, Void, Void> {

    private UniversityDao universityDao;

    private InsertAsyncTask(UniversityDao universityDao) {
      this.universityDao = universityDao;
    }

    @Override
    protected Void doInBackground(UniversityEntity... universityEntities) {
      universityDao.insertUniversity(universityEntities[0]);
      return null;
    }
  }

  private static class DeleteAllAsyncCountries extends AsyncTask<Void, Void, Void> {

    private UniversityDao universityDao;

    public DeleteAllAsyncCountries(UniversityDao universityDao) {
      this.universityDao = universityDao;
    }

    @Override
    protected Void doInBackground(Void... voids) {
      universityDao.deleteAllUniversities();
      return null;
    }
  }
}
