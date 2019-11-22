package com.example.notetime.RDS;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

interface TimeApi {
    @GET("api/timezone/{city}")
    Call<TimeResponse> getTime(@Path("city") String city);
    //the city name should only be in europe and those "famous" > capital letter first of the capital city :)
    //eg:Berlin,Copenhagen...
}
