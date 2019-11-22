package com.example.notetime.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "note_table")
public class Note {

    @PrimaryKey(autoGenerate = true)
    private int noteId;

    private String title;
    private String content;

    public Note(String title,String content){

        this.title = title;
        this.content = content;
    }

    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Note)) return false;

        Note note = (Note) o;

        if (noteId != note.noteId) return false;
        return title != null ? title.equals(note.title) : note.title == null;
    }



    @Override
    public int hashCode() {
        int r = noteId;
        r = 31 * r+ (title != null ? title.hashCode() : 0);
        return r;
    }

    @Override
    public String toString() {
        return "Note{" +
                "Note ID=" + noteId + ", title='" + title + '\'' +
                ", content='" + content + '\'' + '}';
    }
}
