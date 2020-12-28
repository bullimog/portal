package com.bullimog.portal.models;

public class Test {
    String aValue;

    public Test(){
        aValue="defaulted";
    }
    public Test(String aValue){
        this.aValue=aValue;
    }
    public String getaValue() {
        return aValue;
    }

    public void setaValue(String aValue) {
        this.aValue = aValue;
    }
}
