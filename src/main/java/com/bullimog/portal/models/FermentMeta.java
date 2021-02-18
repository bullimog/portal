package com.bullimog.portal.models;

import java.time.LocalDateTime;

public class FermentMeta {
    LocalDateTime lastGet;

    public LocalDateTime getLastGet() {
        return lastGet;
    }

    public void setLastGet(LocalDateTime lastGet) {
        this.lastGet = lastGet;
    }
}
