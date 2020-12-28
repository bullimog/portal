package com.bullimog.portal.connectors;

import com.bullimog.portal.models.Temperatures;

public interface TemperatureFileConnector {
    public Temperatures readTemperatures();
    public void writeTemperatures(Temperatures t);
}
