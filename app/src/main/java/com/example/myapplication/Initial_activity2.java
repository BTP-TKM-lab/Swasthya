package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Locale;

public class Initial_activity2 extends AppCompatActivity {
    EditText q1,q2;
    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial2);
        q1=findViewById(R.id.q1);
        q2=findViewById(R.id.q2);
        next=findViewById(R.id.next);
        SharedPreferences sharedPreferences =getSharedPreferences("USERS",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Q1=q1.getText().toString().toLowerCase(Locale.ROOT);
                String Q2=q2.getText().toString().toLowerCase(Locale.ROOT);
                if(!Q1.isEmpty() && !Q2.isEmpty()){
                    editor.putString("q1",Q1);
                    editor.putString("q2",Q2);
                    editor.apply();
                    startActivity(new Intent(getApplicationContext(),Initial_activity3.class));
                }
            }
        });

    }
}