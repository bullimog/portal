package com.bullimog.portal.models;

import java.time.LocalDateTime;

public class FermentMeta {
    LocalDateTime lastGet;
    LocalDateTime lastPost;

    public LocalDateTime getLastGet() {
        return lastGet;
    }
    public LocalDateTime getLastPost() {
        return lastPost;
    }

    public void setLastGet(LocalDateTime lastGet) {
        this.lastGet = lastGet;
    }
    public void setLastPost(LocalDateTime lastPost) {
        this.lastPost = lastPost;
    }
}
