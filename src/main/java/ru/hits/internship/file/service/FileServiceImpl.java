package ru.hits.internship.file.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.hits.internship.common.exceptions.BadRequestException;
import ru.hits.internship.common.exceptions.NotFoundException;
import ru.hits.internship.common.models.pagination.PagedListDto;
import ru.hits.internship.file.dto.FileDeleteEvent;
import ru.hits.internship.file.dto.FileDto;
import ru.hits.internship.file.dto.FileUploadedEvent;
import ru.hits.internship.file.entity.FileEntity;
import ru.hits.internship.file.enumeration.FileType;
import ru.hits.internship.file.mapper.FileMapper;
import ru.hits.internship.file.repository.FileRepository;
import ru.hits.internship.file.util.FileUtils;
import ru.hits.internship.file.validator.FileValidator;
import ru.hits.internship.partner.repository.CompanyPartnerRepository;
import ru.hits.internship.user.model.dto.user.AuthUser;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {
    private final FileRepository fileRepository;
    private final CompanyPartnerRepository companyPartnerRepository;
    private final MinioStorageService minioStorageService;
    private final FileMapper fileMapper;
    private final FileValidator fileValidator;
    private final ApplicationEventPublisher eventPublisher;
    private final FileTypeDetector typeDetector;

    @Override
    @Transactional
    public FileDto uploadFile(UUID userId, MultipartFile file) {
        try {
            fileValidator.validateFile(file);

            String fileName = minioStorageService.buildFileNameForUser(userId,
                    FileUtils.sanitizeOriginalFileName(file.getOriginalFilename()));

            String storedFileName = minioStorageService.uploadFile(file, fileName);

            eventPublisher.publishEvent(new FileUploadedEvent(fileName));

            FileType type = typeDetector.detect(file);
            FileEntity fileEntity = fileMapper.toEntity(file, storedFileName, userId, type);

            FileEntity savedFile = fileRepository.saveAndFlush(fileEntity);

            return fileMapper.toDto(savedFile);
        } catch (Exception e) {
            throw new BadRequestException("Ошибка загрузки файла: " + e.getMessage());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Resource downloadFile(UUID userId, UUID fileId) {
        try {
            FileEntity fileEntity = fileRepository.findById(fileId)
                    .orElseThrow(() -> new NotFoundException(FileEntity.class, fileId));

            return minioStorageService.downloadFile(fileEntity.getObjectKey());
        } catch (Exception e) {
            throw new BadRequestException("Ошибка скачивания файла: " + e.getMessage());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public FileDto getFileMetadata(UUID fileId) {
        return fileRepository.findById(fileId)
                .map(fileMapper::toDto)
                .orElseThrow(() -> new NotFoundException(FileEntity.class, fileId));
    }

    @Override
    @Transactional
    public void deleteFile(UUID fileId) {
        try {
            FileEntity fileEntity = fileRepository.findById(fileId)
                    .orElseThrow(() -> new NotFoundException(FileEntity.class, fileId));

            fileRepository.delete(fileEntity);

            companyPartnerRepository.findByFileId(fileId)
                    .ifPresent(company -> {
                        company.setFileId(null);
                        companyPartnerRepository.save(company);
                    });

            eventPublisher.publishEvent(new FileDeleteEvent(fileEntity.getObjectKey()));
        } catch (Exception e) {
            throw new BadRequestException("Ошибка удаления файла: " + e.getMessage());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public PagedListDto<FileDto> getMyFiles(AuthUser authUser, Pageable pageable) {
        Page<FileDto> fileDtoPage = fileRepository.findAllByUserId(authUser.id(), pageable)
                .map(fileMapper::toDto);

        return new PagedListDto<>(fileDtoPage);
    }

    @Override
    @Transactional(readOnly = true)
    public PagedListDto<FileDto> getAllFilesByType(FileType type, Pageable pageable) {
        Page<FileDto> fileDtoPage = fileRepository.findAllByType(type, pageable)
                .map(fileMapper::toDto);

        return new PagedListDto<>(fileDtoPage);
    }
}
