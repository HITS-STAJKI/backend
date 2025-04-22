package ru.hits.internship.file.service;

import io.minio.GetObjectArgs;
import io.minio.GetObjectResponse;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import io.minio.errors.MinioException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.InputStream;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MinioStorageService {
    private final MinioClient minioClient;
    private final String bucketName;

    public String uploadFile(MultipartFile file, String name) throws MinioException {
        try (InputStream is = file.getInputStream()) {
            PutObjectArgs args = PutObjectArgs.builder()
                    .bucket(bucketName)
                    .object(name)
                    .stream(is, file.getSize(), -1)
                    .contentType(file.getContentType())
                    .build();

            minioClient.putObject(args);

            return name;
        } catch (Exception e) {
            throw new MinioException("Ошибка загрузки файла: " + e.getMessage());
        }
    }

    public Resource downloadFile(String name) throws MinioException {
        try {
            GetObjectArgs getObjectArgs = GetObjectArgs.builder()
                    .bucket(bucketName)
                    .object(name)
                    .build();

            GetObjectResponse response = minioClient.getObject(getObjectArgs);

            return new InputStreamResource(response);
        } catch (Exception e) {
            throw new MinioException("Ошибка загрузки файла: " + e.getMessage());
        }
    }

    public void deleteFile(String name) throws MinioException {
        try {
            RemoveObjectArgs removeObjectArgs = RemoveObjectArgs.builder()
                    .bucket(bucketName)
                    .object(name)
                    .build();

            minioClient.removeObject(removeObjectArgs);

        } catch (Exception e) {
            throw new MinioException("Ошибка удаления файла: " + e.getMessage());
        }
    }

    public String buildFileNameForUser(UUID userID, String fileName) {
        String extension = FilenameUtils.getExtension(fileName);

        if (extension != null && !extension.isEmpty()) {
            return String.format("%s/%s.%s", userID, UUID.randomUUID(), extension);
        }

        return String.format("%s/%s", userID, UUID.randomUUID());
    }
}
