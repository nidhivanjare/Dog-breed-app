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

public class Sign_up extends AppCompatActivity {

    EditText emailId , password;
    Button btnSignUp;
    TextView tvsignup;
    FirebaseAuth mFirebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mFirebaseAuth = FirebaseAuth.getInstance();
        emailId = findViewById(R.id.emailId);
        password = findViewById(R.id.password);
        btnSignUp = findViewById(R.id.sign_up);
        tvsignup =findViewById(R.id.sign_in);



        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String email = emailId.getText().toString();
                String pwd =   password.getText().toString();
                if(email.isEmpty()){
                    emailId.setError("Please enter the email Id");
                    emailId.requestFocus();
                }
                else if(pwd.isEmpty()){
                    password.setError("Please enter the password");
                    password.requestFocus();
                }
                else if(email.isEmpty() && pwd.isEmpty()){
                    Toast.makeText(Sign_up.this,"Fields Are Empty",Toast.LENGTH_SHORT).show();
                }
                else if(!(email.isEmpty() && pwd.isEmpty())){

                    mFirebaseAuth.createUserWithEmailAndPassword(email,pwd).addOnCompleteListener(Sign_up.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(Sign_up.this,"SIGN UP UNSUCCESSFUL , TRY AGAIN !",Toast.LENGTH_SHORT).show();
                            }
                            else {
                                startActivity(new Intent(Sign_up.this,Home_page.class));
                            }
                        }
                    });

                }
                else {
                    Toast.makeText(Sign_up.this,"ERROR",Toast.LENGTH_SHORT).show();
                }
            }
        });


        tvsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i3 = new Intent(Sign_up.this,Sign_in.class);
                startActivity(i3);
            }
        });


    }


}