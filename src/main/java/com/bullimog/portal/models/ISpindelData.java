package com.bullimog.portal.models;

public class ISpindelData {
    String name;
    Integer ID;
    String token;
    Float angle;
    Float temperature;
    String temp_units;
    Float battery;
    Float gravity;
    Integer interval;
    Integer RSSI;

    public ISpindelData(String name, Integer ID, String token, Float angle, Float temperature,
                        String temp_units, Float battery, Float gravity, Integer interval, Integer RSSI){
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
    public Float getAngle(){return this.angle;}

    public Float getTemperature(){
        return this.temperature;
    }
    public String getTemp_Units(){
        return this.temp_units;
    }
    public Float getBattery(){return this.battery;}
    public Float getGravity(){
        return gravity;
    }
    public Integer getInterval(){
        return interval;
    }
}
