package com.example.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void courses(View view) {
        Intent intent = new Intent(this,Courses.class);
        startActivity(intent);
    }

    public void adv(View view) {
        Intent intent = new Intent(this,ADV.class);
        startActivity(intent);
    }

    public void info(View view) {
        Intent intent = new Intent(this,info.class);
        startActivity(intent);
    }

    public void home(View view) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    public void user(View view) {
        Intent intent = new Intent(this,Login.class);
        startActivity(intent);
    }

}