package com.gebril.yamen.pff.activites.rest;


import com.gebril.yamen.pff.activites.Model.Player;
import com.gebril.yamen.pff.activites.Model.Responses;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface ApiInterface {

    @FormUrlEncoded
    @POST("login")
    Call<Player> loginRequest(
            @Field("email") String email,
            @Field("password") String password
    );

    @GET("checkEmail/{email}")
    Call<Integer> checkEmail(
        @Path("email") String email
    );


    @GET("checkUsername/{username}")
    Call<Integer> checkUsername(
            @Path("username") String username
    );

    @POST("register")
    Call<Responses> registerRequest(
           @Body Player player
    );
}
