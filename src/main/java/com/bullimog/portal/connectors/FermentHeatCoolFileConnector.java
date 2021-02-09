package com.bullimog.portal.connectors;

import com.bullimog.portal.models.FermentHeatCools;

public interface FermentHeatCoolFileConnector {
    public FermentHeatCools readFermentHeatCools();
    public boolean writeFermentHeatCools(FermentHeatCools fhc);
}
