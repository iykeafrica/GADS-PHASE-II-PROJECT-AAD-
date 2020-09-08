package com.example.gadsleaderboard.interfaces;

import com.example.gadsleaderboard.models.LearnerSkillIQ;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiSkillIQ {
    //To define all the URLs with the http request type and parameters.

    String BASE_URL = "https://gadsapi.herokuapp.com";

    //Call type as a List and the List type as LearnerSkillIQ.
    @GET("/api/skilliq")
    Call<List<LearnerSkillIQ>> getSkillIqInfo();

}
