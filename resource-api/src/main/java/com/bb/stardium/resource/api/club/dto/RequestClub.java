package com.bb.stardium.resource.api.club.dto;

import com.bb.stardium.chat.domain.player.Player;
import com.bb.stardium.chat.service.club.dto.ClubDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class RequestClub {

    @NotBlank(message = "title 를 적어주세요!")
    private String title;

    @NotBlank(message = "intro 를 적어주세요!")
    private String intro;

    @NotBlank(message = "시를 적어주세요!")
    private String city;

    @NotBlank(message = "구를 적어주세요!")
    private String section;

    @NotBlank(message = "상세 정보를 적어주세요!")
    private String detail;

    @Future
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime startTime;

    @Future
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime endTime;

    @Min(value = 2, message = "2인 이상이어야 합니다.")
    private int playersLimit;

    public ClubDto toEntity(Player master) {
        return ClubDto.builder()
                .title(this.title)
                .intro(this.intro)
                .city(this.city)
                .section(this.section)
                .detail(this.detail)
                .startTime(this.startTime)
                .endTime(this.endTime)
                .playersLimit(this.playersLimit)
                .master(master)
                .build();
    }

    public ClubDto toEntity() {
        return ClubDto.builder()
                .title(this.title)
                .intro(this.intro)
                .city(this.city)
                .section(this.section)
                .detail(this.detail)
                .startTime(this.startTime)
                .endTime(this.endTime)
                .playersLimit(this.playersLimit)
                .build();
    }

}
