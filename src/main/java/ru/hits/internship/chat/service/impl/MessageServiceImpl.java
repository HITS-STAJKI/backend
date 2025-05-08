package ru.hits.internship.chat.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.hits.internship.chat.entity.ChatEntity;
import ru.hits.internship.chat.entity.MessageEntity;
import ru.hits.internship.chat.mapper.MessageMapper;
import ru.hits.internship.chat.model.message.EditMessageRequest;
import ru.hits.internship.chat.model.message.MessageDto;
import ru.hits.internship.chat.model.message.SendMessageRequest;
import ru.hits.internship.chat.repository.ChatRepository;
import ru.hits.internship.chat.repository.MessageRepository;
import ru.hits.internship.chat.service.MessageService;
import ru.hits.internship.common.exceptions.NotFoundException;
import ru.hits.internship.common.models.pagination.PagedListDto;
import ru.hits.internship.user.model.entity.UserEntity;
import ru.hits.internship.user.repository.UserRepository;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;
    private final ChatRepository chatRepository;
    private final UserRepository userRepository;
    private final MessageMapper messageMapper;

    @Override
    @Transactional
    public MessageDto sendMessage(UUID chatId, UUID userId, SendMessageRequest sendMessageRequest) {
        ChatEntity chat = chatRepository.findById(chatId)
                .orElseThrow(() -> new NotFoundException(ChatEntity.class, chatId));

        UserEntity user = userRepository.getReferenceById(userId);

        MessageEntity message = messageMapper.toEntity(sendMessageRequest, user, chat);

        return messageMapper.toDto(messageRepository.save(message));
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

        messageRepository.markAsRead(chatId, userId); // TODO: надо подумать, хотим ли мы отображать пользователю уже как прочитанные
        // или же оставлять их непрочитанными (то есть возвращать page, а только потом помечать как прочитанные ?)

        Page<MessageEntity> page = messageRepository.findAllByChat_Id(chatId, pageable);
        // TODO: подумать, реально ли нам нужна именно offset-пагинация для чата?...
        // возможно, что-то по типу scroll-пагинации будет более подходящим вариантом (cursor-based) или оверхэд?

        return new PagedListDto<>(page.map(messageMapper::toDto));
    }
}
