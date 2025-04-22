package ru.hits.internship.file.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hits.internship.file.repository.FileRepository;
import java.util.UUID;

@Service("acf")
@RequiredArgsConstructor
public class AccessControlFacade {
    private final FileRepository fileRepository;

    public boolean isOwner(UUID userId, UUID fileId) {
        return fileRepository.existsByIdAndUserId(fileId, userId);
    }
}
