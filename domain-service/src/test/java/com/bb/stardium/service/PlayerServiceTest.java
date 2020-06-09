package com.bb.stardium.service;

import com.bb.stardium.domain.player.Player;
import com.bb.stardium.domain.player.repository.PlayerRepository;
import com.bb.stardium.service.player.PlayerService;
import com.bb.stardium.service.player.dto.PlayerDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
class PlayerServiceTest {

    private final PlayerDto playerDto = PlayerDto.builder()
            .email("member@gmail.com")
            .nickname("member")
            .password("password")
            .build();

//    @Mock
//    private Player updatePlayerInfo;

    @Mock
    private PlayerRepository playerRepository;

    @InjectMocks
    private PlayerService playerService;

    @Test
    @DisplayName("회원 등록 테스트")
    void playerRegistrationTest() {
        given(playerRepository.existsByNickname(anyString())).willReturn(false);
        given(playerRepository.existsByEmail(anyString())).willReturn(false);

        Player newPlayer = Player.builder()
                .playerDto(playerDto)
                .build();

        given(playerRepository.save(newPlayer)).willReturn(newPlayer);

        Player savedPlayer = playerService.registrationPlayer(playerDto);

        verify(playerRepository, times(1)).save(newPlayer);
        assertThat(savedPlayer).isEqualTo(newPlayer);
    }

//    @Test
//    @DisplayName("사용자 찾기 테스트")
//    void searchPlayerTest() {
//        given(playerRepository.findById(anyLong())).willReturn(Optional.of(player));
//
//        Player findPlayer = playerService.findPlayer(anyLong());
//
//        verify(playerRepository, times(1)).findById(anyLong());
//        assertThat(findPlayer).isEqualTo(player);
//    }

//    @Test
//    @DisplayName("사용자 정보 업데이트 테스트")
//    void playerUpdateTest() {
//        given(updatePlayerInfo.getId()).willReturn(1L);
//        given(updatePlayerInfo.getPassword()).willReturn("change-password");
//        given(playerRepository.findById(anyLong())).willReturn(Optional.of(player));
//
//        Player updatedPlayer = playerService.editPlayer(1L, updatePlayerInfo);
//
//        verify(playerRepository, times(1)).findById(anyLong());
//        assertThat(player.getPassword()).isEqualTo(updatedPlayer.getPassword());
//    }
}