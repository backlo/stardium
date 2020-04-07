package com.bb.stardium.mediafile.domain.repository;

import com.bb.stardium.mediafile.domain.ProfileImage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ProfileImageRepositoryTest {
    @Autowired
    private MediaFileRepository mediaFileRepository;

    @Test
    @DisplayName("미디어파일 CRUD")
    void findMediaFile() {
        ProfileImage profileImage = new ProfileImage("");
        mediaFileRepository.save(profileImage);

        assertThat(profileImage.getId()).isNotNull();
        assertThat(profileImage.getUrl()).isNotNull();

        mediaFileRepository.delete(profileImage);
        Long id = profileImage.getId();

        assertThat(mediaFileRepository.findById(id).isEmpty()).isTrue();
    }
}