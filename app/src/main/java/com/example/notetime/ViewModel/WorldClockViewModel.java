package com.example.notetime.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.notetime.Model.Time;
import com.example.notetime.RDS.TimeRepository;

public class WorldClockViewModel  extends ViewModel {

    TimeRepository repository;

    public WorldClockViewModel( ) {
        repository = TimeRepository.getInstance();
    }
    // TODO: Implement the ViewModel
    /*public void insert(Time time){
        repository.insert(time);
    }
*/
    public LiveData<Time> getTime(){
        return repository.getTime();
    }
    public void updateTime(String s){
        repository.updateTime(s);
    }


}
