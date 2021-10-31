package com.bullimog.portal.connectors;

import java.util.Optional;

public interface FileConnector {
    public <T> Optional <T> readContents(Class<T> valueType);
    public <T> boolean writeContents(T t);
}
