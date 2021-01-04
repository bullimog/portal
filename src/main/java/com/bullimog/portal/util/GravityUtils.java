package com.bullimog.portal.util;

import com.bullimog.portal.config.GravityConfig;

public class GravityUtils {

    //constants for calculating temperature effect on SG.
    private static final Double num0 = 1.00130346;
    private static final Double num1 = 0.000134722124;
    private static final Double num2 = 0.00000204052596;
    private static final Double num3 = 0.00000000232820948;

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

    public Double celsiusToFahrenheit(Double celsius) {
        return (celsius* 9/5)+32;
    }

    public Double adjustGravityForTemperatureC(Double gravity, Double measuredTemp){
        Double measuredTempF = celsiusToFahrenheit(measuredTemp);
        Double adjustedGravity = adjustGravityForTemperatureF(gravity, measuredTempF);
        double rounded = Math.round(adjustedGravity * 10000.0) / 10000.0;
        return rounded;
    };
}
