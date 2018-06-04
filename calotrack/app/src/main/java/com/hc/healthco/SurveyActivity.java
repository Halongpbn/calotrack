package com.hc.healthco;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
        ref = FirebaseDatabase.getInstance().getReference("users");
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
        double bmr = calcCalos();
        User person = new User(bmr);
        person.setBMR(bmr);
        String id = ref.push().getKey();
        ref.child(id).setValue(person);
    }


    private double calcCalos() {

        TextInputLayout tilAge = (TextInputLayout) findViewById(R.id.age);
        int age = Integer.parseInt(tilAge.getEditText().getText().toString());
        TextInputLayout tilHeight = (TextInputLayout) findViewById(R.id.height);
        String height = tilHeight.getEditText().getText().toString();
        TextInputLayout tilWeight = (TextInputLayout) findViewById(R.id.weight);
        double weight = Double.parseDouble(tilWeight.getEditText().getText().toString());

        String feet = height.substring(0, height.indexOf("_"));
        String inches = height.substring(height.indexOf("_") + 1, height.length());
        double cmHeight = 2.54 * (Integer.parseInt(feet) * 12 + Integer.parseInt(inches));
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

        RadioGroup radioGroup2 = (RadioGroup) findViewById(R.id.activity);
        int radioButtonID2 = radioGroup.getCheckedRadioButtonId();
        RadioButton radioButton2 = (RadioButton) radioGroup.findViewById(radioButtonID);
        String activityLevel = (String) radioButton2.getText();
        double calories;

        if(activityLevel.equals("Sedentary (little/no exercise)"))
            calories = bmr * 1.1;
        else if(activityLevel.equals("Lightly active (1-3 days/week)"))
            calories = bmr * 1.275;
        else if(activityLevel.equals("Moderately active (3-5 days/week)"))
            calories = bmr * 1.35;
        else
            calories = bmr * 1.525;

        return calories;






    }

}
