package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Initial_activity3 extends AppCompatActivity {
    EditText q3,q4,q5;
    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial3);
        q3=findViewById(R.id.q3);
        q4=findViewById(R.id.q4);
        q5=findViewById(R.id.q5);
        next=findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences =getSharedPreferences("USERS",MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();

                String Q3=q3.getText().toString().toLowerCase(Locale.ROOT);
                String Q4=q4.getText().toString().toLowerCase(Locale.ROOT);
                String Q5=q5.getText().toString().toLowerCase(Locale.ROOT);
                if(!Q3.isEmpty() && !Q4.isEmpty()){
                    editor.putString("q3",Q3);
                    editor.putString("q4",Q4);
                    editor.apply();
                    InsertDataInDatabase();

                }
            }
        });
    }

    private void InsertDataInDatabase() {
        Map<String ,Object> map=new HashMap<>();
        SharedPreferences sharedPreferences=getSharedPreferences("USERS",MODE_PRIVATE);
        map.put("name",sharedPreferences.getString("name",""));
        map.put("age",sharedPreferences.getString("age",""));
        map.put("blood",sharedPreferences.getString("blood",""));
        map.put("q1",sharedPreferences.getString("q1",""));
        map.put("q2",sharedPreferences.getString("q2",""));
        map.put("q3",sharedPreferences.getString("q3",""));
        map.put("q4",sharedPreferences.getString("q4",""));
        map.put("q5",sharedPreferences.getString("q5",""));


        String uuid =FirebaseAuth.getInstance().getUid();
        assert uuid != null;
        FirebaseFirestore.getInstance().collection("USERS").document(uuid).set(map)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Initial_activity3.this, "Added to database", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                            finish();
                        }
                        else{
                            Toast.makeText(Initial_activity3.this, "Not Added to database ", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),Initial_actitvity1.class));
                            finish();
                        }
                    }
                });
    }
}