package com.gebril.yamen.pff.activities.rest;

import com.gebril.yamen.pff.activities.tools.Preferences;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiClient {

    public static final String BASE_URL = Preferences.BASE_URL;
    private static Retrofit retrofit = null;


    public static Retrofit getClient() {
        //create okhttp client
        OkHttpClient.Builder okhttpClientBuilder = new OkHttpClient.Builder();

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okhttpClientBuilder.addInterceptor(loggingInterceptor);

        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okhttpClientBuilder.build())
                    .build();
        }
        return retrofit;
    }
}
