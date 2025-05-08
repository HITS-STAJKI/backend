package ru.hits.internship.chat.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hits.internship.chat.repository.ChatRepository;
import ru.hits.internship.chat.repository.MessageRepository;
import java.util.UUID;

@Service("macf")
@RequiredArgsConstructor
public class MessageAccessControlFacade {
    private final ChatRepository chatRepository;
    private final MessageRepository messageRepository;

    public boolean isChatOfStudent(UUID chatId, UUID userId) {
        return chatRepository.isChatOfStudent(chatId, userId);
    }

    public boolean isOwner(UUID messageId, UUID userId) {
        return messageRepository.isOwner(messageId, userId);
    }
}
