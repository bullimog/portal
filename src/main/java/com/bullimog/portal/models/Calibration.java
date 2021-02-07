package com.bullimog.portal.models;
import javax.validation.constraints.*;

public class Calibration {
    @NotNull @Min(0) @Max(99)
    Double degree3_1;
    @NotNull @Min(0) @Max(99)
    Double degree3_2;
    @NotNull @Min(0) @Max(99)
    Double degree3_3;
    @NotNull @Min(0) @Max(99)
    Double degree3_4;
    @NotNull @Min(0) @Max(99)
    Double calibratedTemperatureFahrenheit;

    public Calibration(Double degree3_1, Double degree3_2, Double degree3_3, Double degree3_4,
                       Double calibratedTemperatureFahrenheit) {
        this.degree3_1 = degree3_1;
        this.degree3_2 = degree3_2;
        this.degree3_3 = degree3_3;
        this.degree3_4 = degree3_4;
        this.calibratedTemperatureFahrenheit = calibratedTemperatureFahrenheit;
    }

    public void setDegree3_1(Double degree3_1) {
        this.degree3_1 = degree3_1;
    }

    public void setDegree3_2(Double degree3_2) {
        this.degree3_2 = degree3_2;
    }

    public void setDegree3_3(Double degree3_3) {
        this.degree3_3 = degree3_3;
    }

    public void setDegree3_4(Double degree3_4) {
        this.degree3_4 = degree3_4;
    }

    public void setCalibratedTemperatureFahrenheit(Double calibratedTemperatureFahrenheit) {
        this.calibratedTemperatureFahrenheit = calibratedTemperatureFahrenheit;
    }

    public Double getDegree3_1() {
        return degree3_1;
    }

    public Double getDegree3_2() {
        return degree3_2;
    }

    public Double getDegree3_3() {
        return degree3_3;
    }

    public Double getDegree3_4() {
        return degree3_4;
    }

    public Double getCalibratedTemperatureFahrenheit() {
        return calibratedTemperatureFahrenheit;
    }

    @Override
    public String toString() {
        return "Calibration{" +
                "degree3_1=" + degree3_1 +
                ", degree3_2=" + degree3_2 +
                ", degree3_3=" + degree3_3 +
                ", degree3_4=" + degree3_4 +
                ", calibratedTemperatureFahrenheit=" + calibratedTemperatureFahrenheit +
                '}';
    }
}
