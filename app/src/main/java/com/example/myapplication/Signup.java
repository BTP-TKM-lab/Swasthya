package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldPath;
import com.google.firebase.firestore.FirebaseFirestore;

public class Signup extends AppCompatActivity {
    private FirebaseAuth mAuth;
    EditText email,password,confirmpassword,firstname,lastname;
    Button signupbtn,loginButton;
    FirebaseFirestore firebaseFirestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebaseFirestore= FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            isFirstTime();
        }
        setContentView(R.layout.activity_signup);
        email=findViewById(R.id.emailid);
        password=findViewById(R.id.password);
        confirmpassword=findViewById(R.id.confirmpassword);
        signupbtn=findViewById(R.id.signupbutton);
        firstname=findViewById(R.id.firstname);
        lastname=findViewById(R.id.lastname);
        loginButton=findViewById(R.id.Loginbutton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Login.class));
                finish();
            }
        });
        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Email=email.getText().toString();
                String Password=password.getText().toString();
                String ConfirmPassword=confirmpassword.getText().toString();
                String Firstname=firstname.getText().toString();
                String Lastname=lastname.getText().toString();
                if(!Firstname.isEmpty() && !Lastname.isEmpty() && !Email.isEmpty() && !Password.isEmpty() && !ConfirmPassword.isEmpty() && ConfirmPassword.equals(Password))
                {
                    signup(Email,Password,Firstname,Lastname);
                }
                else{
                    Toast.makeText(Signup.this, "Please fill the form correctly", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }

    private void signup(String email,String password,String Firstname,String Lastname) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("TAG", "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            sendVerificationEmail();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("TAG", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(Signup.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

//    @Override
//    public void onStart() {
//        super.onStart();
//        // Check if user is signed in (non-null) and update UI accordingly.
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        if(currentUser != null){
//            isFirstTime();
//        }
//    }
    private void isFirstTime() {
        String uuid =FirebaseAuth.getInstance().getUid();
        firebaseFirestore.collection("USERS").document(uuid).get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        DocumentSnapshot documentSnapshot=task.getResult();
                        if(documentSnapshot.exists()){
                            Toast.makeText(Signup.this, "User exist", Toast.LENGTH_SHORT).show();
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
    private void sendVerificationEmail()
    {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        user.sendEmailVerification()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            // email sent
                            Toast.makeText(Signup.this, "Email has been sent to mail box", Toast.LENGTH_SHORT).show();

                            // after email is sent just logout the user and finish this activity
                            FirebaseAuth.getInstance().signOut();
                        }
                        else
                        {
                            // email not sent, so display message and restart the activity or do whatever you wish to do

                            //restart this activity
                            overridePendingTransition(0, 0);
                            finish();
                            overridePendingTransition(0, 0);
                            startActivity(getIntent());

                        }
                    }
                });
    }

}