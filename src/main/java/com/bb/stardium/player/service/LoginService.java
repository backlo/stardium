package com.bb.stardium.player.service;

import com.bb.stardium.player.domain.repository.PlayerRepository;
import com.bb.stardium.player.dto.PlayerRequestDto;
import com.bb.stardium.player.dto.PlayerSessionDto;
import com.bb.stardium.player.service.exception.AuthenticationFailException;
import com.bb.stardium.player.service.exception.EmailNotExistException;
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
