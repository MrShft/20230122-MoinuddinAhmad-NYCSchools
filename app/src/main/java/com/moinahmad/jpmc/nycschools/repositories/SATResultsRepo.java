package com.moinahmad.jpmc.nycschools.repositories;

import androidx.lifecycle.MutableLiveData;

import com.moinahmad.jpmc.nycschools.services.SchoolsService;
import com.moinahmad.jpmc.nycschools.model.SATResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SATResultsRepo {
    private static SATResultsRepo thisRepo;
    private SchoolsService thisService;

    public SATResultsRepo() {
        thisService = SchoolsService.create();
    }

    public static SATResultsRepo getInstance() {
        if (thisRepo == null) {
            thisRepo = new SATResultsRepo();
        }
        return thisRepo;
    }

    public MutableLiveData<List<SATResult>> requestSATResults(String dbn) {
        MutableLiveData<List<SATResult>> data = new MutableLiveData<>();

        thisService.getSATResults(dbn).enqueue(new Callback<List<SATResult>>() {
            @Override
            public void onResponse(Call<List<SATResult>> call, Response<List<SATResult>> response) {
                if (response.isSuccessful()) {
                    data.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<SATResult>> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }

}
