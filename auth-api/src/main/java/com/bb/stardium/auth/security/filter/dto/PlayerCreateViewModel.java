package com.bb.stardium.auth.security.filter.dto;

import com.bb.stardium.auth.security.filter.dto.core.PlayerViewModel;
import com.bb.stardium.error.exception.FieldsEmptyException;
import lombok.Getter;
import org.springframework.util.StringUtils;

@Getter
public class PlayerCreateViewModel extends PlayerViewModel {
    public PlayerViewModel checkNullField() {
        if (isEmptyOneOfFields()) {
            throw new FieldsEmptyException();
        }

        return this;
    }

    private boolean isEmptyOneOfFields() {
        return StringUtils.isEmpty(this.email) ||
                StringUtils.isEmpty(this.password) ||
                StringUtils.isEmpty(this.nickname);
    }
}