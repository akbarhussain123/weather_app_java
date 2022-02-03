package com.example.weatherapplication.retopit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClint {


    public static String BASE_URL = "https://api.openweathermap.org/data/2.5/";

    private static Retrofit retrofit = null;

    public static Retrofit getClint() {
        if (retrofit == null) {

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();


        }
        return retrofit;
    }


}