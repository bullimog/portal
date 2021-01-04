package com.bullimog.portal.config;

import org.springframework.beans.factory.annotation.Value;

public class GravityConfig {
    private Double degree31;
    private Double degree32;
    private Double degree33;
    private Double degree34;

    private Double num0;
    private Double num1;
    private Double num2;
    private Double num3;


    public GravityConfig(Double degree31, Double degree32, Double degree33, Double degree34,
                         Double num0, Double num1, Double num2, Double num3) {
        this.degree31 = degree31;
        this.degree32 = degree32;
        this.degree33 = degree33;
        this.degree34 = degree34;
        this.num0 = num0;
        this.num1 = num1;
        this.num2 = num2;
        this.num3 = num3;
    }

    public Double getDegree31() {
        return degree31;
    }

    public Double getDegree32() {
        return degree32;
    }

    public Double getDegree33() {
        return degree33;
    }

    public Double getDegree34() {
        return degree34;
    }

    public Double getNum0() {
        return num0;
    }

    public Double getNum1() {
        return num1;
    }

    public Double getNum2() {
        return num2;
    }

    public Double getNum3() {
        return num3;
    }
}
