package com.bb.stardium.domain.player;

import com.bb.stardium.service.player.dto.PlayerDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.OffsetDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class PlayerTest {

    private final PlayerDto newPlayerDto = PlayerDto.builder()
            .email("member@gmail.com")
            .nickname("member")
            .password("password")
            .build();
    private final PlayerDto updatePlayerDto = PlayerDto.builder()
            .email("member@gmail.com")
            .nickname("member")
            .password("password")
            .statusMessage("status-change")
            .build();
    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("저장시 생성시간 등록 테스트")
    void createTimeTestWhenPlayerRegister() {
        Player newPlayer = Player.builder()
                .playerDto(newPlayerDto)
                .build();
        Player persistPlayer = em.persist(newPlayer);

        assertThat(persistPlayer.getCreateTime()).isNotNull();
    }

    @Test
    @DisplayName("프로파일 이미지 존재하는지 테스트")
    void playerProfileImageExistTest() {
        Player newPlayer = Player.builder()
                .playerDto(newPlayerDto)
                .build();
        Player persistPlayer = em.persist(newPlayer);

        assertThat(persistPlayer.getProfile()).isNotNull();
    }

    @Test
    @DisplayName("업데이트시 업데이트 시간 변경, 업데이트 성공 테스트")
    void updateTimeTestWhenPlayerUpdateInfo() {
        Player newPlayer = Player.builder()
                .playerDto(newPlayerDto)
                .build();
        Player persistPlayer = em.persist(newPlayer);

        OffsetDateTime createDateTime = persistPlayer.getCreateTime();
        OffsetDateTime updateDateTime = persistPlayer.getUpdateTime();

        persistPlayer.update(updatePlayerDto);
        em.flush();

        assertThat(persistPlayer.getCreateTime()).isEqualTo(createDateTime);
        assertThat(persistPlayer.getUpdateTime()).isNotEqualTo(updateDateTime);
        assertThat(persistPlayer.getStatusMessage()).isEqualTo("status-change");
    }

}