package com.bb.stardium.aws.util;

import com.bb.stardium.aws.util.media.FileConverter;
import com.bb.stardium.aws.util.media.S3FileUploader;
import com.bb.stardium.domain.player.PlayerProfileImage;
import com.bb.stardium.error.exception.MultipartFileEmptyException;
import com.bb.stardium.error.exception.NotImageFormException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Objects;

public class S3UploadUtils {
    private final S3FileUploader fileUploader;
    private final FileConverter fileConverter;

    public S3UploadUtils(S3FileUploader fileUploader, FileConverter fileConverter) {
        this.fileUploader = fileUploader;
        this.fileConverter = fileConverter;
    }

    public String updateAndGetProfileUrl(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new MultipartFileEmptyException();
        }

        if (!isImageMediaType(file)) {
            throw new NotImageFormException();
        }

        File convertFile = fileConverter.convert(file);
        String profileUrl = fileUploader.uploadFile(convertFile);

        convertFile.delete();

        return profileUrl;
    }

    private boolean isImageMediaType(MultipartFile file) {
        return Objects.requireNonNull(file.getContentType())
                    .toLowerCase().startsWith("image")
                || !file.isEmpty();
    }
}
