package com.moinahmad.jpmc.nycschools.model;

import com.google.gson.annotations.SerializedName;

public class SATResult {
    @SerializedName("dbn")
    public String dbn;

    @SerializedName("schoolName")
    public String schoolName;

    @SerializedName("numOf_SATTestTakers")
    public String numOfSATTestTakers;

    @SerializedName("SAT_CriticalReading_AvgScore")
    public String avgScoreCriticalReading;

    @SerializedName("SAT_Math_AvgScore")
    public String avgScoreMath;

    @SerializedName("SAT_Writing_AvgScore")
    public String avgScoreWriting;
}
