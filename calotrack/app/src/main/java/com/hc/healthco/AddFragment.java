package com.hc.healthco;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;

import com.hc.healthco.AccountActivity.LoginActivity;
import com.hc.healthco.R;

public class AddFragment extends Fragment {
    private CalendarView calendar;
    private TextView time;
    private EditText calories;
    private Button add;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add, container, false);
        calendar = (CalendarView) view.findViewById(R.id.calendar);
        calories = (EditText) view.findViewById(R.id.calories);
        add = (Button) view.findViewById(R.id.addCal);
        time = (TextView) view.findViewById(R.id.date);

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String date = (month + 1) + "/" +dayOfMonth + "/" + year;
                time.setText(date);

            }
        });
        long date = calendar.getDate();
        calendar.setDate(date, true, true);
        return view;
    }



}
