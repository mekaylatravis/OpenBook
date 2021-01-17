package com.example.login.database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DB_Help extends SQLiteOpenHelper {
        public static final String DBNAME = "Login.db";
        public DB_Help(Context context) {
            super(context, "Login.db", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase MyDB) {
            MyDB.execSQL("create Table users(username TEXT primary key, password TEXT)");
        }

        @Override
        public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
            MyDB.execSQL("drop Table if exists users");
        }

        public Boolean insertData(String username, String password){
            SQLiteDatabase MyDB = this.getWritableDatabase();
            ContentValues contentValues= new ContentValues();
            contentValues.put("username", username);
            contentValues.put("password", password);
            long result = MyDB.insert("users", null, contentValues);
            if(result==-1) return false;
            else
                return true;
        }

        public Boolean check(String username) {
            SQLiteDatabase MyDB = this.getWritableDatabase();
            Cursor cursor = MyDB.rawQuery("Select * from users where username = ?", new String[]{username});
            if (cursor.getCount() > 0)
                return true;
            else
                return false;
        }

        public Boolean checkboth(String username, String password){
            SQLiteDatabase MyDB = this.getWritableDatabase();
            Cursor cursor = MyDB.rawQuery("Select * from users where username = ? and password = ?", new String[] {username,password});
            if(cursor.getCount()>0)
                return true;
            else
                return false;
        }
    }
    /*

    public static final String DB = "Signin.db";

    public DB_Help(Context context) {
        super(context, "Signin.db", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table users(Username TEXT primary key, Password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists users");
    }

    public Boolean insert(String user, String code) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("Username", user);
        contentValues.put("Password", code);
        long login = db.insert("users", null, contentValues);

        if(login != -1){
            return true;
        } else {
            return false;
        }
    }
    public Boolean check(String user){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cur = db.rawQuery("Select * from users where user = ?", new String[] {user});

        if(cur.getCount() >= 1){
            return true;
        } else {
            return false;
        }
    }

    public Boolean checkboth(String user, String code){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cur = db.rawQuery("Select * from users where user = ? and code = ?", new String[] {user, code});

        if(cur.getCount() >= 1){
            return true;
        } else {
            return false;
        }
    }
     */