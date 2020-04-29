package com.bb.stardium.v1.common.util;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class S3FileUploader {
    private static final String PROFILE_DIRECTORY_NAME = "profile-image/";

    private final AmazonS3 amazonS3Client;
    private final String bucket;

    public S3FileUploader(AmazonS3 amazonS3, @Value("${cloud.aws.s3.bucket}") String bucket) {
        this.amazonS3Client = amazonS3;
        this.bucket = bucket;
    }

    public String uploadFile(File file) {
        return uploadCloud(file);
    }

    public void deleteFile(String fileNmae) {
        amazonS3Client.deleteObject(bucket,  PROFILE_DIRECTORY_NAME + fileNmae);
    }

    private String uploadCloud(File uploadFile) {
        String fileName = PROFILE_DIRECTORY_NAME + uploadFile.getName();
        amazonS3Client.putObject(new PutObjectRequest(bucket, fileName, uploadFile)
                .withCannedAcl(CannedAccessControlList.PublicRead));
        return amazonS3Client.getUrl(bucket, fileName).toString();
    }

}
