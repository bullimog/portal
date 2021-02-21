package com.bullimog.portal.models;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotBlank;

public class FermentConfig {
    @NotBlank
    String updateUrl;
    @NotNull @Min(1) @Max(25)
    Double target;
    @NotNull @Min((long)0.1) @Max(99)
    Double tolerance;
    @NotNull @Min(5000) @Max(9999999)
    int historyDuration;
    @NotNull @Min(-99) @Max(99)
    Double wortCalibrate;
    @NotNull @Min(-99) @Max(99)
    Double fridgeCalibrate;
    @NotNull @Min(-99) @Max(99)
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
