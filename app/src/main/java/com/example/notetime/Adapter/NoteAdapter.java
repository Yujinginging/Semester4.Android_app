package com.example.notetime.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notetime.Model.Note;
import com.example.notetime.R;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteHolder> {

    private List<Note> mnotes;

    private OnNoteListener listener;

    public NoteAdapter(ArrayList<Note> notes){
        mnotes = notes;
    }
    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.notepad_listitem, parent, false);
        return new NoteHolder(itemView);
    }

    @Override
    public void onBindViewHolder(NoteHolder holder, final int position) {
        Note currentNote = mnotes.get(position);
        holder.tTitle.setText(currentNote.getTitle());
        holder.tContent.setText(currentNote.getContent());
    }

    @Override
    public int getItemCount() {
        return mnotes.size();
    }

    public void setMnotes(List<Note> mnotes) {
        this.mnotes = mnotes;
        notifyDataSetChanged();
    }

    public Note getNoteAt(int position){
        return mnotes.get(position);
    }

    class NoteHolder extends RecyclerView.ViewHolder {
        private TextView tTitle, tContent;

        NoteHolder(View itemView) {
            super(itemView);
            tTitle = itemView.findViewById(R.id.textView_notetitle);
            tContent = itemView.findViewById(R.id.textView_notecontent);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION){
                        listener.onNoteClick(getNoteAt(position));
                    }
                }
            });

        }
    }

    public interface OnNoteListener{
        void onNoteClick(Note note);
    }
    public void setOnNoteClickListener(OnNoteListener listener){
        this.listener = listener;
    }
}