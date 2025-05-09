package ru.hits.internship.file.service;

import lombok.SneakyThrows;
import org.apache.tika.Tika;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import ru.hits.internship.file.enumeration.FileType;

import java.util.Set;

@Component
public class FileTypeDetector {
    private static final Set<String> IMAGE_MIME_PREFIXES = Set.of("image/");
    private static final Set<String> DOC_MIMES = Set.of(
            "application/pdf",
            "application/msword",
            "application/vnd.openxmlformats-officedocument.wordprocessingml.document"
    );

    private final Tika tika = new Tika();

    @SneakyThrows
    public FileType detect(MultipartFile file) {
        String mime = tika.detect(file.getInputStream(), file.getOriginalFilename());

        if (IMAGE_MIME_PREFIXES.stream().anyMatch(mime::startsWith)) {
            return FileType.LOGO;
        } else if (DOC_MIMES.contains(mime)) {
            return FileType.REPORT;
        } else {
            return FileType.OTHER;
        }
    }
}
