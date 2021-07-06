package com.example.sportyguru.actitivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sportyguru.Connection;
import com.example.sportyguru.table.University;
import com.example.sportyguru.adapter.UniversityAdapter;
import com.example.sportyguru.databinding.ActivityMainBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
  private ActivityMainBinding binding;
  private UniversityAdapter universityAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = ActivityMainBinding.inflate(getLayoutInflater());
    View view = binding.getRoot();

    setContentView(view);

    instantiate();
    initialize();
    listen();
    load();
  }

  private void instantiate() {
    universityAdapter = new UniversityAdapter();

  }

  private void initialize() {
    binding.rvUniversity.setAdapter(universityAdapter);
  }

  private void listen() {

  }

  private void load() {
    Call<List<University>> data = Connection.getUniversityInterface().getUniversities();
    data.enqueue(new Callback<List<University>>() {
      @Override
      public void onResponse(Call<List<University>> call, Response<List<University>> universityResponse) {

        if (universityResponse.body() != null) {
          universityAdapter.setUniversityList(universityResponse.body());
        }
      }
      @Override
      public void onFailure(Call<List<University>> call, Throwable t) {
        Toast.makeText(MainActivity.this, "Please check your internet connection or try again", Toast.LENGTH_SHORT).show();

      }
    });
  }
}