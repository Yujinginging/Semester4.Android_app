package com.example.notetime.RDS;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {
    private static Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
            .baseUrl("http://worldtimeapi.org/")
            .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = retrofitBuilder.build();

    private static TimeApi api = retrofit.create(TimeApi.class);

    public static TimeApi getTimeApi() {
        return api;
    }
}
