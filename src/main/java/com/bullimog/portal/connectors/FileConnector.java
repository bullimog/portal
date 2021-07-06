package com.bullimog.portal.connectors;

public interface FileConnector {
    public <T> T readContents(Class<T> valueType);
    public <T> boolean writeContents(T t);
}
