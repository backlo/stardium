package com.bb.stardium.v1.player.service;

import com.bb.stardium.v1.player.domain.repository.PlayerRepository;
import com.bb.stardium.v1.player.dto.PlayerRequestDto;
import com.bb.stardium.v1.player.dto.PlayerSessionDto;
import com.bb.stardium.v1.player.service.exception.AuthenticationFailException;
import com.bb.stardium.v1.player.service.exception.EmailNotExistException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LoginService {

    private final PlayerRepository playerRepository;

    public LoginService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Transactional(readOnly = true)
    public PlayerSessionDto login(final PlayerRequestDto requestDto) {
        try {
            return playerRepository.findByEmail(requestDto.getEmail())
                    .orElseThrow(EmailNotExistException::new)
                    .checkMatchPassword(requestDto.getPassword())
                    .toPlayerSessionDtoObject();
        } catch (Exception e) {
            throw new AuthenticationFailException(e);
        }
    }
}
