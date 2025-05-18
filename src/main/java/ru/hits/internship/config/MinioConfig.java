package ru.hits.internship.config;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Getter
@Setter
@Slf4j
@Configuration
@ConfigurationProperties(prefix = "minio")
public class MinioConfig {
    @NotBlank
    private String endpoint;

    @NotBlank
    private String accessKey;

    @NotBlank
    private String secretKey;

    @NotBlank
    private String bucketName;

    @NotNull
    private Long connectTimeout;

    @NotNull
    private Long writeTimeout;

    @NotNull
    private Long readTimeout;


    @Bean
    public MinioClient minioClient() {
        try {
            MinioClient minioClient = MinioClient.builder()
                    .endpoint(endpoint)
                    .credentials(accessKey, secretKey)
                    .httpClient(
                            new OkHttpClient.Builder()
                                    .connectTimeout(connectTimeout, TimeUnit.MILLISECONDS)
                                    .writeTimeout(writeTimeout, TimeUnit.MILLISECONDS)
                                    .readTimeout(readTimeout, TimeUnit.MILLISECONDS)
                                    .build()
                    )
                    .build();

            boolean bucketExists = minioClient.bucketExists(
                    BucketExistsArgs.builder().bucket(bucketName).build()
            );

            if (!bucketExists) {
                minioClient.makeBucket(
                        MakeBucketArgs.builder().bucket(bucketName).build()
                );
                log.info("Бакет {} создан", bucketName);
            } else {
                log.info("Бакет {} уже существует", bucketName);
            }

            return minioClient;
        } catch (Exception e) {
            log.error("Ошибка при создании MinioClient: {}", e.getMessage());
            throw new RuntimeException("Ошибка при создании MinioClient", e);
        }
    }

    @Bean
    public String bucketName() {
        return bucketName;
    }
}
