package com.example.sportyguru.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sportyguru.databinding.RvUniversityBinding;
import com.example.sportyguru.table.University;

import java.util.ArrayList;
import java.util.List;

public class UniversityAdapter extends RecyclerView.Adapter<UniversityAdapter.ViewHolder> {
  private List<University> universityList = new ArrayList<>();
  private boolean isUniversityExpanded = false;

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    return new ViewHolder(RvUniversityBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    holder.populate(universityList.get(position));
  }

  @Override
  public int getItemCount() {
    return universityList.size();
  }

  public void setUniversityList(List<University> universityList) {
    this.universityList = universityList;
    notifyDataSetChanged();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {
    private final RvUniversityBinding binding;

    public ViewHolder(RvUniversityBinding binding) {
      super(binding.getRoot());
      this.binding = binding;

      binding.llUniversity.setOnClickListener(v -> expandUniversity());

    }

    private void populate(University university) {

      binding.tvName.setText(university.getName());
      binding.tvAlphaCode.setText(university.getAlphaTwoCode());
      binding.tvCountry.setText(university.getCountry());
      binding.tvState.setText(university.getState());
      binding.tvDomain.setText(university.getDomains().get(0));
      binding.tvWebsite.setText(university.getWebPages().get(0));

    }

    private void expandUniversity() {

      if (!isUniversityExpanded) {
        binding.llUniversityInformation.setVisibility(View.VISIBLE);
        isUniversityExpanded = true;
      } else {
        binding.llUniversityInformation.setVisibility(View.GONE);
        isUniversityExpanded = false;
      }

    }
  }
}
