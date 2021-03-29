package com.bullimog.portal.models;

import java.time.LocalDateTime;

public class ISpindelMeta {
    LocalDateTime lastPost;
    Double angle;
    public LocalDateTime getLastPost() {
        return lastPost;
    }
    public void setLastPost(LocalDateTime lastPost) {
        this.lastPost = lastPost;
    }
    public Double getAngle() {return angle;}
    public void setAngle(Double angle) { this.angle = angle;}
}
