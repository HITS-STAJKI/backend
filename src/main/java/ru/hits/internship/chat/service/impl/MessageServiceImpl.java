package ru.hits.internship.chat.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.hits.internship.chat.entity.ChatEntity;
import ru.hits.internship.chat.entity.ChatReadStateEntity;
import ru.hits.internship.chat.entity.MessageEntity;
import ru.hits.internship.chat.mapper.MessageMapper;
import ru.hits.internship.chat.model.message.EditMessageRequest;
import ru.hits.internship.chat.model.message.MessageDto;
import ru.hits.internship.chat.model.message.SendMessageRequest;
import ru.hits.internship.chat.repository.ChatReadStateRepository;
import ru.hits.internship.chat.repository.ChatRepository;
import ru.hits.internship.chat.repository.MessageRepository;
import ru.hits.internship.chat.service.MessageService;
import ru.hits.internship.common.exceptions.NotFoundException;
import ru.hits.internship.common.models.pagination.PagedListDto;
import ru.hits.internship.user.model.entity.UserEntity;
import ru.hits.internship.user.repository.StudentRepository;
import ru.hits.internship.user.repository.UserRepository;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;
    private final ChatRepository chatRepository;
    private final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final MessageMapper messageMapper;
    private final ChatReadStateRepository chatReadStateRepository;

    @Override
    @Transactional
    public MessageDto sendMessage(UUID chatId, UUID userId, SendMessageRequest sendMessageRequest) {
        ChatEntity chat = chatRepository.findById(chatId)
                .orElseThrow(() -> new NotFoundException(ChatEntity.class, chatId));

        UserEntity user = userRepository.getReferenceById(userId);

        MessageEntity message = messageMapper.toEntity(sendMessageRequest, user, chat);
        message = messageRepository.saveAndFlush(message);

        ChatReadStateEntity chatReadState = chatReadStateRepository
                .findByChatIdAndUserId(chatId, userId)
                .orElseGet(() -> {
                    ChatReadStateEntity newChatReadState = new ChatReadStateEntity();
                    newChatReadState.setChat(chat);
                    newChatReadState.setUser(user);
                    return newChatReadState;
                });

        chatReadState.setLastReadAt(message.getCreatedAt());
        chatReadStateRepository.save(chatReadState);

        MessageDto messageDto = messageMapper.toDto(message);
        messageDto.setIsRead(true);

        return messageDto;
    }

    @Override
    @Transactional
    public MessageDto editMessage(UUID messageId, EditMessageRequest editMessageRequest) {
        MessageEntity message = messageRepository.findById(messageId)
                .orElseThrow(() -> new NotFoundException(MessageEntity.class, messageId));

        messageMapper.update(message, editMessageRequest);

        return messageMapper.toDto(messageRepository.save(message));
    }

    @Override
    @Transactional
    public void deleteMessage(UUID messageId) {
        MessageEntity message = messageRepository.findById(messageId)
                .orElseThrow(() -> new NotFoundException(MessageEntity.class, messageId));

        messageRepository.delete(message);
    }

    @Override
    @Transactional
    public PagedListDto<MessageDto> getMessagesByChatId(UUID chatId, UUID userId, Pageable pageable) {
        if (!chatRepository.existsById(chatId)) {
            throw new NotFoundException(ChatEntity.class, chatId);
        }

        UserEntity currentUser = userRepository.getReferenceById(userId);
        ChatEntity currentChat = chatRepository.getReferenceById(chatId);

        ChatReadStateEntity chatReadState = chatReadStateRepository
                .findByChatIdAndUserId(chatId, userId)
                .orElse(null);

        LocalDateTime originalLastReadAt = (chatReadState != null) ? chatReadState.getLastReadAt() : null;
        Page<MessageEntity> messages = messageRepository.findAllByChat_Id(chatId, pageable);

        Page<MessageDto> dtoPage = messages.map(message -> {
            MessageDto messageDto = messageMapper.toDto(message);
            boolean isSentByCurrentUser = message.getSender().getId().equals(currentUser.getId());
            if (isSentByCurrentUser) {
                messageDto.setIsRead(true);
            } else if (originalLastReadAt != null) {
                messageDto.setIsRead(!message.getCreatedAt().isAfter(originalLastReadAt));
            } else {
                messageDto.setIsRead(false);
            }
            return messageDto;
        });

        LocalDateTime now = LocalDateTime.now();
        if (chatReadState == null) {
            chatReadState = new ChatReadStateEntity();
            chatReadState.setChat(currentChat);
            chatReadState.setUser(currentUser);
        }

        chatReadState.setLastReadAt(now);
        chatReadStateRepository.save(chatReadState);

        return new PagedListDto<>(dtoPage);
    }
}
