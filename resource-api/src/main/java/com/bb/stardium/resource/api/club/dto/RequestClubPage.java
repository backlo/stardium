package com.bb.stardium.resource.api.club.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;

@Getter
@NoArgsConstructor
public class RequestClubPage {
    private int page;
    private int size;
    private Sort.Direction direction;
    private String properties;

    public void setPage(int page) {
        this.page = page <= 0 ? 1 : page;
    }

    public void setSize(int size) {
        this.size = size > 50 ? 10 : size;
    }

    public void setDirection(Sort.Direction direction) {
        this.direction = StringUtils.isEmpty(direction.name()) ? Sort.Direction.DESC : direction;
    }

    public void setProperties(String properties) {
        this.properties = StringUtils.isEmpty(properties) ? "createTime" : properties;
    }

    public PageRequest of() {
        return PageRequest.of(page - 1, size, direction, properties);
    }
}
