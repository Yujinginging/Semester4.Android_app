package com.example.notetime.fragments;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.notetime.Model.Note;
import com.example.notetime.R;
import com.example.notetime.RDS.NoteDatabase;
import com.example.notetime.ViewModel.NotepadViewModel;

import java.util.List;

public class Notepad extends Fragment implements View.OnClickListener {

    private NotepadViewModel mViewModel;
    private EditText title, content;
    private Button save;
    private View root;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.notepad_fragment, container, false);


        save = root.findViewById(R.id.savebtn);
        title = root.findViewById(R.id.tv_noteTitle);
        content = root.findViewById(R.id.editText2);
        save = root.findViewById(R.id.savebtn);


        mViewModel = ViewModelProviders.of(getActivity()).get(NotepadViewModel.class);
        mViewModel.getAllNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {


            }
        });
        //save.setOnClickListener(this);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                saveNote();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, new NoteList());
                fragmentTransaction.commit();
            }
        });

        return root;
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(getContext(), "Saved successfully", Toast.LENGTH_SHORT);
    }


    //
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    public void saveNote() {
        String title1 = title.getText().toString();
        String description1 = content.getText().toString();


        Note note = new Note(title1, description1);

        if (title1.isEmpty()) {
            Toast.makeText(getContext(), "please insert Title", Toast.LENGTH_SHORT).show();
            return;
        } else {

            mViewModel.insert(note);
            Toast.makeText(getContext(), "insert works", Toast.LENGTH_SHORT).show();

        }


    }


}


