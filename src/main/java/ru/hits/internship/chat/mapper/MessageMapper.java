package ru.hits.internship.chat.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import ru.hits.internship.chat.entity.ChatEntity;
import ru.hits.internship.chat.entity.MessageEntity;
import ru.hits.internship.chat.model.message.EditMessageRequest;
import ru.hits.internship.chat.model.message.MessageDto;
import ru.hits.internship.chat.model.message.SendMessageRequest;
import ru.hits.internship.user.model.common.UserRole;
import ru.hits.internship.user.model.entity.UserEntity;
import ru.hits.internship.user.utils.RoleChecker;
import java.time.LocalDateTime;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface MessageMapper {
    @Mapping(target = "sender", source = "user")
    @Mapping(target = "modifiedAt", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    MessageEntity toEntity(SendMessageRequest sendMessageRequest, UserEntity user, ChatEntity chat);


    @Mapping(target = "isRead", ignore = true)
    @Mapping(target = "sentAt", source = "createdAt")
    @Mapping(target = "senderId", source = "sender.id")
    @Mapping(target = "isEdited", expression = "java(messageEntity.getModifiedAt() != null)")
    MessageDto toDto(MessageEntity messageEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            unmappedTargetPolicy = ReportingPolicy.IGNORE)
    void update(@MappingTarget MessageEntity message, EditMessageRequest editMessageRequest);
}
