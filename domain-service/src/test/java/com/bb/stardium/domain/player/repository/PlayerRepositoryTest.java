package com.bb.stardium.domain.player.repository;

import com.bb.stardium.domain.player.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class PlayerRepositoryTest {

    private final Player player = Player.builder()
            .email("member@gmail.com")
            .nickname("member")
            .password("password")
            .build();

    @Autowired
    private PlayerRepository playerRepository;

    @Test
    @DisplayName("이메일이 존재하는지 테스트")
    void existPlayerByEmailTest() {
    }

    @Test
    @DisplayName("이메일로 플레이어를 찾아내는지")
    void findPlayerByEmailTest() {
        playerRepository.save(player);

        Optional<Player> persist = playerRepository.findByEmail("member@gmail.com");

        assertTrue(persist.isPresent());
        assertThat(persist.get().getNickname()).isEqualTo("member");
        assertThat(persist.get().getEmail()).isEqualTo("member@gmail.com");

    }
}