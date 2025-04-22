package ru.hits.internship.file.service;

import org.springframework.core.io.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;
import ru.hits.internship.common.models.pagination.PagedListDto;
import ru.hits.internship.file.dto.FileDto;
import ru.hits.internship.user.model.dto.user.AuthUser;
import java.util.UUID;

public interface FileService {
    FileDto uploadFile(UUID userId, MultipartFile file);

    Resource downloadFile(UUID userId, UUID fileId);

    FileDto getFileMetadata(UUID fileId);

    void deleteFile(UUID fileId);

    PagedListDto<FileDto> getMyFiles(AuthUser authUser, Pageable pageable);
}
