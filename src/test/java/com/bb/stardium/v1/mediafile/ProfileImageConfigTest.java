package com.bb.stardium.v1.mediafile;

import com.bb.stardium.v1.mediafile.config.MediaFileResourceLocation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ProfileImageConfigTest {
    @Autowired
    private ApplicationContext context;

    @Test
    void name() {
        MediaFileResourceLocation bean = context.getBean(MediaFileResourceLocation.class);
        assertThat(bean.getLocation()).isNotNull();
        assertThat(bean.getUrl()).isNotNull();
    }
}