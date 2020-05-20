package com.bb.stardium.v2.user.domain;

import org.springframework.data.annotation.Id;

import java.time.OffsetDateTime;

public class User {

    @Id
    private Long id;

    private OffsetDateTime createDataTime;

    private OffsetDateTime updateDateTime;

    private String nickname;

    private String email;

    private String passwrod;

    private String statusMessage;


}
