package com.example.notetime.RDS;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.notetime.Model.Note;


import java.util.List;

public class NoteRepository {
    private NoteDAO noteDAO;
    private static NoteRepository instance;
    private LiveData<List<Note>> allNotes;


    public NoteRepository(Application application) {
        NoteDatabase noteDatabase = NoteDatabase.getInstance(application);
        noteDAO = noteDatabase.getnoteDAO();
        allNotes = noteDAO.getAllNotes();
    }



   public static synchronized NoteRepository getInstance(Application application){
        if(instance == null)
            instance = new NoteRepository(application);

        return instance;
    }
    public LiveData<List<Note>> getAllNotes(){
        return allNotes;
    }

    public void insert(Note note) {
        new InsertNoteAsync(noteDAO).execute(note);
    }

    public void deleteAllNotes(){
        new DeleteAllNotesAsync(noteDAO).execute();
    }

    public void update(Note note) {
        new UpdateNoteAsync(noteDAO).execute(note);
    }

    public void delete(Note note) {
        new DeleteNoteAsync(noteDAO).execute(note);
    }
    private static class InsertNoteAsync extends AsyncTask<Note,Void,Void> {
        private NoteDAO noteDao;

        private InsertNoteAsync(NoteDAO noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.insert(notes[0]);
            return null;
        }
    }

    private static class DeleteAllNotesAsync extends AsyncTask<Void,Void,Void> {
        private NoteDAO noteDao;

        private DeleteAllNotesAsync(NoteDAO noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.deleteAllNotes();
            return null;
        }
    }

    private static class UpdateNoteAsync extends AsyncTask<Note, Void, Void> {
        private NoteDAO noteDao;

        private UpdateNoteAsync(NoteDAO noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.update(notes[0]);
            return null;
        }
    }

    private static class DeleteNoteAsync extends AsyncTask<Note, Void, Void> {
        private NoteDAO noteDao;

        private DeleteNoteAsync(NoteDAO noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.delete(notes[0]);
            return null;
        }
    }


}
