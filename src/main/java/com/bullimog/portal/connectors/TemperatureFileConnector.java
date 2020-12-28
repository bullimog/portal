package com.bullimog.portal.connectors;

import com.bullimog.portal.models.Temperatures;

public interface TemperatureFileConnector {
    Temperatures readTemperatures();
    boolean writeTemperatures(Temperatures t);
}
