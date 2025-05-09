package ru.hits.internship.file.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.multipart.MultipartFile;
import ru.hits.internship.file.util.FileUtils;

import java.util.Objects;


@RequiredArgsConstructor
public class FileSizeValidator implements ConstraintValidator<FileSize, MultipartFile> {
    private static final String PROPERTY_KEY = "spring.servlet.multipart.max-file-size";
    private static final String DEFAULT_MAX_VALUE = "100MB";
    private final Environment environment;


    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext constraintValidatorContext) {
        if (file == null || file.isEmpty()) {
            return true;
        }

        long maxFileSize = getMaxFileSize();

        long fileSize = file.getSize();

        boolean isValid = fileSize <= maxFileSize;

        if (!isValid) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(
                            String.format("Размер файла превышает допустимый лимит: %s. Вы загружаете: %s",
                                    FileUtils.formatFileSize(maxFileSize), FileUtils.formatFileSize(fileSize)))
                    .addConstraintViolation();
        }

        return isValid;
    }


    private long getMaxFileSize() {
        String stringValue = environment.getProperty(PROPERTY_KEY);

        return FileUtils.parseFileSize(Objects.requireNonNullElse(stringValue, DEFAULT_MAX_VALUE));
    }
}
