package com.bb.stardium.api.error;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ApiErrorResponse {
    private String type;
    private String title;
    private int status;
    private String detail;
    private String instance;
}
