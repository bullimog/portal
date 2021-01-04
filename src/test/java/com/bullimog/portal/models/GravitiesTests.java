package com.bullimog.portal.models;

import com.bullimog.portal.config.GravityConfig;
import com.bullimog.portal.util.GravityUtils;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GravitiesTests {
    @Test
    public void testGravityAdjustments(){
        Double degree31 = 0.8829738953763338;
        Double degree32 = 0.006232944160223315;
        Double degree33 = 0.00009307076837167256;
        Double degree34 = 6.366267580822437e-7;
        Double num0 = 1.00130346;
        Double num1 = 0.000134722124;
        Double num2 = 0.00000204052596;
        Double num3 = 0.00000000232820948;
        GravityConfig gravityConfig = new GravityConfig(degree31,degree32,degree33,degree34,num0,num1,num2,num3);
        GravityUtils gravityUtils= new GravityUtils(gravityConfig);

        Double result = gravityUtils.adjustGravityForTemperatureF(1.040, 59.0);
        Double expected = 1.0403;
        assertEquals(expected, result);

        result = gravityUtils.adjustGravityForTemperatureF(1.040, 68.0);
        expected = 1.0412;
        assertEquals(expected, result);

        result = gravityUtils.adjustGravityForTemperatureC(1.040, 20.0);
        expected = 1.0412;
        assertEquals(expected, result);

        result = gravityUtils.adjustGravityForTemperatureF(1.040, 13.0);
        expected = 1.0402;
        assertEquals(expected, result);
    }
}
