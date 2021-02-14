package com.bullimog.portal.connectors;

import com.bullimog.portal.models.FermentConfig;

public interface FermentConfigFileConnector {
    public FermentConfig readConfig();
    public boolean writeFermentConfig(FermentConfig fc);
}
