package ru.hits.internship.file.service.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import ru.hits.internship.file.dto.FileUploadedEvent;
import ru.hits.internship.file.service.MinioStorageService;

@Slf4j
@Component
@RequiredArgsConstructor
public class FileUploadRollbackListener {
    private final MinioStorageService minioStorageService;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_ROLLBACK)
    public void onUploadRollback(FileUploadedEvent fileUploadedEvent) {
        try {
            minioStorageService.deleteFile(fileUploadedEvent.getFileName());
        } catch (Exception e) {
            log.error("Ошибка удаления файла в MinIO после отката транзакции: {}", e.getMessage());
        }
    }
}
