package com.moinahmad.jpmc.nycschools.model;

import com.google.gson.annotations.SerializedName;

public class School {
    @SerializedName("dbn")
    public String dbn;

    @SerializedName("schoolName")
    public String schoolName;

    @SerializedName("overview_Paragraph")
    public String overviewParagraph;

    @SerializedName("location")
    public String location;

    @SerializedName("phoneNum")
    public String phoneNumber;

    @SerializedName("school_Email")
    public String schoolEmail;
}
