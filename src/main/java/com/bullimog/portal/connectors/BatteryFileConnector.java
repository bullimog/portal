package com.bullimog.portal.connectors;

import com.bullimog.portal.models.Batteries;

public interface BatteryFileConnector {
    Batteries readBatteries();
    boolean writeBatteries(Batteries b);
}
