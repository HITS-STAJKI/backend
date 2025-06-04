package ru.hits.internship.chat.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hits.internship.chat.repository.ChatRepository;
import ru.hits.internship.chat.repository.MessageRepository;
import ru.hits.internship.user.model.common.UserRole;
import ru.hits.internship.user.model.dto.user.AuthUser;

import java.util.UUID;

@Service("macf")
@RequiredArgsConstructor
public class MessageAccessControlFacade {
    private final ChatRepository chatRepository;
    private final MessageRepository messageRepository;

    public boolean isChatOfStudent(UUID chatId, AuthUser user) {
        var studentRole = user.roles().get(UserRole.STUDENT);

        return studentRole != null && chatRepository.isChatOfStudent(chatId, studentRole.id());
    }

    public boolean isOwner(UUID messageId, UUID userId) {
        return messageRepository.isOwner(messageId, userId);
    }
}
