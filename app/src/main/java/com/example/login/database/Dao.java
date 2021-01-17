package com.example.login.database;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.login.activities.Notes;

import java.util.ArrayList;
import java.util.List;

@androidx.room.Dao
public interface Dao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Notes notes);

    @Delete
    void delete(Notes notes);

    @Update
    void update(Notes note);

    @Query("SELECT * FROM notes")
    List<Notes> getNote();

    @Query("SELECT * FROM notes WHERE id = :idNote")
    Notes getIdNotes(int idNote);

    @Query("DELETE FROM notes WHERE id = :idNote")
    void deleteID(int idNote);
}
