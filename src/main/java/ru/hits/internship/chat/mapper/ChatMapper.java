package ru.hits.internship.chat.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.hits.internship.chat.entity.ChatEntity;
import ru.hits.internship.chat.model.chat.ChatDto;
import ru.hits.internship.chat.model.chat.ChatInfoDto;
import ru.hits.internship.user.mapper.StudentMapper;
import ru.hits.internship.user.model.entity.role.StudentEntity;

@Mapper(componentModel = "spring")
public interface ChatMapper {
    @Mapping(target = "studentId", source = "student.id")
    ChatDto toDto(ChatEntity chat);

    @Mapping(target = "student", source = "studentEntity")
    @Mapping(target = "messages", ignore = true)
    @Mapping(target = "id", ignore = true)
    ChatEntity toEntity(StudentEntity studentEntity);

    @Mapping(target = "unreadCount", ignore = true)
    @Mapping(target = "studentId", source = "chat.student.id")
    @Mapping(target = "chatId", source = "chat.id")
    ChatInfoDto toInfoDto(ChatEntity chat);
}
