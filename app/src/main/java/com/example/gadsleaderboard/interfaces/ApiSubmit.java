package com.example.gadsleaderboard.interfaces;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiSubmit {
    String BASE_URL = "https://docs.google.com/forms/d/e/";

    @POST("1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse")
    @FormUrlEncoded
    Call<Void> savePost(@Field("entry.1877115667") String firstName,
                  @Field("entry.2006916086") String lastName,
                  @Field("entry.1824927963") String emailAddress,
                  @Field("entry.284483984") String linkToProject);


//    String BASE_URL = "https://docs.google.com/forms/u/0/d/e/";
//
//    @POST("1FAIpQLScDNAK-3igQiytBmd1kiCsdE9IhOtWct3DcWqO8E9aMbk69Qw/formResponse")
//    @FormUrlEncoded
//    Call<Void> savePost(@Field("entry.765549710") String firstName,
//                        @Field("entry.1198796902") String lastName,
//                        @Field("entry.885164019") String emailAddress,
//                        @Field("entry.27132077") String linkToProject);
}
