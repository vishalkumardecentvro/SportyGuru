package com.example.sportyguru.room.database;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.sportyguru.room.dao.UniversityDao;
import com.example.sportyguru.room.entity.UniversityEntity;

@androidx.room.Database(entities = {UniversityEntity.class}, version = 1, exportSchema = false)
public abstract class Database extends RoomDatabase {

  public static Database databaseInstance;

  public static synchronized Database getDatabase(Context context) {
    if (databaseInstance == null) {

      databaseInstance = Room.databaseBuilder(context.getApplicationContext(), Database.class, "university")
              .fallbackToDestructiveMigrationFrom()
              .build();
    }
    return databaseInstance;
  }

  public abstract UniversityDao universityDao();
}
