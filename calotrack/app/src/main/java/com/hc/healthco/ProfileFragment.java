package com.hc.healthco;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hc.healthco.AccountActivity.LoginActivity;

public class ProfileFragment extends Fragment {
    private FirebaseAuth firebaseAuth;
    private Button logout;
    private Activity context;
    private TextView bmr;
    public DatabaseReference ref;
    private Button update;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        firebaseAuth = FirebaseAuth.getInstance();
        logout = (Button) view.findViewById(R.id.logout_button);
        bmr = (TextView) view.findViewById(R.id.bmr_text);
        ref = FirebaseDatabase.getInstance().getReference("users");
        update = (Button) view.findViewById(R.id.updateBMR);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context = getActivity();
                firebaseAuth.signOut();
                context.finish();
                startActivity(new Intent(context, LoginActivity.class));
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context = getActivity();
                startActivity(new Intent(context, SurveyActivity.class));
            }
        });
        return view;
    }

    public void onStart() {
        super.onStart();
        String id = ref.push().getKey();
        ref.child(id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //double value = dataSnapshot.getValue(double.class);
                //bmr.setText("BMR " + value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
