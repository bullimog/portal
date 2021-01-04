package com.bullimog.portal.connectors;

import com.bullimog.portal.models.Calibration;

public interface CalibrationFileConnector {
    Calibration readCalibration();
    boolean writeCalibration(Calibration c);
}
