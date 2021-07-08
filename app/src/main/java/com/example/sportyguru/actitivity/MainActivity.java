package com.example.sportyguru.actitivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.sportyguru.Connection;
import com.example.sportyguru.R;
import com.example.sportyguru.adapter.UniversityAdapter;
import com.example.sportyguru.databinding.ActivityMainBinding;
import com.example.sportyguru.room.entity.UniversityEntity;
import com.example.sportyguru.room.view.UniversityView;
import com.example.sportyguru.table.University;
import com.wessam.library.NetworkChecker;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
  private ActivityMainBinding binding;

  private UniversityAdapter universityAdapter;

  private UniversityView universityView;

  private boolean isOffline = false;

  private SharedPreferences sharedPreferences;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = ActivityMainBinding.inflate(getLayoutInflater());
    View view = binding.getRoot();

    setContentView(view);

    isOffline = !NetworkChecker.isNetworkConnected(this);

    instantiate();
    initialize();
    listen();
    load();

  }

  private void instantiate() {
    universityAdapter = new UniversityAdapter();

    universityView = ViewModelProviders.of(this).get(UniversityView.class);

    sharedPreferences = getSharedPreferences("university", MODE_PRIVATE);

  }

  private void initialize() {
    binding.rvUniversity.setAdapter(universityAdapter);
  }

  private void listen() {
    binding.mcvInternet.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

        if (NetworkChecker.isNetworkConnected(MainActivity.this)) {

          isOffline = !NetworkChecker.isNetworkConnected(MainActivity.this);

          load();

          binding.rvUniversity.setVisibility(View.VISIBLE);
          binding.ivNoInternet.setVisibility(View.GONE);
          binding.mcvInternet.setVisibility(View.GONE);
          binding.mcvLoadData.setVisibility(View.GONE);
        }
      }
    });

  }

  private void load() {
    binding.lpiProgress.setVisibility(View.VISIBLE);
    binding.lpiProgress.setIndicatorColor(getResources().getColor(R.color.green_primary));

    if (isOffline)
      populateDataInOfflineMode();
    else {
      Call<List<University>> data = Connection.getUniversityInterface().getUniversities();
      data.enqueue(new Callback<List<University>>() {
        @Override
        public void onResponse(Call<List<University>> call, Response<List<University>> universityResponse) {

          if (universityResponse.body() != null) {

            universityAdapter.setUniversityList(universityResponse.body());
            getSupportActionBar().setTitle("Universities");
            saveUniversitiesInRoom(universityResponse.body());

          }
          binding.lpiProgress.setVisibility(View.GONE);
        }

        @Override
        public void onFailure(Call<List<University>> call, Throwable t) {
          Toast.makeText(MainActivity.this, "Please check your internet connection or try again", Toast.LENGTH_SHORT).show();
        }
      });
    }
  }

  private void saveUniversitiesInRoom(List<University> universityList) {

    if (!sharedPreferences.getBoolean("isUniversityPopulatedInOfflineMode", false)) {
      int universityCount = 0;

      for (University university : universityList) {

        if (universityCount == 20) {

          SharedPreferences.Editor universityEditor = sharedPreferences.edit();
          universityEditor.putBoolean("isUniversityPopulatedInOfflineMode", true);
          universityEditor.apply();

          return;
        }

        saveUniversity(university);
        universityCount++;
      }
    } else
      Toast.makeText(MainActivity.this, "20 universities stored in room database", Toast.LENGTH_SHORT).show();

  }

  private void saveUniversity(University university) {
    UniversityEntity universityEntity = new UniversityEntity();

    universityEntity.setName(university.getName());
    universityEntity.setCountry(university.getCountry());
    universityEntity.setDomain(university.getDomains().get(0));
    universityEntity.setWebPage(university.getWebPages().get(0));
    universityEntity.setAlphaTwoCode(university.getAlphaTwoCode());

    if (university.getState() != null)
      universityEntity.setState(university.getState());

    universityView.insert(universityEntity);
  }

  private void populateDataInOfflineMode() {

    universityView.getAllUniversities().observe(MainActivity.this, new Observer<List<UniversityEntity>>() {
      @Override
      public void onChanged(List<UniversityEntity> universityList) {
        if (!universityList.isEmpty()) {
          if (isOffline) {
            getSupportActionBar().setTitle("Universities (Offline (20) )");

            universityAdapter.setUniversityEntityList(universityList, isOffline);

            binding.rvUniversity.setVisibility(View.VISIBLE);
            binding.ivNoInternet.setVisibility(View.GONE);
            binding.mcvInternet.setVisibility(View.GONE);
          }
          binding.lpiProgress.setVisibility(View.GONE);

        } else {
          Toast.makeText(MainActivity.this, "Please connect internet once to save data in offline mode", Toast.LENGTH_SHORT).show();

          binding.lpiProgress.setVisibility(View.GONE);
          binding.rvUniversity.setVisibility(View.GONE);
          binding.ivNoInternet.setVisibility(View.VISIBLE);
          binding.mcvInternet.setVisibility(View.VISIBLE);

        }
      }
    });
  }
}