package com.moinahmad.jpmc.nycschools.repositories;

import androidx.lifecycle.MutableLiveData;

import com.moinahmad.jpmc.nycschools.services.SchoolsService;
import com.moinahmad.jpmc.nycschools.model.School;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SchoolsDirectoryRepo {
    private static final int size = 30;
    private static SchoolsDirectoryRepo schoolsDirRepo;
    private SchoolsService schoolsService;

    public SchoolsDirectoryRepo() { schoolsService = SchoolsService.create(); }

    public static SchoolsDirectoryRepo getInstance() {
        if (schoolsDirRepo == null) {
            schoolsDirRepo = new SchoolsDirectoryRepo();
        }
        return schoolsDirRepo;
    }

    public MutableLiveData<List<School>> requestSchoolDir(int offset) {
        MutableLiveData<List<School>> data = new MutableLiveData<>();

        schoolsService.getSchools(offset, size).enqueue(new Callback<List<School>>() {
            @Override
            public void onResponse(Call<List<School>> call, Response<List<School>> response) {
                if (response.isSuccessful()) {
                    data.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<School>> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }
}
