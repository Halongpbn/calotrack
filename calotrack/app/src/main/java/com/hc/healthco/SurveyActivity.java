package com.hc.healthco;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hc.healthco.AccountActivity.LoginActivity;
import com.hc.healthco.AccountActivity.SignUpActivity;

public class SurveyActivity extends AppCompatActivity {
    private Button submitButton;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase database;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);

        submitButton = findViewById(R.id.submit_button);
        firebaseAuth = FirebaseAuth.getInstance();
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addUser();
                startActivity(new Intent(SurveyActivity.this, MainActivity.class));
            }
        });
    }
    private void addUser()
    {
        double bmr = calcBMR();
        ref = FirebaseDatabase.getInstance().getReference("users");
        String id = ref.push().getKey();
        User user = new User(id, bmr);
        DatabaseReference childRef = ref.child(id);
        childRef.setValue(bmr);
    }


    private double calcBMR() {

        TextInputLayout tilAge = (TextInputLayout) findViewById(R.id.age);
        int age = Integer.parseInt(tilAge.getEditText().getText().toString());
        TextInputLayout tilHeight = (TextInputLayout) findViewById(R.id.height);
        String height = tilHeight.getEditText().getText().toString();
        TextInputLayout tilWeight = (TextInputLayout) findViewById(R.id.weight);
        double weight = Double.parseDouble(tilWeight.getEditText().getText().toString());

        String feet = height.substring(0, height.indexOf("_"));
        String inches = height.substring(height.indexOf("_") + 1, height.length());
        double cmHeight = 2.54 * (Integer.parseInt(feet) + Double.parseDouble(inches) / 12);
        double kg = weight * 0.453592;
        double bmr;

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.sex);
        int radioButtonID = radioGroup.getCheckedRadioButtonId();
        RadioButton radioButton = (RadioButton) radioGroup.findViewById(radioButtonID);
        String selectedtext = (String) radioButton.getText();

        if(selectedtext.equals("Male"))
            bmr = cmHeight * 6.25 + kg * 9.9 - age * 4.92 + 5;
        else
            bmr = cmHeight * 6.25 + kg * 9.9 - age * 4.92 - 161;

        return bmr;




    }

}
