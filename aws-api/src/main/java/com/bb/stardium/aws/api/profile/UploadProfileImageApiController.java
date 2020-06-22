package com.bb.stardium.aws.api.profile;

import com.bb.stardium.aws.api.profile.dto.ResponseUploadImage;
import com.bb.stardium.aws.util.S3UploadUtils;
import com.bb.stardium.domain.player.Player;
import com.bb.stardium.interceptor.annotation.AuthorizePlayer;
import com.bb.stardium.service.player.PlayerService;
import com.bb.stardium.service.player.dto.PlayerEditDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/static")
public class UploadProfileImageApiController {

    private final PlayerService playerService;
    private final S3UploadUtils s3UploadUtils;

    public UploadProfileImageApiController(PlayerService playerService, S3UploadUtils s3UploadUtils) {
        this.playerService = playerService;
        this.s3UploadUtils = s3UploadUtils;
    }

    @PutMapping(path = "/profile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ResponseUploadImage> uploadProfileImage(@RequestParam("file") MultipartFile multipartFile,
                                                                  @AuthorizePlayer Player authPlayer) {
        String updateProfileUrl = s3UploadUtils.updateAndGetProfileUrl(multipartFile);

        PlayerEditDto playerDto = PlayerEditDto.builder()
                    .authPlayer(authPlayer)
                    .profile(updateProfileUrl)
                .build();

        String editUrl =  playerService.editPlayerProfileImage(playerDto);

        return ResponseEntity.ok(
                ResponseUploadImage.builder()
                            .imageUrl(editUrl)
                        .build()
        );
    }
}
