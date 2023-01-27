package com.moinahmad.jpmc.nycschools.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.TextView;

import com.moinahmad.jpmc.R;
import com.moinahmad.jpmc.nycschools.adapter.SchoolsDirectoryAdapter;
import com.moinahmad.jpmc.nycschools.adapter.recyclerAdapter;
import com.moinahmad.jpmc.nycschools.viewmodels.SchoolsViewModel;
import com.moinahmad.jpmc.nycschools.model.School;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView thisRecyclerView = findViewById(R.id.recyclerView);
    SchoolsViewModel thisSchoolsViewModel;
    SchoolsDirectoryAdapter schoolAdapter;
    private ArrayList<School> schoolsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //schoolsList = new ArrayList<>();

        thisRecyclerView = findViewById(R.id.schoolList);
        thisSchoolsViewModel = new ViewModelProvider(this).get(SchoolsViewModel.class);
        thisSchoolsViewModel.init();

        Context context = this;

        if (schoolAdapter == null) {
            requestData();
        }

        initRecyclerView();
    }

    private void initRecyclerView() {
        if (schoolAdapter != null) {
            return;
        }

        //requestData();

        schoolAdapter = new SchoolsDirectoryAdapter(schoolsList, school -> {
            Intent intent = new Intent(MainActivity.this, SATResultsActivity.class);

            intent.putExtra("dbn", school.dbn);
            intent.putExtra("name", school.schoolName);
            intent.putExtra("description", school.overviewParagraph);

            startActivity(intent);
        });

        thisRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        //thisRecyclerView.setLayoutManager(layoutManager);
        thisRecyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerAdapter adapter = new recyclerAdapter(schoolsList);
        //thisRecyclerView.setAdapter(schoolAdapter);
        thisRecyclerView.setAdapter(adapter);

        thisRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (!thisRecyclerView.canScrollVertically(1)) {
                    requestData();
                }
            }
        });
    }

    private void requestData() {

        thisSchoolsViewModel.getSchoolDirectory(schoolsList.size()).observe(this, schoolsData -> {
            if (!schoolsData.isEmpty()) {
                schoolsList.addAll(schoolsData);
                schoolAdapter.notifyDataSetChanged();
            } else {
                ((TextView)findViewById(R.id.errorMsg)).setVisibility(View.VISIBLE);
            }
        });
    }
}