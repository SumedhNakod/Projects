package com.example.adroit.leaveapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    TextInputEditText txtUsername,txtpassword;
    Button btnLogin,btnSignup;
    FirebaseAuth mAuth;
    ProgressBar progbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtpassword = (TextInputEditText) findViewById(R.id.password1);
        txtUsername = (TextInputEditText) findViewById(R.id.sapid1);
        btnLogin = (Button) findViewById(R.id.loginbtn);
        btnSignup=(Button)findViewById(R.id.signbtn);
        progbar=(ProgressBar)findViewById(R.id.progressbar1);
        mAuth=FirebaseAuth.getInstance();

    }
    public void login(View view) {
        String mailid=txtUsername.getText().toString().trim();
        String pass=txtpassword.getText().toString().trim();

        if (TextUtils.isEmpty(txtUsername.getText().toString().trim()))  {
            txtUsername.setError("Fields can't be empty");
            return;

        }
        if ( TextUtils.isEmpty(txtpassword.getText().toString().trim())) {
            txtpassword.setError("Fields can't be empty");
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(mailid).matches()) {
            txtUsername.setError("Please enter a valid email id");
            return;
        }
        if(pass.length()<6) {
            txtpassword.setError("Minimum length of password should be 6");
            return;
        }
        progbar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(mailid,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Login Successfull", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                    startActivity(i);
                    finish();
                }
                else{
                    Toast.makeText(getApplicationContext(),task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
                progbar.setVisibility(View.GONE);

            }
        });

    }

    public void launchClick(View v) {
        Intent intent = null;

        switch(v.getId()) {
            case R.id.signbtn:
                intent = new Intent(LoginActivity.this, SignupActivity.class);
                break;

        }
        startActivityForResult(intent, 0);
    }

}
