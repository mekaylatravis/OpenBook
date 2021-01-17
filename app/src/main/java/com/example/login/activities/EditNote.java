package com.example.login.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.login.activities.Notes;
import com.example.login.database.Dao;
import com.example.login.database.NotesDatabase;
import com.example.login.R;

import java.util.Date;

public class EditNote extends AppCompatActivity {
    private EditText input;
    private Dao dao;
    private Notes temp;
    public static final String NOTE_EXTRA_Key = "note_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);
        // Toolbar toolbar = findViewById(R.id.edit_note_activity_toolbar);
        // setSupportActionBar(toolbar);

        input = findViewById(R.id.input);
        dao = NotesDatabase.getInstance(this).dao();
        if (getIntent().getExtras() != null) {
            int id = getIntent().getExtras().getInt(NOTE_EXTRA_Key, 0);
            temp = dao.getIdNotes(id);
            input.setText(temp.getText());
        } else input.setFocusable(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.notes_edit, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.save)
            onSaveNote();
        return super.onOptionsItemSelected(item);
    }

    private void onSaveNote() {
        // TODO: 20/06/2018 Save Note
        String text = input.getText().toString();
        if (!text.isEmpty()) {
            long date = new Date().getTime(); // get  system time
            // if  exist update els crete new
            if (temp == null) {
                temp = new Notes(text, date);
                dao.insert(temp); // create new note and inserted to database
            } else {
                temp.setText(text);
                temp.setText_date(date);
                dao.update(temp); // change text and date and update note on database
            }

            finish(); // return to the MainActivity
        }

    }
}



/*
public class EditNote extends AppCompatActivity {
    private EditText input;
    private Dao dao;
    private Notes temp;
    public static final String NOTE_EXTRA_Key = "note_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);
       // Toolbar toolbar = findViewById(R.id.edit_note_activity_toolbar);
        //setSupportActionBar(toolbar);

        input = findViewById(R.id.input);
        dao = NotesDatabase.getInstance(this).dao();
        if(getIntent().getExtras() != null){
            int id = getIntent().getExtras().getInt(NOTE_EXTRA_Key, 0);
            temp = dao.getIdNotes(id);

            input.setText(temp.getText());
        } else {
            temp = new Notes();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.notes_edit, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.save)
            onSaveNote();
        return super.onOptionsItemSelected(item);
    }

    private void onSaveNote() {
        // TODO: 12/28/2020 Save Note
        String text = input.getText().toString();
        if (!text.isEmpty()) {
            long date = new Date().getTime(); // get  system time

            temp.setText_date(date);
            temp.setText(text);

            if(temp.getId() == -1){
                dao.insert(temp);
            } else {
                dao.update(temp);
            }
            //Notes note = new Notes(text, date);

            finish(); // return to the MainActivity
        }

    }
}
 */