package com.example.login.activities;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "notes")
public class Notes {

    @PrimaryKey (autoGenerate = true)
    private int id;

    @ColumnInfo(name = "text")
    private String text;

    @ColumnInfo(name = "date")
    private long text_date;

    public Notes() {}
//    @Ignore
    //  private boolean checked = false;

    public Notes(String text, long text_date) {
        this.text = text;
        this.text_date = text_date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getText_date() {
        return text_date;
    }

    public void setText_date(long text_date) {
        this.text_date = text_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Notes{" +
                "id=" + id +
                ", text_date=" + text_date +
                '}';
    }
}