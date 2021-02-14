package com.bullimog.portal.models;

public class FermentMonitorData {
   Double shedTemp;
   Double fridgeTemp;
   Double wortTemp;
   Double target;
   Double tolerance;
   Integer bubbles;
   Integer cooling;
   Integer heating;

    public FermentMonitorData(Double shedTemp, Double fridgeTemp, Double wortTemp,
                              Double target, Double tolerance,
                              Integer bubbles, Integer cooling, Integer warming) {
        this.shedTemp = shedTemp;
        this.fridgeTemp = fridgeTemp;
        this.wortTemp = wortTemp;
        this.target = target;
        this.tolerance = tolerance;
        this.bubbles = bubbles;
        this.cooling = cooling;
        this.heating = warming;
    }

    public Double getShedTemp() { return shedTemp; }
    public Double getFridgeTemp() { return fridgeTemp; }
    public Double getWortTemp() { return wortTemp; }
    public Double getTarget(){return target;}
    public Double getTolerance(){return tolerance;}
    public Integer getBubbles() { return bubbles; }
    public Integer getCooling() { return cooling; }
    public Integer getHeating() { return heating; }
}
