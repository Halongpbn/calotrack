package com.hc.healthco;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.hc.healthco.R;

public class ProfileFragment extends Fragment {
    private FirebaseAuth firebaseAuth;
    private Button logout;
    private Activity context;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = getActivity();
        firebaseAuth = FirebaseAuth.getInstance();
        logout = (Button) context.findViewById(R.id.logout_button);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.signOut();
                context.finish();
                startActivity(new Intent(context, MainActivity.class));
            }
        });
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }
}
