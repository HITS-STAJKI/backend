package ru.hits.internship.file.service.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import ru.hits.internship.file.dto.FileDeleteEvent;
import ru.hits.internship.file.service.MinioStorageService;

@Slf4j
@Component
@RequiredArgsConstructor
public class FileDeleteEventListener {
    private final MinioStorageService minioStorageService;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void onAfterDelete(FileDeleteEvent event) {
        try {
            minioStorageService.deleteFile(event.getObjectKey());
        } catch (Exception ex) {
            log.error("Не получилось удалить из MinIO файл {}: {}", event.getObjectKey(), ex.getMessage());
        }
    }
}
