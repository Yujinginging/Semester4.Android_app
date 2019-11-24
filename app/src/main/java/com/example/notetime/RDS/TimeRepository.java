package com.example.notetime.RDS;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.notetime.Model.Time;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TimeRepository {

    private TimeDAO timeDAO;
    private static TimeRepository instance;
    private MutableLiveData<Time> time;

    private TimeRepository()
    {
       /* TimeDatabase timeDatabase = TimeDatabase.getInstance(application);
        timeDAO = timeDatabase.timeDAO();*/
        time = new MutableLiveData<>();
    }

    public static synchronized TimeRepository getInstance() {
        if (instance == null) {
            instance = new TimeRepository();
        }
        return instance;
    }

    public LiveData<Time> getTime() {
        return time;
    }


    public void updateTime(String city) {
        TimeApi timeApi = ServiceGenerator.getTimeApi();
        Call<TimeResponse> call = timeApi.getTime(city);
        call.enqueue(new Callback<TimeResponse>() {
            @Override
            public void onResponse(Call<TimeResponse> call, Response<TimeResponse> response) {
                if (response.code() == 200) {
                      time.setValue(response.body().getTime());
                }
            }

            @Override
            public void onFailure(Call<TimeResponse> call, Throwable t) {
                Log.i("Retrofit", ":( TAT ");
            }
        });
    }

}
