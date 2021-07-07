package com.example.sportyguru.room.view;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.sportyguru.room.entity.UniversityEntity;
import com.example.sportyguru.room.repository.UniversityRepository;

import java.util.List;

public class UniversityView extends AndroidViewModel {

  private UniversityRepository repository;

  private LiveData<List<UniversityEntity>> allUniversities;

  public UniversityView(@NonNull Application application) {
    super(application);

    repository = new UniversityRepository(application);
    allUniversities = repository.getUniversity();
  }

  public void insert(UniversityEntity universityEntity) {
    repository.insertUniversity(universityEntity);
  }

  public LiveData<List<UniversityEntity>> getAllUniversities() {
    return allUniversities;
  }

  public void deleteAllUniversities() {
    repository.deleteAllUniversities();
  }
}
