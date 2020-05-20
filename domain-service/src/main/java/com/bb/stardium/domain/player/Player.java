package com.bb.stardium.domain.player.domain;

import com.bb.stardium.domain.match.Match;
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

    @Column(name = "nickname", length = 64, nullable = false, unique = true)
    private String nickname;

    @Column(name = "email", length = 64, nullable = false, unique = true)
    private String email;

    @Column(name = "password", length = 64, nullable = false)
    private String password;

    @Column(name = "statusMessage")
    private String statusMessage;

    @Embedded
    @AttributeOverride(name = "url", column = @Column(name = "profile_image_url"))
    private PlayerProfileImage profile = PlayerProfileImage.defaultImage();

    @OneToMany(mappedBy = "club", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Match> clubs = new ArrayList<>();

    @Builder
    public Player(String nickname, String email, String password, String statusMessage) {
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.statusMessage = statusMessage;
    }

    public Player update(Player updatePlayer) {
        this.nickname = updatePlayer.getNickname();
        this.email = updatePlayer.getEmail();
        this.password = updatePlayer.getPassword();
        this.statusMessage = updatePlayer.getStatusMessage();
        this.profile = updatePlayer.getProfile();

        return this;
    }
}
