package com.bullimog.portal.models;

public class FermentMonitorData {
   Double shedTemp;
   Double fridgeTemp;
   Double wortTemp;
   Integer bubbles;
   Integer cooling;
   Integer heating;

    public FermentMonitorData(Double shedTemp, Double fridgeTemp, Double wortTemp, Integer bubbles, Integer cooling, Integer warming) {
        this.shedTemp = shedTemp;
        this.fridgeTemp = fridgeTemp;
        this.wortTemp = wortTemp;
        this.bubbles = bubbles;
        this.cooling = cooling;
        this.heating = warming;
    }

    public Double getShedTemp() { return shedTemp; }
    public Double getFridgeTemp() { return fridgeTemp; }
    public Double getWortTemp() { return wortTemp; }
    public Integer getBubbles() { return bubbles; }
    public Integer getCooling() { return cooling; }
    public Integer getHeating() { return heating; }
}
