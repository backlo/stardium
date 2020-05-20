package com.bb.stardium.domain.player.service;

import com.bb.stardium.domain.player.domain.repository.PlayerRepository;
import com.bb.stardium.domain.player.domain.Player;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Transactional
    public boolean registrationPlayer(Player newPlayer) {
        if (playerRepository.existsByEmail(newPlayer.getEmail())) {
            playerRepository.saveAndFlush(newPlayer);
            return true;
        }

        return false;
    }

    @Transactional(readOnly = true)
    public Player findPlayer(Long id) {
        return playerRepository
                .findById(id)
                .orElse(Player.builder().build());
    }

    @Transactional
    public Player editPlayer(Long playerId, Player updatePlayer) {
        Player player = findPlayer(playerId);

        return player.update(updatePlayer);
    }

    @Transactional
    @Modifying
    public boolean withdrawPlayer(Long id) {
        if (playerRepository.existsById(id)) {
            playerRepository.deleteById(id);
            return true;
        }

        return false;
    }
}
