package com.example.demoapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText username,password;
    Button login;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.editText);
        password = findViewById(R.id.editText2);
        login = findViewById(R.id.button);


        sharedPreferences = getSharedPreferences("Detail", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username", "Admin");
        editor.putString("password","123456");

        editor.commit();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String usrname = username.getText().toString().trim();
                String passwrd = password.getText().toString().trim();
                if(usrname.equals("Admin")&& passwrd.equals("123456")){
                    startActivity(new Intent(MainActivity.this,HomeActivity.class));
                }

            }
        });
    }
}