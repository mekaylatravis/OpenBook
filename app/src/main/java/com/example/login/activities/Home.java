package com.example.login.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.login.database.DB_Help;
import com.example.login.R;

public class Home extends AppCompatActivity {

    EditText user, code;
    Button signin, signup, lock;
    DB_Help DATA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        user = (EditText) findViewById(R.id.userr);
        code = (EditText) findViewById(R.id.codee);
        signin = (Button) findViewById(R.id.signinn);
        signup = (Button) findViewById(R.id.signupp);
        //lock = (Button) findViewById(R.id.lock);
        DATA = new DB_Help(this);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String usern = user.getText().toString();
                String pw = code.getText().toString();

                if (usern.equals("") || pw.equals("")) {
                    Toast.makeText(Home.this, "Please enter in all information", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean check = DATA.checkboth(usern, pw);
                    if (check == true) {
                        Toast.makeText(Home.this, "Successful login!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), Journal.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(Home.this, "Username or password are incorrect", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this, Signin.class));
            }
        });

    /*
        lock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Signin.class));
            }
        });

         */
    }
}
