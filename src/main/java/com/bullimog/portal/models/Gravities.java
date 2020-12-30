package com.bullimog.portal.models;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Gravities {
    private ArrayList<LocalDateTime> dateTimes;
    private  ArrayList<Float> gravities;

    public Gravities(){
        dateTimes = new ArrayList<LocalDateTime>();
        gravities = new ArrayList<Float>();
    }

    public Gravities(ArrayList<LocalDateTime> dateTimes, ArrayList<Float> gravities){
        this.dateTimes = dateTimes;
        this.gravities = gravities;
    }

    public void appendGravity(Float gravity){
        LocalDateTime date = LocalDateTime.now();
        dateTimes.add(date);
        gravities.add(gravity);
    }

    public ArrayList<LocalDateTime> getdateTime() {return dateTimes;}
    public ArrayList<Float> getGravities(){return gravities;}
}
