package com.bb.stardium.v1.player.service;

import com.bb.stardium.v1.mediafile.domain.ProfileImage;
import com.bb.stardium.v1.player.domain.Player;
import com.bb.stardium.v1.player.domain.repository.PlayerRepository;
import com.bb.stardium.v1.player.dto.PlayerRequestDto;
import com.bb.stardium.v1.player.dto.PlayerResponseDto;
import com.bb.stardium.v1.player.dto.PlayerSessionDto;
import com.bb.stardium.v1.player.service.exception.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PlayerService {
    private static final Logger log = LoggerFactory.getLogger(PlayerService.class);

    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Transactional
    public PlayerResponseDto register(final PlayerRequestDto requestDto) {
        if (playerRepository.existsByEmail(requestDto.getEmail())) {
            throw new EmailAlreadyExistException();
        }
        Player newPlayer = playerRepository.save(requestDto.toEntity());

        return newPlayer.toPlayerResponseDtoObject();
    }

    public PlayerResponseDto findPlayerById(Long id) {
        return findById(id).toPlayerResponseDtoObject();
    }

    @Transactional(readOnly = true)
    public Player findById(Long id) {
        return playerRepository.findById(id)
                .orElseThrow(NoFoundPlayerIdException::new);
    }

    @Transactional
    public PlayerResponseDto update(PlayerRequestDto requestDto, ProfileImage profileImage, PlayerSessionDto sessionDto) {
        log.error("request: {}", requestDto.getPlayerId());
        log.error("request: {}", sessionDto.getEmail());
        Player player = findById(requestDto.getPlayerId());

        if (!player.isSamePlayer(sessionDto.getEmail())) {
            throw new MisMatchedPlayerAndSessionException();
        }

        player.update(requestDto.toEntity(), profileImage);
        return new PlayerResponseDto(player);
    }

    @Transactional(readOnly = true)
    public String findNicknameByPlayerId(final long playerId) {
        return playerRepository
                .findById(playerId)
                .map(Player::getNickname)
                .orElseThrow(EmailNotExistException::new);
    }
}