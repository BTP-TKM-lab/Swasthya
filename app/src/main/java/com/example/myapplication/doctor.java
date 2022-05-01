package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class doctor extends AppCompatActivity {
    TextView name,phone,name2,phone2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);
        name=findViewById(R.id.doctor_name);
        phone=findViewById(R.id.doctor_phone_no);
        name2=findViewById(R.id.doctor_name2);
        phone2=findViewById(R.id.doctor_phone_no2);
        name.setText("Dr Ashok Gupta ");
        phone.setText("9876543210");
        name2.setText("Dr Ashok Gupta ");
        phone2.setText("9876543210");



    }
}