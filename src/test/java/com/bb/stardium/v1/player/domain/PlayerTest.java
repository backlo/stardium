package com.bb.stardium.v1.player.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class PlayerTest {
    @Autowired
    TestEntityManager em;

    private Player player;

    @BeforeEach
    void setUp() {
        player = Player.builder()
                .email("a@a.com")
                .nickname("andole")
                .password("abcd")
                .build();
    }

    @Test
    @DisplayName("패스워드를 제대로 검사하는지")
    void matchPassword() {
        assertThat(player.isMatchPassword("wrong")).isFalse();
        assertThat(player.isMatchPassword("abcd")).isTrue();
    }

    @Test
    @DisplayName("업데이트가 잘 동작하는지")
    void update() {
        em.persist(player);

        Player update = Player.builder()
                .email("update@update.com")
                .nickname("update")
                .password("password")
                .build();

        player.update(update);
        em.persist(player);

        assertThat(player.getEmail()).isEqualTo("update@update.com");
        assertThat(player.getNickname()).isEqualTo("update");
    }
}