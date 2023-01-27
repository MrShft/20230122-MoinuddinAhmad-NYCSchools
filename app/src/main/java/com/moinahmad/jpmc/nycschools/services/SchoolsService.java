package com.moinahmad.jpmc.nycschools.services;

import com.moinahmad.jpmc.nycschools.model.School;
import com.moinahmad.jpmc.nycschools.model.SATResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SchoolsService {
    @GET("s3k6-pzi2.json")
    Call<List<School>> getSchools(@Query("$offset") int offset, @Query("$limit") int limit);

    @GET("f9bf-2cp4.json")
    Call<List<SATResult>> getSATResults(@Query("dbn") String dbn);

    static SchoolsService create() {
        final String BASE_URL = "https://data.cityofnewyork.us/resource/";

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(SchoolsService.class);
    }
}
