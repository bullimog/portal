package com.bullimog.portal.models;
import java.util.ArrayList;
import java.time.LocalDateTime;

public class FermentTemperatures {
    private  ArrayList<LocalDateTime> dateTimes;
    private  ArrayList<Double> shedTemps;
    private  ArrayList<Double> fridgeTemps;
    private  ArrayList<Double> wortTemps;

    public FermentTemperatures(){
        dateTimes = new ArrayList<LocalDateTime>();
        shedTemps = new ArrayList<Double>();
        fridgeTemps = new ArrayList<Double>();
        wortTemps = new ArrayList<Double>();
    }

    public FermentTemperatures(ArrayList<LocalDateTime> dateTimes,
                               ArrayList<Double> shedTemps,
                               ArrayList<Double> fridgeTemps,
                               ArrayList<Double> wortTemps){
        this.dateTimes = dateTimes;
        this.shedTemps = shedTemps;
        this.fridgeTemps = fridgeTemps;
        this.wortTemps = wortTemps;
    }

    public void appendTemperature(Double shedTemp, Double fridgeTemp, Double wortTemp){
        LocalDateTime date = LocalDateTime.now();
        dateTimes.add(date);
        shedTemps.add(shedTemp);
        fridgeTemps.add(fridgeTemp);
        wortTemps.add(wortTemp);
    }

    public ArrayList<LocalDateTime> getdateTime() {return dateTimes;}
    public ArrayList<Double> getShedTemps(){return shedTemps;}
    public ArrayList<Double> getFridgeTemps(){return fridgeTemps;}
    public ArrayList<Double> getWortTemps(){return wortTemps;}
}
