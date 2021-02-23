package com.bullimog.portal.models;

import java.time.LocalDateTime;

public class ISpindelMeta {
    LocalDateTime lastPost;
    public LocalDateTime getLastPost() {
        return lastPost;
    }
    public void setLastPost(LocalDateTime lastPost) {
        this.lastPost = lastPost;
    }
}
