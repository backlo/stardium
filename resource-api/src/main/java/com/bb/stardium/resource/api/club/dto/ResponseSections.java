package com.bb.stardium.resource.api.club.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class ResponseSections {
    private final List<String> sections;

    @Builder
    public ResponseSections(List<String> sections) {
        this.sections = sections;
    }
}
