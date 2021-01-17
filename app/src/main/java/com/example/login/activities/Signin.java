package com.example.login.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.SigningInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.login.database.DB_Help;
import com.example.login.R;

public class Signin extends AppCompatActivity {

    EditText user, code, code_again;
    Button signup, signin;
    DB_Help DATA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        user = (EditText) findViewById(R.id.user);
        code = (EditText) findViewById(R.id.code);
        code_again = (EditText) findViewById(R.id.code_again);
        signup = (Button) findViewById(R.id.signup);
        signin = (Button) findViewById(R.id.signin);
        DATA = new DB_Help(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usern = user.getText().toString();
                String pw = code.getText().toString();
                String pw_again = code_again.getText().toString();

                if(usern.equals("") || pw.equals("") || pw_again.equals("")) {
                    Toast.makeText(Signin.this, "Please enter in all information", Toast.LENGTH_SHORT).show();
                } else {
                    if(pw.equals(pw_again)) {
                        Boolean check = DATA.check(usern);
                        if(check == false) {
                            Boolean insert = DATA.insertData(usern, pw);
                            if(insert == true) {
                                Toast.makeText(Signin.this, "Successful registration!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), Journal.class);
                            }else {
                                Toast.makeText(Signin.this, "Unsuccessful registration. Try again", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(Signin.this, "User already exists!", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(Signin.this, "Passwords do not match!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

    }
}