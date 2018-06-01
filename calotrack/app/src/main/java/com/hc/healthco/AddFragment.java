package com.hc.healthco;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.hc.healthco.AccountActivity.LoginActivity;
import com.hc.healthco.R;

public class AddFragment extends Fragment {
    private FloatingActionButton add;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add, container, false);
    }
    @Override
    public void onViewCreated()
    {
        super.onCreate(savedInstances);
        FragmentActivity context = getActivity();
        add = (FloatingActionButton) context.findViewById(R.id.adding);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
