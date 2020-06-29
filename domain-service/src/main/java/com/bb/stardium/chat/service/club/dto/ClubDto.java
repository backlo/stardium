package com.bb.stardium.chat.service.club.dto;

import com.bb.stardium.chat.domain.club.Address;
import com.bb.stardium.chat.domain.player.Player;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ClubDto {
    private final Long id;
    private final String title;
    private final String intro;
    private final Address address;
    private final int playersLimit;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    private final Player master;

    @Builder
    public ClubDto(Long id, String title, String intro, String city,
                   String section, String detail, int playersLimit,
                   LocalDateTime startTime, LocalDateTime endTime, Player master) {
        this.id = id;
        this.title = title;
        this.intro = intro;
        this.address = convertAddress(city, section, detail);
        this.playersLimit = playersLimit;
        this.startTime = startTime;
        this.endTime = endTime;
        this.master = master;
    }

    private Address convertAddress(String city, String section, String detail) {
        return Address.builder()
                .city(city)
                .section(section)
                .detail(detail)
                .build();
    }
}
