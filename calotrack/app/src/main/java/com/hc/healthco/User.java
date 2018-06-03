package com.hc.healthco;

public class User {
    double bmr;

    public User()
    {}
    public User(double bmr)
    {
        this.bmr = bmr;

    }
    public double returnBMR()
    {
        return bmr;
    }
    public void setBMR(double num)
    {
        bmr = num;
    }

}
