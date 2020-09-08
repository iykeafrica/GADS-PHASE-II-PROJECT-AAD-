package com.example.gadsleaderboard.interfaces;

import com.example.gadsleaderboard.models.LearnerHour;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiHours {

    String BASE_URL = "https://gadsapi.herokuapp.com";

    @GET("/api/hours")
    Call<List<LearnerHour>> getHourInfo();

}
