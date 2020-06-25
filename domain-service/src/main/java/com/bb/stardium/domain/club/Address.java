package com.bb.stardium.domain.club;

import com.bb.stardium.domain.club.exception.NotAllowCityException;
import com.bb.stardium.domain.club.exception.NotAllowSectionException;
import lombok.Builder;
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

    @Builder
    public Address(String city, String section, String detail) {
        this.city = checkCityName(city);
        this.section = checkSectionName(section);
        this.detail = detail;
    }

    private String checkCityName(String city) {
        if (!city.contains("서울")) {
            throw new NotAllowCityException();
        }
        return city;
    }

    private String checkSectionName(String section) {
        if (!Section.isExistSection(section)) {
            throw new NotAllowSectionException();
        }

        return section;
    }
}
