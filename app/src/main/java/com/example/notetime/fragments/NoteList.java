package com.example.notetime.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.ListFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notetime.Adapter.NoteAdapter;
import com.example.notetime.MainActivity;
import com.example.notetime.Model.Note;
import com.example.notetime.R;
import com.example.notetime.ViewModel.NotepadViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class NoteList extends ListFragment implements NoteAdapter.OnNoteListener {

    private NotepadViewModel mViewModel;
    private View root;
    private FloatingActionButton addbtn;
    private RecyclerView recyclerView;
    private ArrayList<Note> notearray = new ArrayList<>();



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.note_list_fragment, container, false);

        return root;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {


        addbtn = root.findViewById(R.id.floatingActionButton2);
        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, new Notepad());
                fragmentTransaction.commit();
            }
        });

        //test
       /* notearray.add(new Note("Monday","cannot wake up TAT"));
        notearray.add(new Note("Tuesday","cannot wake up TAT"));
        notearray.add(new Note("Wednsday","cannot wake up TAT"));
        notearray.add(new Note("Thursday","cannot wake up TAT"));*/



        recyclerView = root.findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        notearray = new ArrayList<>();

        final NoteAdapter adapter = new NoteAdapter(notearray);
        recyclerView.setAdapter(adapter);

        mViewModel = ViewModelProviders.of(getActivity()).get(NotepadViewModel.class);
        mViewModel.getAllNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {

               adapter.setMnotes(notes);

            }
        });


        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                mViewModel.delete(adapter.getNoteAt(viewHolder.getAdapterPosition()));
                Toast.makeText(getActivity(), "Note deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);

    }

    @Override
    public void onNoteClick(Note note) {

    }
}