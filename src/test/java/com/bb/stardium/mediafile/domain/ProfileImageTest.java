package com.bb.stardium.mediafile.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class ProfileImageTest {
    @Test
    @DisplayName("공백 url은 기본 URL로 변")
    void validUrl() {
        ProfileImage profileImage = new ProfileImage("");
        assertThat(profileImage.getUrl()).isNotEqualTo("");
    }

    @Test
    @DisplayName("업데이트 후 상태 변화")
    void update() {
        ProfileImage origin = new ProfileImage("");
        ProfileImage update = new ProfileImage("update");

        origin.update(update);

        assertThat(update.getUrl()).isEqualTo(origin.getUrl());
    }
}