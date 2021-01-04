package com.bullimog.portal.util;

import com.bullimog.portal.config.GravityConfig;

public class GravityUtils {

    GravityConfig gravityConfig;
    public GravityUtils(GravityConfig gravityConfig) {
        this.gravityConfig=gravityConfig;
    }

    public Double calculateGravity(Double tilt) {
        Double calculatedGravity =
                gravityConfig.getDegree31() +
                        gravityConfig.getDegree32() * tilt -
                        gravityConfig.getDegree33() * tilt * tilt +
                        gravityConfig.getDegree34() * tilt * tilt * tilt;

        return calculatedGravity;
    }

    private static final Double calibratedTemp = 55.40; //13c
    public Double adjustGravityForTemperatureF(Double gravity, Double measuredTemp){
        Double num0 = gravityConfig.getNum0();
        Double num1 = gravityConfig.getNum1();
        Double num2 = gravityConfig.getNum2();
        Double num3 = gravityConfig.getNum3();
        Double adjustedGravity = gravity*(
                (num0-num1*measuredTemp+
                        num2*measuredTemp*measuredTemp-
                        num3*measuredTemp*measuredTemp*measuredTemp)/
                        (num0-num1*calibratedTemp+
                                num2*calibratedTemp*calibratedTemp-
                                num3*calibratedTemp*calibratedTemp*calibratedTemp));

        double rounded = Math.round(adjustedGravity * 10000.0) / 10000.0;
        return rounded;
    }

    public Double adjustGravityForTemperatureC(Double gravity, Double measuredTemp){
        Double measuredTempF = (measuredTemp* 9/5)+32;
        Double adjustedGravity = adjustGravityForTemperatureF(gravity, measuredTempF);
        double rounded = Math.round(adjustedGravity * 10000.0) / 10000.0;
        return rounded;
    };
}
