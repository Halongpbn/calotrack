package com.hc.healthco;

public class calPoint {
    public double numCal;
    public int day;

    public calPoint()
    {
    }

    public calPoint(double numCal, int day) {
        this.numCal = numCal;
        this.day = day;
    }

    public void setNumCal(double numCal) {
        this.numCal = numCal;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public double getNumCal() {
        return numCal;
    }

    public int getDay() {
        return day;
    }
}
