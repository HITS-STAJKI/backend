package ru.hits.internship.partner.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.hits.internship.common.exceptions.BadRequestException;
import ru.hits.internship.common.exceptions.NotFoundException;
import ru.hits.internship.file.entity.FileEntity;
import ru.hits.internship.file.enumeration.FileType;
import ru.hits.internship.file.repository.FileRepository;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class LogoValidator {
    private final FileRepository fileRepository;

    public void checkFileOrThrow(UUID fileId) {
        if (fileId != null) {
            var file = fileRepository.findById(fileId)
                    .orElseThrow(() -> new NotFoundException(FileEntity.class, fileId));

            if (file.getType() != FileType.LOGO) {
                throw new BadRequestException("Файл не является логотипом");
            }
        }
    }
}
