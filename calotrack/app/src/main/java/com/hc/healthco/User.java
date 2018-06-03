package com.hc.healthco;

public class User {
    private double bmr;
    private String id;

    public User()
    {}
    public User(String id, double bmr)
    {
        this.bmr = bmr;
        this.id = id;

    }
    public double returnBMR()
    {
        return bmr;
    }
    public void setBMR(double num)
    {
        bmr = num;
    }
    public String returnId()
    {
        return id;
    }
    public void setId(String word)
    {
        id = word;
    }
}
