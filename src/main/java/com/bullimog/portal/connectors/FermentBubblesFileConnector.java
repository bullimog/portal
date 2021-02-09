package com.bullimog.portal.connectors;

import com.bullimog.portal.models.FermentBubbles;
import com.bullimog.portal.models.FermentHeatCools;

public interface FermentBubblesFileConnector {
    public FermentBubbles readFermentBubbles();
    public boolean writeFermentBubbles(FermentBubbles fb);
}
