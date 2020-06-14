package com.bb.stardium.aws.api.profile.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ResponseUploadImage {
    private String imageUrl;

    @Builder
    public ResponseUploadImage(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
