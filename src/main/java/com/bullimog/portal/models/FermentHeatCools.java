package com.bullimog.portal.models;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class FermentHeatCools {
    private ArrayList<LocalDateTime> dateTimes;
    private  ArrayList<Integer> coolings;
    private  ArrayList<Integer> heatings;

    public FermentHeatCools(){
        dateTimes = new ArrayList<LocalDateTime>();
        coolings = new ArrayList<Integer>();
        heatings = new ArrayList<Integer>();
    }

    public FermentHeatCools(ArrayList<LocalDateTime> dateTimes, ArrayList<Integer> cooling,
                            ArrayList<Integer> heating){
        this.dateTimes = dateTimes;
        this.coolings = cooling;
        this.heatings = heating;
    }

    public void appendFermentHeatCool(Integer cooling, Integer heating){
        LocalDateTime date = LocalDateTime.now();
        dateTimes.add(date);
        coolings.add(cooling);
        heatings.add(heating);
    }

    public ArrayList<LocalDateTime> getdateTime() {return dateTimes;}
    public ArrayList<Integer> getHeatings(){return heatings;}
    public ArrayList<Integer> getCoolings() {return coolings;}
}
