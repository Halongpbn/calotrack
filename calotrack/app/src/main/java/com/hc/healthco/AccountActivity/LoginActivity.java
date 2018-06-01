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
import com.google.firebase.auth.FirebaseUser;
import com.hc.healthco.MainActivity;
import com.hc.healthco.R;

public class LoginActivity extends AppCompatActivity {
    private Button loginButton;
    private ProgressBar loginProgress;
    private TextView userLogin;
    private EditText email, password;
    private int counter = 5;
    private TextView error;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.login_mail);
        password = findViewById(R.id.login_password);
        loginButton = findViewById(R.id.login_button);
        loginProgress = findViewById(R.id.login_progress);
        error =  findViewById(R.id.error_message);
        loginProgress.setVisibility(View.INVISIBLE);
        setUpUIViews();
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if(user != null)
        {
            finish();
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
        }
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginProgress.setVisibility(View.VISIBLE);
                loginButton.setVisibility(View.INVISIBLE);
                validate(email.getText().toString(), password.getText().toString());
            }
        });
        userLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
            }
        });

    }
    private void setUpUIViews()
    {
        userLogin = (TextView) findViewById(R.id.textView);
    }
    private void validate(String userName, String pass)
    {
        firebaseAuth.signInWithEmailAndPassword(userName, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                    counter--;
                    if(counter == 0)
                    {
                        loginButton.setEnabled(false);
                        Toast.makeText(LoginActivity.this, "You've failed to sign in too many times, please try again later.", Toast.LENGTH_LONG);
                    }
                }
            }
        });
    }
}
