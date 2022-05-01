package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.nio.charset.StandardCharsets;
import java.util.Locale;

public class Initial_actitvity1 extends AppCompatActivity {
    EditText name,age,blood_group,gender;
    Button next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_actitvity1);
        name=findViewById(R.id.edit_name);
        age=findViewById(R.id.edit_age);
        blood_group=findViewById(R.id.edit_bloodgroup);
        gender=findViewById(R.id.edit_gender);
        next=findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Name=name.getText().toString().toLowerCase(Locale.ROOT);
                String Age=age.getText().toString().toLowerCase(Locale.ROOT);
                SharedPreferences sharedPreferences =getSharedPreferences("USERS",MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();

                String Blood=blood_group.getText().toString().toLowerCase(Locale.ROOT);
                String Gender=gender.getText().toString().toLowerCase(Locale.ROOT);

                if(!Name.isEmpty() && !Age.isEmpty() && !Blood.isEmpty() && !Gender.isEmpty() ){
                    editor.putString("name",Name);
                    editor.putString("age",Age);
                    editor.putString("blood",Blood);
                    editor.putString("gender",Gender);
                    editor.apply();
                    startActivity(new Intent(getApplicationContext(),Initial_activity2.class));
                }
            }
        });

    }
}