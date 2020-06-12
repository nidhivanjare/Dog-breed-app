package com.example.dog_breed;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Sign_in extends AppCompatActivity {

    EditText emailId, password;
    Button btnSignIn;
    TextView tvsignin;
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);


        mFirebaseAuth = FirebaseAuth.getInstance();
        emailId = findViewById(R.id.emailId1);
        password = findViewById(R.id.password1);
        btnSignIn = findViewById(R.id.sign_in1);
        tvsignin = findViewById(R.id.sign_up1);

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
                if (mFirebaseUser != null) {
                    Toast.makeText(Sign_in.this, "YOU ARE LOGGED IN", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(Sign_in.this, Home_page.class);
                    startActivity(i);
                } else {
                    Toast.makeText(Sign_in.this, "PLEASE LOGIN", Toast.LENGTH_SHORT).show();
                }
            }
        };


        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = emailId.getText().toString();
                String pwd = password.getText().toString();
                if (email.isEmpty()) {
                    emailId.setError("Please enter the email Id");
                    emailId.requestFocus();
                } else if (pwd.isEmpty()) {
                    password.setError("Please enter the password");
                    password.requestFocus();
                } else if (email.isEmpty() && pwd.isEmpty()) {
                    Toast.makeText(Sign_in.this, "Fields Are Empty", Toast.LENGTH_SHORT).show();
                } else if (!(email.isEmpty() && pwd.isEmpty())) {

                    mFirebaseAuth.signInWithEmailAndPassword(email, pwd).addOnCompleteListener(Sign_in.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(Sign_in.this, "LOGIN ERROR , TRY AGAIN ", Toast.LENGTH_SHORT).show();
                            } else {
                                Intent i1 = new Intent(Sign_in.this, Home_page.class);
                                startActivity(i1);
                            }
                        }
                    });
                } else {
                    Toast.makeText(Sign_in.this, "ERROR", Toast.LENGTH_SHORT).show();
                }
            }
        });

        tvsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2 = new Intent(Sign_in.this, Sign_up.class);
                startActivity(i2);
            }
        });
    }

    protected void onStart() {
        super.onStart();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);

    }


}