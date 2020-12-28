package com.bullimog.portal.models;
import java.util.ArrayList;
import java.time.LocalDateTime;

public class Temperatures {
    private  ArrayList<LocalDateTime> dateTimes;
    private  ArrayList<Float> temperatures;

    public Temperatures(){
        dateTimes = new ArrayList<LocalDateTime>();
        temperatures = new ArrayList<Float>();
    }

    public Temperatures(ArrayList<LocalDateTime> dateTimes, ArrayList<Float> temperatures){
        this.dateTimes = dateTimes;
        this.temperatures = temperatures;
    }

    public ArrayList<LocalDateTime> getdateTime() {return dateTimes;}
    public ArrayList<Float> getTemperature(){return temperatures;}

//    public void setDateTimes(ArrayList<LocalDateTime> dateTime) {this.dateTimes = dateTimes;}
//    public void setTemperatures(ArrayList<Float> temperature) {this.temperatures = temperatures;}

    public void appendTemperature(Float temperature){
        LocalDateTime date = LocalDateTime.now();
        dateTimes.add(date);
        temperatures.add(temperature);
    }
}
