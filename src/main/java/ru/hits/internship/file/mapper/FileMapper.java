package ru.hits.internship.file.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.web.multipart.MultipartFile;
import ru.hits.internship.file.dto.FileDto;
import ru.hits.internship.file.entity.FileEntity;
import ru.hits.internship.file.enumeration.FileType;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface FileMapper {

    @Mapping(target = "objectKey", source = "fileName")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "name", source = "file.originalFilename")
    @Mapping(target = "type", source = "fileType")
    FileEntity toEntity(MultipartFile file, String fileName, UUID userId, FileType fileType);


    FileDto toDto(FileEntity fileEntity);
}
