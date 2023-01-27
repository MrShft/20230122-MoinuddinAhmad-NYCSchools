package com.moinahmad.jpmc.nycschools.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import com.moinahmad.jpmc.nycschools.repositories.SATResultsRepo;
import com.moinahmad.jpmc.nycschools.repositories.SchoolsDirectoryRepo;
import com.moinahmad.jpmc.nycschools.model.SATResult;
import com.moinahmad.jpmc.nycschools.model.School;

public class SchoolsViewModel extends ViewModel {
    private SchoolsDirectoryRepo schoolsDirectoryRepo;
    private SATResultsRepo SATresultsRepo;

    public void init() {
        if (schoolsDirectoryRepo == null) {
            schoolsDirectoryRepo = SchoolsDirectoryRepo.getInstance();
        }

        if (SATresultsRepo == null) {
            SATresultsRepo = SATResultsRepo.getInstance();
        }
    }

    public LiveData<List<School>> getSchoolDirectory(int offset) {
        return schoolsDirectoryRepo.requestSchoolDir(offset);
    }

    public LiveData<List<SATResult>> getSATResults(String dbn) {
        return SATresultsRepo.requestSATResults(dbn);
    }
}
