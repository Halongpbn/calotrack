package com.hc.healthco;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.hc.healthco.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class HomeFragment extends Fragment {
    LineChart lineChart;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        lineChart = (LineChart) view.findViewById(R.id.chart);
        ArrayList <Entry> yAxis = new ArrayList<>();
        for(int i = 0; i < 30;i++) {
            float val = (float) Math.random()*100;
            yAxis.add(new Entry(i, val));
        }
        lineChart.setDragEnabled(true);
        lineChart.setScaleEnabled(false);
        LineDataSet lineData = new LineDataSet(yAxis, "DataSet1");
        ArrayList <ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(lineData);

        ArrayList<String> xAxis = new ArrayList<>();
        xAxis.add("April");
        xAxis.add("May");
        xAxis.add("June");
        xAxis.add("July");
        xAxis.add("August");
        xAxis.add("September");

        LimitLine limit = new LimitLine(1000f, "Calorie Limit");
        limit.setLineWidth(4f);
        limit.enableDashedLine(10f, 10f, 0f);
        limit.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_TOP);
        limit.setTextSize(10f);

        LineData data = new LineData(dataSets);
        lineChart.setData(data);


        return view;

    }
}
