package com.bullimog.portal.connectors;

import com.bullimog.portal.models.Batteries;
import com.bullimog.portal.models.Gravities;

public interface GravityFileConnector {
    Gravities readGravities();
    boolean writeGravities(Gravities g);
}
