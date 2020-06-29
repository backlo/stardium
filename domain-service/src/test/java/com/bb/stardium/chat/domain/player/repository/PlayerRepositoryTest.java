package com.bb.stardium.chat.domain.player.repository;

import com.bb.stardium.chat.domain.player.Player;
import com.bb.stardium.chat.service.player.dto.PlayerDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class PlayerRepositoryTest {
    private final Player newPlayer = Player.builder()
            .playerDto(
                    PlayerDto.builder()
                            .email("member@gmail.com")
                            .nickname("member")
                            .password("password")
                            .build()
            )
            .build();

    @Autowired
    private PlayerRepository playerRepository;

    @BeforeEach
    void setUp() {
        playerRepository.save(newPlayer);
    }

    @Test
    @DisplayName("PlayerRepository findByEmail, existsByEmail, existsByNickname 테스트")
    void findPlayerByEmailTest() {
        Optional<Player> persist = playerRepository.findByEmail("member@gmail.com");

        assertTrue(persist.isPresent());
        assertThat(persist.get().getNickname()).isEqualTo("member");
        assertThat(persist.get().getEmail()).isEqualTo("member@gmail.com");

        assertTrue(playerRepository.existsByEmail("member@gmail.com"));
        assertTrue(playerRepository.existsByNickname("member"));

        assertFalse(playerRepository.existsByEmail("notExistMember@gamil.com"));
        assertFalse(playerRepository.existsByNickname("notExistMember"));
    }

    @AfterEach
    void tearDown() {
        playerRepository.delete(newPlayer);
    }
}