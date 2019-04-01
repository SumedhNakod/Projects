package com.example.adroit.leaveapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;

public class SignupActivity extends AppCompatActivity {
    EditText name, lastname, txtemail, txtpassword, conpassword;
    Button signup;
    ProgressBar prog;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        name=(EditText)findViewById(R.id.signup_input_layout_firstname);
        lastname=(EditText)findViewById(R.id.signup_input_lastname);
        txtemail =(EditText)findViewById(R.id.sapid);
        txtpassword=(EditText)findViewById(R.id.password);
        conpassword=(EditText)findViewById(R.id.password1);
        signup=(Button)findViewById(R.id.signupac);
        prog=(ProgressBar)findViewById(R.id.progressbar);
        mAuth = FirebaseAuth.getInstance();
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
                Intent intent= new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void registerUser() {
        String username = txtemail.getText().toString().trim();
        String pswrd = txtpassword.getText().toString().trim();

        if (TextUtils.isEmpty(txtemail.getText().toString().trim())) {
            txtemail.setError("Fields can't be empty");
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(username).matches()) {
            txtemail.setError("Please enter a valid email id");
            return;
         }
        if (TextUtils.isEmpty(txtpassword.getText().toString().trim())) {
            txtpassword.setError("Fields can't be empty");
            return;
        }
        if(pswrd.length()<6) {
            txtpassword.setError("Minimum length of password should be 6");
            return;
        }
        if (TextUtils.isEmpty(name.getText().toString().trim())) {
            name.setError("Fields can't be empty");
            return;
        }
        if (TextUtils.isEmpty(lastname.getText().toString().trim())) {
            lastname.setError("Fields can't be empty");
            return;
        }
        if (TextUtils.isEmpty(conpassword.getText().toString().trim())) {
            conpassword.setError("Fields can't be empty");
            return;
        }
        prog.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(username,pswrd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                prog.setVisibility(View.GONE);
                if(task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(),"User Registered Successfully",Toast.LENGTH_SHORT).show();



                }
                else
                {
                    if(task.getException()instanceof FirebaseAuthUserCollisionException){
                        Toast.makeText(getApplicationContext(),"User Already Registered",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });




    }
}


