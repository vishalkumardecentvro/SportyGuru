package com.example.sportyguru.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sportyguru.University;
import com.example.sportyguru.databinding.RvUniversityBinding;

import java.util.ArrayList;
import java.util.List;

public class UniversityAdapter extends RecyclerView.Adapter<UniversityAdapter.ViewHolder> {
  private List<University> universityList = new ArrayList<>();

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

    }

    private void populate(University university) {
      binding.tvName.setText(university.getName());
    }
  }
}
