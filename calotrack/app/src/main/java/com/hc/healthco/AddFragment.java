package com.hc.healthco;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddFragment extends Fragment {
    private CalendarView calendar;
    private TextView time;
    private EditText calories;
    private Button add;
    public DatabaseReference ref;
    private int day;
    private List<calPoint> points = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add, container, false);
        calendar = (CalendarView) view.findViewById(R.id.calendar);
        calories = (EditText) view.findViewById(R.id.calories);
        add = (Button) view.findViewById(R.id.addCal);
        time = (TextView) view.findViewById(R.id.date);
        ref = FirebaseDatabase.getInstance().getReference();
        String date = 6 + "/" + 4 + "/" + 2018;
        day = 4;
        time.setText(date);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addPoint();
            }
        });
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String date = (month + 1) + "/" +dayOfMonth + "/" + year;
                day = dayOfMonth;
                time.setText(date);

            }
        });
        long dateSet = calendar.getDate();
        calendar.setDate(dateSet, true, true);
        return view;
    }
    public void addPoint()
    {
        final double point = Double.parseDouble(calories.getText().toString());
        //String key = ref.push().getKey();
        calPoint calorie = new calPoint(point, day);
        //DatabaseReference pushedKey = ref.push();
        points.add(calorie);
        //ref.setValue(points);
        Map<String, Object> updates = new HashMap<>();
        updates.put("data", points);

        ref.updateChildren(updates);
    }

}
