package com.bullimog.portal.models;

import java.util.ArrayList;
import java.time.LocalDateTime;
public class Batteries {

    private  ArrayList<LocalDateTime> dateTimes;
    private  ArrayList<Float> batteries;

    public Batteries(){
        dateTimes = new ArrayList<LocalDateTime>();
        batteries = new ArrayList<Float>();
    }

    public Batteries(ArrayList<LocalDateTime> dateTimes, ArrayList<Float> batteries){
        this.dateTimes = dateTimes;
        this.batteries = batteries;
    }

    public void appendBattery(Float battery){
        LocalDateTime date = LocalDateTime.now();
        dateTimes.add(date);
        batteries.add(battery);
    }

    public ArrayList<LocalDateTime> getdateTime() {return dateTimes;}
    public ArrayList<Float> getBatteries(){return batteries;}
}
