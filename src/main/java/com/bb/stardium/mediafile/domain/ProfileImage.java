package com.bb.stardium.mediafile.domain;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode(of = {"profileUrl", "profileName"})
public class ProfileImage {
    private static final String DEFAULT_PROFILE_IMAGE_URL = "https://stardium2020.s3.ap-northeast-2.amazonaws.com/profile-image/default_profile_img.png";
    private static final String DEFAULT_PROFILE_IMAGE_NAME = "default_profile.png";

    @Column(name = "profile_url", nullable = false)
    private String profileUrl;

    @Column(name = "profile_name", nullable = false)
    private String profileName;

    public ProfileImage(String profileUrl, String profileName) {
        this.profileUrl = profileUrl;
        this.profileName = profileName;
    }

    public static ProfileImage defaultImage() {
        return new ProfileImage(DEFAULT_PROFILE_IMAGE_URL, DEFAULT_PROFILE_IMAGE_NAME);
    }
}
