package com.example.notetime.ViewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.notetime.Model.Note;
import com.example.notetime.RDS.NoteRepository;

import java.util.List;

public class LogoutViewModel extends AndroidViewModel {
    // TODO: Implement the ViewModel
    private NoteRepository noteRepository;
    private LiveData<List<Note>> notes;

    public LogoutViewModel(Application application) {
        super(application);
        noteRepository =NoteRepository.getInstance(application);
        notes = noteRepository.getAllNotes();

    }


    public void delete(Note note) {
        noteRepository.delete(note);
    }

    public LiveData<List<Note>> getAllNotes() {
        return notes;
    }


    public void deleteAllNotes() {
        noteRepository.deleteAllNotes();
    }
}
