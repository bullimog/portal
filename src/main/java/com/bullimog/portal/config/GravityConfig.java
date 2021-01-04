package com.bullimog.portal.config;

import org.springframework.beans.factory.annotation.Value;

public class GravityConfig {
    private Double degree31;
    private Double degree32;
    private Double degree33;
    private Double degree34;

    public GravityConfig(Double degree31, Double degree32, Double degree33, Double degree34) {
        this.degree31 = degree31;
        this.degree32 = degree32;
        this.degree33 = degree33;
        this.degree34 = degree34;
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
}
