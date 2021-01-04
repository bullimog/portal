package com.bullimog.portal.models;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Component
public class Gravities {
    private ArrayList<LocalDateTime> dateTimes;
    private ArrayList<Double> gravities;
    private ArrayList<Double> adjustedGravities;

    public Gravities(){
        dateTimes = new ArrayList<LocalDateTime>();
        gravities = new ArrayList<Double>();
        adjustedGravities = new ArrayList<Double>();
    }

    public Gravities(ArrayList<LocalDateTime> dateTimes, ArrayList<Double> gravities, ArrayList<Double> adjustedGravities){
        this.dateTimes = dateTimes;
        this.gravities = gravities;
        this.adjustedGravities = adjustedGravities;
    }

    public void appendGravity(Double gravity, Double adjustedGravity){
        LocalDateTime date = LocalDateTime.now();
        dateTimes.add(date);
        gravities.add(gravity);
        adjustedGravities.add(adjustedGravity);
    }

    public ArrayList<LocalDateTime> getdateTime() {return dateTimes;}
    public ArrayList<Double> getGravities(){return gravities;}
    public ArrayList<Double> getAdjustedGravities(){return adjustedGravities;}

}
