package com.bb.stardium.domain.club.domain;

import com.bb.stardium.domain.club.domain.exception.NotAllowCityException;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@NoArgsConstructor
@Getter
@Embeddable
public class Address {

    private String city;

    private String section;

    private String detail;

    private String checkCityName(String city) {
        if (!city.contains("서울")) {
            throw new NotAllowCityException();
        }
        return city;
    }
}
