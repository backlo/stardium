package com.bb.stardium.aws.util.media;

import com.bb.stardium.error.exception.FileConvertException;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileConverter {
    private static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd-HH-mm-ss-";

    public File convert(MultipartFile file) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(YYYY_MM_DD_HH_MM_SS);
        File convertFile = new File(LocalDateTime.now().format(dateTimeFormatter) + file.getOriginalFilename());

        try {
            if (convertFile.createNewFile()) {
                FileOutputStream fos = new FileOutputStream(convertFile);
                fos.write(file.getBytes());
                return convertFile;
            }
        } catch (IOException e) {
            convertFile.delete();
        }

        throw new FileConvertException();
    }
}
