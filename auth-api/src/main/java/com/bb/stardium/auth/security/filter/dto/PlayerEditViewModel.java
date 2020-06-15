package com.bb.stardium.auth.security.filter.dto;

import com.bb.stardium.auth.security.filter.dto.core.PlayerViewModel;
import lombok.Getter;

@Getter
public class PlayerEditViewModel extends PlayerViewModel {
    private String statusMessage;
}
