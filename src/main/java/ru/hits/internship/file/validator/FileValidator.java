package ru.hits.internship.file.validator;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import ru.hits.internship.common.exceptions.BadRequestException;
import ru.hits.internship.file.util.FileUtils;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Component
public class FileValidator {
    private final Set<String> allowedMimeTypes = new HashSet<>(Arrays.asList(
            "application/pdf",
            "application/msword",
            "application/vnd.openxmlformats-officedocument.wordprocessingml.document",
            "image/png",
            "image/jpeg",
            "image/svg+xml",
            "image/gif"
    ));

    private final Set<String> allowedExtensions = new HashSet<>(Arrays.asList(
            "pdf",
            "doc",
            "docx",
            "png",
            "jpg",
            "jpeg",
            "svg",
            "gif"
    ));

    private final Set<String> dangerousExtensions = new HashSet<>(Arrays.asList(
            "exe",
            "bat",
            "cmd",
            "sh",
            "js",
            "vbs",
            "wsf",
            "jar",
            "msi"
    ));


    public void validateFile(MultipartFile file) {
        if (file.isEmpty()) {
            throw new BadRequestException("Пустой файл");
        }

        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null || file.isEmpty()) {
            throw new BadRequestException("Пустое имя файла");
        }


        String extension = FileUtils.extractExtension(originalFilename).toLowerCase();

        if (dangerousExtensions.contains(extension)) {
            throw new BadRequestException("Небезопасное расширение файла: " + extension);
        }

        if (!allowedExtensions.contains(extension)) {
            throw new BadRequestException(String.format("Недопустимое расширение файла: %s. Разрешены только: %s",
                    extension, String.join(", ", allowedExtensions)));
        }

        if (!allowedMimeTypes.contains(file.getContentType())) {
            throw new BadRequestException("Такой тип файла не поддерживается");
        }
    }

}
