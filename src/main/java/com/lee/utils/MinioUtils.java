package com.lee.utils;

import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * @author KitenLee
 * * @date 2025/6/2
 */
@Component
public class MinioUtils {
    private final MinioClient minioClient;

    @Value("${minio.bucketName}")
    private String bucketName;

    public MinioUtils(@Value("${minio.endpoint}") String endpoint,
                     @Value("${minio.accessKey}") String accessKey,
                     @Value("${minio.secretKey}") String secretKey) {
        this.minioClient = MinioClient.builder()
                .endpoint(endpoint)
                .credentials(accessKey, secretKey)
                .build();
    }

    public String uploadFile(MultipartFile file) throws Exception {
        String filename = "/avater/"+file.getOriginalFilename();
        try (InputStream is = file.getInputStream()) {
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucketName)
                            .object(filename)
                            .stream(is, file.getSize(), -1)
                            .contentType(file.getContentType())
                            .build()
            );
        }
        return filename;
    }

}
