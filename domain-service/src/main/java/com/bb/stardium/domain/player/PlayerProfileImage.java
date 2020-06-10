package com.bb.stardium.domain.player;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = {"profileUrl"})
public class PlayerProfileImage {
    private static final String DEFAULT_PROFILE_IMAGE_URL = "https://stardium2020.s3.ap-northeast-2.amazonaws.com/profile-image/default_profile_img.png";

    @Column(name = "profile_url", nullable = false)
    private String profileUrl;

    @Builder
    public PlayerProfileImage(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    static PlayerProfileImage defaultImage() {
        return new PlayerProfileImage(DEFAULT_PROFILE_IMAGE_URL);
    }
}
