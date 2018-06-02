package com.hc.healthco;

import android.app.Activity;
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
import android.widget.CalendarView;

import com.hc.healthco.AccountActivity.LoginActivity;
import com.hc.healthco.R;

public class AddFragment extends Fragment {
    private FloatingActionButton add;
    private CalendarView calendar;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add, container, false);
        calendar = (CalendarView) view.findViewById(R.id.calendar);
        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity context = getActivity();

            }
        });
        long date = calendar.getDate();
        calendar.setDate(date, true, true);
        return view;
    }



}
