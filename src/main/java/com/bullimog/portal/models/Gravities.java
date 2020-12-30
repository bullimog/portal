package com.bullimog.portal.models;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Gravities {
    private ArrayList<LocalDateTime> dateTimes;
    private ArrayList<Double> gravities;

    public Gravities(){
        dateTimes = new ArrayList<LocalDateTime>();
        gravities = new ArrayList<Double>();
    }

    public Gravities(ArrayList<LocalDateTime> dateTimes, ArrayList<Double> gravities){
        this.dateTimes = dateTimes;
        this.gravities = gravities;
    }

    public void appendGravity(Double gravity){
        LocalDateTime date = LocalDateTime.now();
        dateTimes.add(date);
        gravities.add(gravity);
    }

    public ArrayList<LocalDateTime> getdateTime() {return dateTimes;}
    public ArrayList<Double> getGravities(){return gravities;}
}
