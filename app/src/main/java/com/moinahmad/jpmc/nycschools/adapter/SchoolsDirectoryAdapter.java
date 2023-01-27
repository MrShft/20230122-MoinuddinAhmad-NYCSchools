package com.moinahmad.jpmc.nycschools.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.annotation.NonNull;

import com.moinahmad.jpmc.R;
import com.moinahmad.jpmc.nycschools.model.School;

import java.util.ArrayList;


public class SchoolsDirectoryAdapter extends RecyclerView.Adapter<SchoolsDirectoryAdapter.MyViewHolder> {
    private ArrayList<School> schoolsArray;
    private OnItemClickListener myListener;

    public interface OnItemClickListener {
        void OnItemClick(School school);
    }

    public SchoolsDirectoryAdapter(ArrayList<School> schools, OnItemClickListener listener) {
        this.schoolsArray = schools;
        this.myListener = listener;
    }

    @NonNull
    @Override
    public SchoolsDirectoryAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.school_item, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return schoolsArray.size();
    }

    @Override
    public void onBindViewHolder(@NonNull SchoolsDirectoryAdapter.MyViewHolder holder, int position) {
        holder.name_textView.setText(schoolsArray.get(position).schoolName);
        holder.location_textView.setText(schoolsArray.get(position).location);
        holder.phoneNum_textView.setText(schoolsArray.get(position).phoneNumber);
        holder.schoolEmail_textView.setText(schoolsArray.get(position).schoolEmail);
        holder.itemView.setOnClickListener(view -> myListener.OnItemClick(schoolsArray.get(position)));
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView name_textView;
        private TextView location_textView;
        private TextView phoneNum_textView;
        private TextView schoolEmail_textView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name_textView = itemView.findViewById(R.id.school_name);
            location_textView = itemView.findViewById(R.id.location);
            phoneNum_textView = itemView.findViewById(R.id.phone_number);
            schoolEmail_textView = itemView.findViewById(R.id.school_email);
        }
    }
}
