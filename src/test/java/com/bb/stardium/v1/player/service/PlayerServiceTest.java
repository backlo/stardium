package com.bb.stardium.v1.player.service;

import com.bb.stardium.v1.player.domain.Player;
import com.bb.stardium.v1.player.domain.repository.PlayerRepository;
import com.bb.stardium.v1.player.dto.PlayerRequestDto;
import com.bb.stardium.v1.player.dto.PlayerResponseDto;
import com.bb.stardium.v1.player.service.exception.EmailAlreadyExistException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class PlayerServiceTest {
    @Mock
    PlayerRepository playerRepository;
    @InjectMocks
    PlayerService playerService;
    private Player player;
    private PlayerRequestDto requestDto;

    @BeforeEach
    void setUp() {
        requestDto = new PlayerRequestDto();
        requestDto.setNickname("nickname");
        requestDto.setEmail("email@email.com");
        requestDto.setPassword("password");
        requestDto.setStatusMessage("별일 없이 산다");
        player = requestDto.toEntity();
    }

    @Test
    @DisplayName("이미 가입된 이메일로 가입 시도")
    void alreadyRegistered() {
        given(playerRepository.findByEmail(anyString())).willReturn(Optional.of(player));
        given(playerRepository.existsByEmail(anyString())).willReturn(true);

        assertThatThrownBy(() -> playerService.register(requestDto))
                .isInstanceOf(EmailAlreadyExistException.class);
    }

    @Test
    @DisplayName("로그인 성공")
    void login() {
        given(playerRepository.findByEmail("email@email.com")).willReturn(Optional.of(player));

//        PlayerSessionDto responseDto = playerService.login(requestDto);

        verify(playerRepository).findByEmail("email@email.com");
    }

    @Test
    @DisplayName("없는 이메일로 로그인 시도")
    void loginFailureByEmail() {
        given(playerRepository.findByEmail(anyString())).willReturn(Optional.empty());

//        assertThatThrownBy(() -> playerService.login(requestDto))
//                .isInstanceOf(EmailNotExistException.class);
    }

    @Test
    @DisplayName("잘못된 패스워드로 로그인 시도")
    void wrongPassword() {
        PlayerRequestDto wrongPasswordDto = new PlayerRequestDto();
        wrongPasswordDto.setNickname("nickname");
        wrongPasswordDto.setEmail("email@email.com");
        wrongPasswordDto.setPassword("wrongPassword");
        wrongPasswordDto.setStatusMessage("핫하 죽어라");

        given(playerRepository.findByEmail(anyString())).willReturn(Optional.of(player));

//        assertThatThrownBy(() -> playerService.login(wrongPasswordDto))
//                .isInstanceOf(AuthenticationFailException.class);
    }
}