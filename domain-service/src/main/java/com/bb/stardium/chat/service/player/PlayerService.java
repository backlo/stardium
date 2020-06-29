package com.bb.stardium.chat.service.player;

import com.bb.stardium.chat.domain.player.Player;
import com.bb.stardium.chat.domain.player.repository.PlayerRepository;
import com.bb.stardium.chat.service.player.dto.PlayerDto;
import com.bb.stardium.chat.service.player.dto.PlayerEditDto;
import com.bb.stardium.chat.service.player.exception.EmailAlreadyExistException;
import com.bb.stardium.chat.service.player.exception.NicknameAlreadyExistException;
import com.bb.stardium.chat.service.player.exception.PlayerNotFoundException;
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
        Player newPlayer = Player.builder()
                .playerDto(checkEmailAndNickname(newPlayerInfo))
                .build();

        return playerRepository.save(newPlayer);
    }

    @Transactional(readOnly = true)
    PlayerDto checkEmailAndNickname(PlayerDto playerDto) {
        if (playerRepository.existsByEmail(playerDto.getEmail())) {
            throw new EmailAlreadyExistException();
        }
        if (playerRepository.existsByNickname(playerDto.getNickname())) {
            throw new NicknameAlreadyExistException();
        }

        return playerDto;
    }

    @Transactional(readOnly = true)
    public Player findPlayerByEmail(String email) {
        return playerRepository.findByEmail(email)
                .orElseThrow(PlayerNotFoundException::new);
    }

    @Transactional
    public Player editPlayer(PlayerEditDto updatePlayerInfo) {
        Player editPlayer = updatePlayerInfo.getAuthPlayer();

        if (isExistByNickname(updatePlayerInfo) &&
                !editPlayer.getNickname().equals(updatePlayerInfo.getNickname())) {
            throw new NicknameAlreadyExistException();
        }

        return editPlayer.update(updatePlayerInfo);
    }

    @Transactional(readOnly = true)
    boolean isExistByNickname(PlayerEditDto updatePlayerDto) {
        return playerRepository.existsByNickname(updatePlayerDto.getNickname());
    }

    @Transactional
    public String editPlayerProfileImage(PlayerEditDto updatePlayerDto) {
        Player editPlayer = updatePlayerDto.getAuthPlayer();
        return editPlayer.updateProfile(updatePlayerDto);
    }

}
