package com.example.login.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.example.login.R;
import com.example.login.adapters.NoteAdapter;
import com.example.login.cb.NoteListener;
import com.example.login.database.Dao;
import com.example.login.database.NotesDatabase;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import static com.example.login.activities.EditNote.NOTE_EXTRA_Key;

public class Journal extends AppCompatActivity implements NoteListener {

    private RecyclerView rec;
    private ArrayList<Notes> notes;
    private NoteAdapter noteAdapter;
    private Dao dao;
    //private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        rec = findViewById(R.id.list);
        rec.setLayoutManager(new LinearLayoutManager(this));

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: 12/27/2020  add new note
                addNote();
            }
        });

        dao = NotesDatabase.getInstance(this).dao();
    }

    private void load() {

        this.notes = new ArrayList<>();
        List<Notes> list = dao.getNote();
        this.notes.addAll(list);
        this.noteAdapter = new NoteAdapter(this, this.notes);
        this.noteAdapter.setNoteListener(this);
        this.rec.setAdapter(noteAdapter);

        swipeToDelete.attachToRecyclerView(rec);

    }

    private void addNote() {
        // TODO: 12/28/2020  start EditNote
        startActivity(new Intent(this, EditNote.class));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        load();
    }

    @Override
    public void noteClick(Notes notes) {
        // TODO: 12/28/2020 note click to edit
        //Toast.makeText(this, notes.getId(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, EditNote.class);
        intent.putExtra(NOTE_EXTRA_Key, notes.getId());
        startActivity(intent);
    }

    @Override
    public void noteLongClick(Notes notes) {
        // TODO: 12/28/2020 note long clicked to delete or share
     /*
     new AlertDialog.Builder(this)
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i){
                        dialogInterface.dismiss();
                    }
                });
      */
    }

    private ItemTouchHelper swipeToDelete = new ItemTouchHelper(
            new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                @Override
                public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                    return false;
                }

                @Override
                public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                    // TODO: 28/09/2018 delete note when swipe

                    if (notes != null) {
                        // get swiped note
                        Notes note = notes.get(viewHolder.getAdapterPosition());
                        if (note != null) {
                            delete(note, viewHolder);

                        }

                    }
                }
            });

    private void delete (final Notes note, final RecyclerView.ViewHolder view) {
        new AlertDialog.Builder(Journal.this)
                .setMessage("Delete Note?")
                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // TODO: 28/09/2018 delete note
                        dao.delete(note);
                        notes.remove(note);
                        noteAdapter.notifyItemRemoved(view.getAdapterPosition());
                        //showEmptyView();

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // TODO: 1/13/2021 Notes delete swipe
                        rec.getAdapter().notifyItemChanged(view.getAdapterPosition());


                    }
                })
                .setCancelable(false)
                .create().show();
    }

    /*
    private void showEmptyView() {
        if (notes.size() == 0) {
            this.rec.setVisibility(View.GONE);
            findViewById(R.id.empty_notes_view).setVisibility(View.VISIBLE);

        } else {
            this.rec.setVisibility(View.VISIBLE);
            findViewById(R.id.empty_notes_view).setVisibility(View.GONE);
        }
    }
     */
}