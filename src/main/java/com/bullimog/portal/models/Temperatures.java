package com.bullimog.portal.models;
import java.util.ArrayList;
import java.time.LocalDateTime;

public class Temperatures {
    private  ArrayList<LocalDateTime> dateTimes;
    private  ArrayList<Double> temperatures;

    public Temperatures(){
        dateTimes = new ArrayList<LocalDateTime>();
        temperatures = new ArrayList<Double>();
    }

    public Temperatures(ArrayList<LocalDateTime> dateTimes, ArrayList<Double> temperatures){
        this.dateTimes = dateTimes;
        this.temperatures = temperatures;
    }

    public void appendTemperature(Double temperature){
        LocalDateTime date = LocalDateTime.now();
        dateTimes.add(date);
        temperatures.add(temperature);
    }

    public ArrayList<LocalDateTime> getdateTime() {return dateTimes;}
    public ArrayList<Double> getTemperature(){return temperatures;}
}
