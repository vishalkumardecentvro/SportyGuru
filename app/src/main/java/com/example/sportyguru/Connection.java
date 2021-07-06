package com.example.sportyguru;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class Connection {

  public static final String URL = "http://universities.hipolabs.com/";

  public static UniversityInterface universityInterface = null;

  public static UniversityInterface getUniversityInterface() {
    if (universityInterface == null) {
      Retrofit retrofit = new Retrofit.Builder()
              .baseUrl(URL)
              .addConverterFactory(GsonConverterFactory.create())
              .build();

      universityInterface = retrofit.create(UniversityInterface.class);
    }
    return universityInterface;
  }

  public interface UniversityInterface {
    @GET("search?country=India")
    Call<List<University>> getUniversities();
  }
}
