package com.bb.stardium.resource.api.match.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ResponseMatch {
    private Boolean flag;

    @Builder
    public ResponseMatch(Boolean flag) {
        this.flag = flag;
    }
}
