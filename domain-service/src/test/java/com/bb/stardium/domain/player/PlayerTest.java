package com.bb.stardium.domain.player;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.OffsetDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class PlayerTest {

    @Autowired
    private TestEntityManager em;

    private final Player player = Player.builder()
            .email("member@gmail.com")
            .nickname("member")
            .password("password")
            .build();

//    private final Player updatePlayer = Player.builder()
//            .email("member@gmail.com")
//            .nickname("member")
//            .password("password")
//            .statusMessage("status-change")
//            .build();

    @Test
    @DisplayName("저장시 생성시간 등록 테스트")
    void createTimeTestWhenPlayerRegister() {
        Player persistPlayer = em.persist(player);

        assertThat(persistPlayer.getCreateTime()).isNotNull();
    }

//    @Test
//    @DisplayName("업데이트시 업데이트 시간 변경 테스트")
//    void updateTimeTestWhenPlayerUpdateInfo() {
//        Player persistPlayer = em.persist(player);
//
//        OffsetDateTime createDateTime = persistPlayer.getCreateTime();
//        OffsetDateTime updateDateTime = persistPlayer.getUpdateTime();
//
//        persistPlayer.update(updatePlayer);
//        em.flush();
//
//        assertThat(persistPlayer.getCreateTime()).isEqualTo(createDateTime);
//        assertThat(persistPlayer.getUpdateTime()).isNotEqualTo(updateDateTime);
//        assertThat(persistPlayer.getStatusMessage()).isEqualTo("status-change");
//    }

    @Test
    @DisplayName("프로파일 이미지 존재하는지 테스트")
    void playerProfileImageExistTest() {
        Player persistPlayer = em.persist(player);

        assertThat(persistPlayer.getProfile()).isNotNull();
    }
}