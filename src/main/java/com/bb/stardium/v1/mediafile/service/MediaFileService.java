package com.bb.stardium.v1.mediafile.service;

import com.bb.stardium.v1.common.util.FileConverter;
import com.bb.stardium.v1.common.util.S3FileUploader;
import com.bb.stardium.v1.mediafile.domain.ProfileImage;
import com.bb.stardium.v1.mediafile.service.exception.NotImageFileException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Objects;

@Transactional
@Service
public class MediaFileService {

    private final S3FileUploader fileUploader;
    private final FileConverter fileConverter;

    public MediaFileService(S3FileUploader fileUploader, FileConverter fileConverter) {
        this.fileUploader = fileUploader;
        this.fileConverter = fileConverter;
    }

    public ProfileImage updateProfile(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return null;
        }

        if (!isImageMediaType(file)) {
            throw new NotImageFileException("이미지 형식이 맞지 않습니다.");
        }

        File convertFile = fileConverter.convert(file);
        String profileUrl = fileUploader.uploadFile(convertFile);
        String originFileName = file.getOriginalFilename();

        convertFile.delete();

        return new ProfileImage(profileUrl, originFileName);
    }

    private boolean isImageMediaType(MultipartFile file) {
        return Objects.requireNonNull(file.getContentType()).toLowerCase().startsWith("image") || !file.isEmpty();
    }
}
