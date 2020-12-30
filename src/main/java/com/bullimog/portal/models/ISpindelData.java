package com.bullimog.portal.models;

public class ISpindelData {
    String name;
    Integer ID;
    String token;
    Double angle;
    Double temperature;
    String temp_units;
    Double battery;
    Double gravity;
    Integer interval;
    Integer RSSI;

    public ISpindelData(String name, Integer ID, String token, Double angle, Double temperature,
                        String temp_units, Double battery, Double gravity, Integer interval, Integer RSSI){
        this.name=name;
        this.ID=ID;
        this.token=token;
        this.angle=angle;
        this.temperature=temperature;
        this.temp_units=temp_units;
        this.battery=battery;
        this.gravity=gravity;
        this.interval=interval;
        this.RSSI=RSSI;
    }


    public String getName() {return name;}
    public Integer getID(){return ID;}
    public String getToken(){return token;}
    public Double getAngle(){return this.angle;}

    public Double getTemperature(){
        return this.temperature;
    }
    public String getTemp_Units(){
        return this.temp_units;
    }
    public Double getBattery(){return this.battery;}
    public Double getGravity(){
        return gravity;
    }
    public Integer getInterval(){
        return interval;
    }
}
