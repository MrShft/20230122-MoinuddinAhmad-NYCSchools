package com.moinahmad.jpmc.nycschools.activity;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.moinahmad.jpmc.R;
import com.moinahmad.jpmc.nycschools.model.SATResult;
import com.moinahmad.jpmc.nycschools.viewmodels.SchoolsViewModel;


public class SATResultsActivity extends AppCompatActivity {
    SchoolsViewModel schoolsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.acitivity_sat_results);

        String dbn = getIntent().getStringExtra("dbn");
        String name = getIntent().getStringExtra("name");
        String description = getIntent().getStringExtra("description");

        ((TextView)findViewById(R.id.school_name)).setText(name);
        ((TextView)findViewById(R.id.school_description)).setText(description);

        schoolsViewModel = new ViewModelProvider(this).get(SchoolsViewModel.class);
        schoolsViewModel.init();

        schoolsViewModel.getSATResults(dbn).observe(SATResultsActivity.this, satResults -> {
            if (!satResults.isEmpty()) {
                SATResult satResult = satResults.get(0);
                ((TextView)findViewById(R.id.numOfSatTestTakers)).setText(String.valueOf(satResult.numOfSATTestTakers));
                ((TextView)findViewById(R.id.satCriticalReadingAvgScore)).setText(String.valueOf(satResult.avgScoreCriticalReading));
                ((TextView)findViewById(R.id.satMathAvgScore)).setText(String.valueOf(satResult.avgScoreMath));
                ((TextView)findViewById(R.id.satWritingAvgScore)).setText(String.valueOf(satResult.avgScoreWriting));
            } else {
                ((TextView)findViewById(R.id.numOfSatTestTakers)).setText(R.string.unknown);
                ((TextView)findViewById(R.id.satCriticalReadingAvgScore)).setText(R.string.unknown);
                ((TextView)findViewById(R.id.satMathAvgScore)).setText(R.string.unknown);
                ((TextView)findViewById(R.id.satWritingAvgScore)).setText(R.string.unknown);
            }
        });
    }
}
