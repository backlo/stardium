package com.bb.stardium.aws.util.media;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.bb.stardium.error.exception.FileUploadException;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;

public class S3FileUploader {
    private static final int SUCCESS_FLAG = 1;

    private final AmazonS3 amazonS3Client;

    @Value("${s3.directory}")
    private String profileDirectoryName;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public S3FileUploader(AmazonS3 amazonS3Client) {
        this.amazonS3Client = amazonS3Client;
    }

    public String uploadFile(File file) {
        return uploadCloud(file);
    }

    private String uploadCloud(File uploadFile) {
        try {
            StringBuilder fileName = new StringBuilder();
            fileName.append(profileDirectoryName).append(uploadFile.getName());

            amazonS3Client.putObject(
                    new PutObjectRequest(bucket, fileName.toString(), uploadFile)
                            .withCannedAcl(CannedAccessControlList.PublicRead)
            );

            return amazonS3Client
                    .getUrl(bucket, fileName.toString())
                    .toString();
        } catch (Exception e) {
            throw new FileUploadException(e.getMessage());
        }
    }

    public int deleteFile(String fileName) {
        try {
            amazonS3Client.deleteObject(bucket, profileDirectoryName + fileName);
            return SUCCESS_FLAG;
        } catch (Exception e) {
            throw new FileUploadException(e.getMessage());
        }

    }
}
