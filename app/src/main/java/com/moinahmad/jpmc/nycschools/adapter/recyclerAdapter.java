package com.moinahmad.jpmc.nycschools.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.moinahmad.jpmc.R;
import com.moinahmad.jpmc.nycschools.model.School;

import java.util.ArrayList;

public class recyclerAdapter extends RecyclerView.Adapter<recyclerAdapter.MyViewHolder> {
    private ArrayList<School> schoolsList;
    private RecyclerView recyclerView;

    public recyclerAdapter(ArrayList<School> schoolsList) {
        this.schoolsList = schoolsList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView nameText;

        public MyViewHolder(final View view) {
            super(view);

            nameText = view.findViewById(R.id.school_name);
        }
    }

    @NonNull
    @Override
    public recyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View schoolItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.school_item, parent, false);

        return new MyViewHolder(schoolItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull recyclerAdapter.MyViewHolder holder, int position) {
            String schoolName = schoolsList.get(position).schoolName;
            holder.nameText.setText(schoolName);
    }

    @Override
    public int getItemCount() {
        return schoolsList.size();
    }
}
