package com.bb.stardium.domain.player;

import com.bb.stardium.service.player.exception.InvalidProfileUrlException;
import lombok.*;
import org.springframework.util.StringUtils;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = {"profileUrl"})
public class PlayerProfileImage {

    private static final String PREFIX_PROFILE_DIRECTORY = "https://stardium2020.s3.ap-northeast-2.amazonaws.com/profile-image/";
    private static final String DEFAULT_IMAGE_NAME = "default_profile_img.png";

    @Column(name = "profile_url", nullable = false)
    private String profileUrl;

    @Builder
    public PlayerProfileImage(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    public PlayerProfileImage createDefaultImage() {
        this.profileUrl = PREFIX_PROFILE_DIRECTORY + DEFAULT_IMAGE_NAME;
        return this;
    }

    public PlayerProfileImage update(String profileUrl) {
        if (StringUtils.isEmpty(profileUrl) || !profileUrl.startsWith(PREFIX_PROFILE_DIRECTORY)) {
            throw new InvalidProfileUrlException();
        }

        this.profileUrl = profileUrl;
        return this;
    }
}
