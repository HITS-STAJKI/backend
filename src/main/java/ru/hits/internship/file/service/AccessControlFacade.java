package ru.hits.internship.file.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hits.internship.file.enumeration.FileType;
import ru.hits.internship.file.repository.FileRepository;

import java.util.UUID;

@Service("acf")
@RequiredArgsConstructor
public class AccessControlFacade {
    private final FileRepository fileRepository;

    public boolean hasAccess(UUID userId, UUID fileId) {
        return fileRepository.findById(fileId).
                map(f -> f.getType() == FileType.LOGO ||
                        isOwner(userId, f.getUserId()))
                .orElse(false);
    }

    public boolean isOwner(UUID userId, UUID ownerId) {
        return userId.equals(ownerId);
    }
}
