package com.hc.healthco.AccountActivity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.hc.healthco.R;
import com.hc.healthco.SurveyActivity;

public class SignUpActivity extends AppCompatActivity {
    private EditText regUser, regPass, regEmail;
    private Button regButton;
    private FirebaseAuth firebaseAuth;
    private TextView userSignUp;
    private ProgressBar registerProgress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        registerProgress = findViewById(R.id.registerProgress);
        registerProgress.setVisibility(View.INVISIBLE);
        setUpUIViews();
        firebaseAuth = FirebaseAuth.getInstance();
        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validate())
                {
                    String userUser = regUser.getText().toString().trim();
                    String userPass = regPass.getText().toString().trim();
                    String userEmail = regEmail.getText().toString().trim();

                    firebaseAuth.createUserWithEmailAndPassword(userEmail, userPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            regButton.setVisibility(View.INVISIBLE);
                            registerProgress.setVisibility(View.VISIBLE);
                            if (task.isSuccessful())
                            {
                                firebaseAuth.signOut();
                                Toast.makeText(SignUpActivity.this, "Registration Complete, please answer some questions first", Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(SignUpActivity.this, SurveyActivity.class));
                            }
                            else
                            {
                                regButton.setVisibility(View.VISIBLE);
                                registerProgress.setVisibility(View.INVISIBLE);
                                Toast.makeText(SignUpActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
        userSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUpActivity.this, SurveyActivity.class));
            }
        });
    }
    private void setUpUIViews()
    {
        regUser = (EditText) findViewById(R.id.regUser);
        regEmail = (EditText) findViewById(R.id.regEmail);
        regPass = (EditText) findViewById(R.id.regPass);
        regButton = (Button) findViewById(R.id.register_button);
        userSignUp = (TextView) findViewById(R.id.login_link);
    }
    private Boolean validate()
    {
        Boolean result = false;
        String email = regEmail.getText().toString();
        String user = regUser.getText().toString();
        String pass = regPass.getText().toString();
        if (user.isEmpty() || email.isEmpty() || pass.isEmpty()) {
            Toast.makeText(this, "Please enter all details", Toast.LENGTH_SHORT).show();
        }
        else
        {
            result = true;
        }
        return result;
    }
}
