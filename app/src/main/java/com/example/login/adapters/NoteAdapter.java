package com.example.login.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.login.R;
import com.example.login.activities.Notes;
import com.example.login.cb.NoteListener;
import com.example.login.utilities.Utilities;

import java.util.ArrayList;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.TextHolder>{
    private Context context;

    private ArrayList<Notes> notes;
    private NoteListener noteListener;

    public NoteAdapter(Context context, ArrayList<Notes> notes) {
        this.context = context;
        this.notes = notes;
    }

    @Override
    public TextHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.notes, parent, false);


        return new TextHolder(view);
    }

    @Override
    public void onBindViewHolder(TextHolder holder, int position) {
        Notes note = get_notes(position);
        if( note != null){
            holder.text.setText(note.getText());
            holder.data.setText(Utilities.long_data(note.getText_date()));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    noteListener.noteClick(note);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    noteListener.noteLongClick(note);
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    private Notes get_notes(int position){
        return notes.get(position);
    }

    class TextHolder extends RecyclerView.ViewHolder {
        TextView text, data;

        public TextHolder(View itemView) {
            super(itemView);
            data = itemView.findViewById(R.id.date);
            text = itemView.findViewById(R.id.text);
        }
    }

    public void setNoteListener(NoteListener noteListener) {
        this.noteListener = noteListener;
    }
}

