package com.bb.stardium.service.player;

import com.bb.stardium.domain.player.Player;
import com.bb.stardium.domain.player.repository.PlayerRepository;
import com.bb.stardium.service.player.dto.PlayerDto;
import com.bb.stardium.service.player.exception.EmailAndNickNameAlreadyExistException;
import com.bb.stardium.service.player.exception.PlayerNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    public Player registrationPlayer(PlayerDto newPlayerInfo) {
        if (isExistsByEmailAndNickname(newPlayerInfo)) {
            throw new EmailAndNickNameAlreadyExistException();
        }

        Player newPlayer = newPlayerInfo.toEntity();
        return playerRepository.save(newPlayer);
    }

    @Transactional(readOnly = true)
    boolean isExistsByEmailAndNickname(PlayerDto playerDto) {
        return playerRepository.existsByEmailOrNickname(playerDto.getEmail(), playerDto.getNickname());
    }

    @Transactional(readOnly = true)
    public Player findPlayer(Long id) {
        return playerRepository
                .findById(id)
                .orElseThrow(PlayerNotFoundException::new);
    }

    @Transactional(readOnly = true)
    public Player findPlayerById(Long id) {
        return playerRepository.findById(id)
                .orElseThrow(PlayerNotFoundException::new);
    }

    @Transactional(readOnly = true)
    public Player findPlayerByEmail(String email) {
        return playerRepository.findByEmail(email)
                .orElseThrow(PlayerNotFoundException::new);
    }

    @Transactional
    public Player editPlayer(Long playerId, PlayerDto updatePlayerInfo) {
        if (isExistByNickname(updatePlayerInfo)) {
            throw new EmailAndNickNameAlreadyExistException();
        }

        Player player = findPlayer(playerId);
        return player.update(updatePlayerInfo);
    }

    @Transactional(readOnly = true)
    boolean isExistByNickname(PlayerDto updatePlayerDto) {
        return playerRepository.existsByNickname(updatePlayerDto.getNickname());
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
