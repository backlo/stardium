package com.bb.stardium.domain.player;

import com.bb.stardium.domain.match.Match;
import com.bb.stardium.service.player.dto.PlayerDto;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@EqualsAndHashCode(of = "email")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    private OffsetDateTime createTime;

    @UpdateTimestamp
    private OffsetDateTime updateTime;

    @Column(name = "email", length = 64, nullable = false, unique = true)
    private String email;

    @Column(name = "nickname", length = 64, nullable = false, unique = true)
    private String nickname;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "statusMessage")
    private String statusMessage;

    @Column(name = "role")
    private String role;

    @Embedded
    @AttributeOverride(name = "url", column = @Column(name = "profile_image_url"))
    private PlayerProfileImage profile = PlayerProfileImage.defaultImage();

    @OneToMany(mappedBy = "club", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Match> clubs = new ArrayList<>();

    @Builder
    public Player(PlayerDto playerDto) {
        this.nickname = playerDto.getNickname();
        this.email = playerDto.getEmail();
        this.password = playerDto.getPassword();
        this.role = playerDto.getRole();
    }

    public Player update(PlayerDto updatePlayerDto) {
        if (!updatePlayerDto.getPassword().isEmpty()) {
            this.password = updatePlayerDto.getPassword();
        }
        if (!updatePlayerDto.getNickname().isEmpty()) {
            this.nickname = updatePlayerDto.getNickname();
        }
        if (!updatePlayerDto.getStatusMessage().isEmpty()) {
            this.statusMessage = updatePlayerDto.getStatusMessage();
        }
        if (!updatePlayerDto.getProfile().isEmpty()) {
            this.profile = updatePlayerDto.getProfile();
        }

        return this;
    }

}
