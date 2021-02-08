package com.bullimog.portal.connectors;

import com.bullimog.portal.models.FermentTemperatures;

public interface FermentTemperaturesFileConnector {
    public FermentTemperatures readFermentTemperatures();
    public boolean writeFermentTemperatures(FermentTemperatures ft);
}
