package com.bb.stardium.resource.api.common.dto;

import com.bb.stardium.resource.api.club.dto.ResponseClub;
import lombok.Getter;

import java.util.List;

@Getter
public abstract class ResponsePage {
    protected List<ResponseClub> content;
    protected Integer totalPages;
    protected Integer elementSize;
    protected Integer pageNumber;
    protected Boolean sortEnable;
}
