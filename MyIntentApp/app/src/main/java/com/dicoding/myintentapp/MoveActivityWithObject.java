package com.dicoding.myintentapp;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.dicoding.myintentapp.model.Person;

public class MoveActivityWithObject extends AppCompatActivity {
    public static final String EXTRA_PERSON = "extra_person";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move_with_object);
        TextView tvObject = findViewById(R.id.tv_object_received);

        Person person = getIntent().getParcelableExtra(EXTRA_PERSON);
        String text = "Name : "+person.getName()+",\nEmail : "+person.getEmail()+",\nAge : "+person.getAge()+",\nLocation : "+person.getCity();
        tvObject.setText(text);
    }
}
