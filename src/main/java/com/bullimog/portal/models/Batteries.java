package com.bullimog.portal.models;

import java.util.ArrayList;
import java.time.LocalDateTime;
public class Batteries {

    private  ArrayList<LocalDateTime> dateTimes;
    private  ArrayList<Double> batteries;

    public Batteries(){
        dateTimes = new ArrayList<LocalDateTime>();
        batteries = new ArrayList<Double>();
    }

    public Batteries(ArrayList<LocalDateTime> dateTimes, ArrayList<Double> batteries){
        this.dateTimes = dateTimes;
        this.batteries = batteries;
    }

    public void appendBattery(Double battery){
        LocalDateTime date = LocalDateTime.now();
        dateTimes.add(date);
        batteries.add(battery);
    }

    public ArrayList<LocalDateTime> getdateTime() {return dateTimes;}
    public ArrayList<Double> getBatteries(){return batteries;}
}
