package com.bb.stardium.service.club.dto;

import com.bb.stardium.domain.club.Address;
import com.bb.stardium.domain.player.Player;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ClubDto {
    private final String title;
    private final String intro;
    private final Address address;
    private final int playerLimit;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    private final Player master;

    @Builder
    public ClubDto(String title, String intro, String city,
                   String section, String detail, int playerLimit,
                   LocalDateTime startTime, LocalDateTime endTime, Player master) {
        this.title = title;
        this.intro = intro;
        this.address = convertAddress(city, section, detail);
        this.playerLimit = playerLimit;
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
