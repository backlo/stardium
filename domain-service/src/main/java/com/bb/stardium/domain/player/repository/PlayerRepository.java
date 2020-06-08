package com.bb.stardium.domain.player.repository;

import com.bb.stardium.domain.player.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
    Optional<Player> findByEmail(String email);

    boolean existsByEmailOrNickname(String email, String nickname);

    boolean existsByNickname(String nickname);
}
