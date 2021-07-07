package com.example.sportyguru.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.sportyguru.room.entity.UniversityEntity;

import java.util.List;

@Dao
public interface UniversityDao {

  @Insert
  void insertUniversity(UniversityEntity universityEntity);

  @Query("DELETE FROM university")
  void deleteAllUniversities();

  @Query("SELECT * FROM university")
  LiveData<List<UniversityEntity>> getAllUniversities();
}
