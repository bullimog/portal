package com.bullimog.portal.models;

public class FermentConfig {
    String updateUrl;
    Double target;
    Double tolerance;
    int historyDuration;
    Double wortCalibrate;
    Double fridgeCalibrate;
    Double shedCalibrate;

    public FermentConfig(String updateUrl, Double target, Double tolerance, int historyDuration, Double wortCalibrate, Double fridgeCalibrate, Double shedCalibrate) {
        this.updateUrl = updateUrl;
        this.target = target;
        this.tolerance = tolerance;
        this.historyDuration = historyDuration;
        this.wortCalibrate = wortCalibrate;
        this.fridgeCalibrate = fridgeCalibrate;
        this.shedCalibrate = shedCalibrate;
    }

    public String getUpdateUrl() {
        return updateUrl;
    }

    public Double getTarget() {
        return target;
    }

    public Double getTolerance() {
        return tolerance;
    }

    public int getHistoryDuration() {
        return historyDuration;
    }

    public Double getWortCalibrate() {
        return wortCalibrate;
    }

    public Double getFridgeCalibrate() {
        return fridgeCalibrate;
    }

    public Double getShedCalibrate() {
        return shedCalibrate;
    }
}
