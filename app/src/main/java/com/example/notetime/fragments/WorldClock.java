package com.example.notetime.fragments;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.notetime.Model.Time;
import com.example.notetime.R;
import com.example.notetime.RDS.TimeRepository;
import com.example.notetime.ViewModel.NotepadViewModel;
import com.example.notetime.ViewModel.WorldClockViewModel;

public class WorldClock extends Fragment implements View.OnClickListener{

    private WorldClockViewModel mViewModel;
    private View root;
    EditText search;
    TextView city,timetext,text;
    ImageButton btn;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.world_clock_fragment, container, false);
        return root;
    }



    @Override
    public void onViewCreated(View view,Bundle savedInstanceState){

        text = root.findViewById(R.id.textView2);
        search = root.findViewById(R.id.editText_searchcity);
        city = root.findViewById(R.id.show_city2);
        timetext = root.findViewById(R.id.show_time2);

        btn = root.findViewById(R.id.imageButton);


        mViewModel = ViewModelProviders.of(this).get(WorldClockViewModel.class);
        mViewModel.getTime().observe(this, new Observer<Time>() {
            @Override
            public void onChanged(Time time) {

                city.setText(time.getCity());
                timetext.setText(time.getTime());

                //Toast.makeText(getContext(),"Timezone not get! check your city name!",Toast.LENGTH_SHORT);

            }
        });

        btn.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        updateTime(v);

        Log.d("Clicked","Sucess");

    }

    public void updateTime(View view){
        mViewModel.updateTime(search.getText().toString());
    }
}
