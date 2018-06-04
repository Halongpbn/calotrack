package com.hc.healthco;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.github.mikephil.charting.components.YAxis;
import java.util.ArrayList;

public class HomeFragment extends Fragment {
    private LineChart lineChart;
    private DatabaseReference ref;
    private ArrayList <Entry> yAxis;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        lineChart = (LineChart) view.findViewById(R.id.chart);
        lineChart.setDragEnabled(true);
        lineChart.setScaleEnabled(false);
        lineChart.getDescription().setEnabled(false);

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                yAxis = new ArrayList<>();

                for(int i = 1; i <= dataSnapshot.getChildrenCount() ; i++) {
                        calPoint point = new calPoint();
                        point.setDay(dataSnapshot.child(i + "").getValue(calPoint.class).getDay());
                        point.setNumCal(dataSnapshot.child(i + "").getValue(calPoint.class).getNumCal());
                        yAxis.add(new Entry(point.getDay(), (float) point.getNumCal()));

                    }
                setGraph();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("DEBUG", "Failure");
            }
        };


        ref = FirebaseDatabase.getInstance().getReference("data");
        ref.addValueEventListener(valueEventListener);

        LimitLine limit = new LimitLine(1000f, "Calories Needed");
        limit.setLineWidth(2f);
        limit.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_TOP);
        limit.setTextSize(10f);
        YAxis leftAxis = lineChart.getAxisLeft();
        leftAxis.removeAllLimitLines();
        leftAxis.addLimitLine(limit);
        leftAxis.enableGridDashedLine(10f, 10f, 0f);
        lineChart.getAxisRight().setEnabled(false);

        return view;

    }
    public void setGraph()
    {
        LineDataSet lineData = new LineDataSet(yAxis, "Calorie Intake");
        ArrayList <ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(lineData);
        LineData data = new LineData(dataSets);
        lineChart.setData(data);
    }


}

