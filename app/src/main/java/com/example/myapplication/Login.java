package com.example.myapplication;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Login extends AppCompatActivity {
    private FirebaseAuth mAuth;
    EditText email,password;
    Button signinbtn,loginButton;
    FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        email=findViewById(R.id.login_username);
        password=findViewById(R.id.login_password);
        signinbtn=findViewById(R.id.signinbutton);
        loginButton=findViewById(R.id.SignUpbutton);
        firebaseFirestore=FirebaseFirestore.getInstance();
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Signup.class));
                finish();
            }
        });
        signinbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Email=email.getText().toString();
                String Password=password.getText().toString();
                if(!Email.isEmpty() && !Password.isEmpty())
                {
                    login(Email,Password);
                }
                else{
                    Toast.makeText(Login.this, "Please fill the form correctly", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void login(String email,String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            FirebaseUser user = mAuth.getCurrentUser();
                            if(user.isEmailVerified()){
                                isFirstTime();
//                                startActivity(new Intent(getApplicationContext(),MainActivity.class));
//                                finish();
                            }
                            else{
                                Toast.makeText(Login.this, "Please Verify your email first",
                                        Toast.LENGTH_SHORT).show();
                            }

                        } else {
                            // If sign in fails, display a message to the user.

                            Toast.makeText(Login.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void isFirstTime() {
        SharedPreferences sharedPreferences =getSharedPreferences("USERS",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("name","");
        editor.putString("age","");
        editor.putString("gender","");
        editor.putString("blood","");
        editor.putString("q1","");
        editor.putString("q2","");
        editor.putString("q3","");
        editor.putString("q4","");
        editor.putString("q5","");
        editor.apply();



        String uuid =FirebaseAuth.getInstance().getUid();
        firebaseFirestore.collection("USERS").document(uuid).get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        DocumentSnapshot documentSnapshot=task.getResult();
                        if(documentSnapshot.exists()){
                            Toast.makeText(Login.this, "User exist", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                            finish();
                        }
                        else{
                            startActivity(new Intent(getApplicationContext(),Initial_actitvity1.class));
                            finish();
                            //Toast.makeText(Login.this, "users does not exist", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
}