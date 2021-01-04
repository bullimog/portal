package com.bullimog.portal.models;

public class Calibration {
    Double degree3_1;
    Double degree3_2;
    Double degree3_3;
    Double degree3_4;

    public Calibration(Double degree3_1, Double degree3_2, Double degree3_3, Double degree3_4) {
        this.degree3_1 = degree3_1;
        this.degree3_2 = degree3_2;
        this.degree3_3 = degree3_3;
        this.degree3_4 = degree3_4;
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
}
