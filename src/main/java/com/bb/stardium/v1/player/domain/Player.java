package com.bb.stardium.v1.player.domain;


import com.bb.stardium.v1.bench.domain.Room;
import com.bb.stardium.v1.mediafile.domain.ProfileImage;
import com.bb.stardium.v1.player.dto.PlayerResponseDto;
import com.bb.stardium.v1.player.dto.PlayerSessionDto;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor]
@Getter
@EqualsAndHashCode(of = "id")
@Entity
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    private OffsetDateTime createDateTime;

    @UpdateTimestamp
    private OffsetDateTime updateDateTime;

    @Column(name = "nickname", length = 64, nullable = false, unique = true)
    private String nickname;

    @Column(name = "email", length = 64, nullable = false, unique = true)
    private String email;

    @Column(name = "password", length = 64, nullable = false)
    private String password;

    @Column(name = "statusMessage")
    private String statusMessage;

    @ManyToMany(mappedBy = "players")
    private List<Room> rooms = new ArrayList<>();

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "url", column = @Column(name = "profile_image_url")),
            @AttributeOverride(name = "fileName", column = @Column(name = "profile_image_file_name"))
    })
    private ProfileImage profile = ProfileImage.defaultImage();

    @Builder
    public Player(String nickname, String email, String password, String statusMessage) {
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.statusMessage = statusMessage;
    }

    public Player checkMatchPassword(final String password) {
        if (!this.password.equals(password)) {
            throw new MisMatchPasswordException();
        }

        return this;
    }

    public PlayerSessionDto toPlayerSessionDtoObject() {
        return PlayerSessionDto.builder()
                .playerId(this.id)
                .email(this.email)
                .build();
    }

    public boolean isSamePlayer(String playerEmail) {
        return this.email.equals(playerEmail);
    }

    public PlayerResponseDto toPlayerResponseDtoObject() {
        return PlayerResponseDto.builder()
                .player(this)
                .build();
    }

    public Player update(Player editPlayer, ProfileImage profileImage) {
        this.nickname = editPlayer.nickname;
        this.email = editPlayer.email;
        this.password = editPlayer.password;
        this.statusMessage = editPlayer.statusMessage;

        if (profileImage != null) {
            this.profile = profileImage;
        }

        return this;
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public Room removeRoom(Room room) {
        rooms.remove(room);
        return room;
    }
}
