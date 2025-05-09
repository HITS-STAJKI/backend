package ru.hits.internship.chat.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.hits.internship.chat.entity.ChatEntity;
import ru.hits.internship.chat.entity.ChatReadStateEntity;
import ru.hits.internship.chat.mapper.ChatMapper;
import ru.hits.internship.chat.model.chat.ChatDto;
import ru.hits.internship.chat.model.chat.ChatInfoDto;
import ru.hits.internship.chat.repository.ChatReadStateRepository;
import ru.hits.internship.chat.repository.ChatRepository;
import ru.hits.internship.chat.repository.MessageRepository;
import ru.hits.internship.chat.service.ChatService;
import ru.hits.internship.common.exceptions.BadRequestException;
import ru.hits.internship.common.exceptions.NotFoundException;
import ru.hits.internship.user.model.entity.UserEntity;
import ru.hits.internship.user.model.entity.role.StudentEntity;
import ru.hits.internship.user.repository.StudentRepository;
import ru.hits.internship.user.repository.UserRepository;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {
    private final ChatRepository chatRepository;
    private final StudentRepository studentRepository;
    private final ChatMapper chatMapper;
    private final UserRepository userRepository;
    private final ChatReadStateRepository chatReadStateRepository;
    private final MessageRepository messageRepository;

    @Override
    @Transactional
    public ChatDto createChat(UUID studentId) {
        StudentEntity student = studentRepository.findById(studentId)
                .orElseThrow(() -> new NotFoundException(StudentEntity.class, studentId));

        if (chatRepository.existsByStudent_Id(studentId)) {
            throw new BadRequestException("Для данного студента уже создан чат");
        }

        ChatEntity chat = chatMapper.toEntity(student);

        return chatMapper.toDto(chatRepository.save(chat));
    }

    @Override
    @Transactional(readOnly = true)
    public ChatInfoDto getMyChatInfo(UUID id) {
        StudentEntity student = studentRepository.findByUser_Id(id)
                .orElseThrow(() -> new NotFoundException(StudentEntity.class, id));

        ChatEntity chat = chatRepository.findByStudent_Id(student.getId())
                .orElseThrow(() -> new NotFoundException(ChatEntity.class, "для студента " + student.getUser().getFullName()));

        ChatInfoDto chatInfo = chatMapper.toInfoDto(chat);

        UserEntity currentUser = userRepository.getReferenceById(id);

        ChatReadStateEntity chatReadState = chatReadStateRepository
                .findByChatIdAndUserId(chat.getId(), currentUser.getId())
                .orElse(null);

        long unreadCount = 0;
        if (chatReadState != null && chatReadState.getLastReadAt() != null) {
            unreadCount = messageRepository.countUnread(chat.getId(), currentUser.getId(), chatReadState.getLastReadAt());
        } else {
            unreadCount = messageRepository.countAllByChat_IdAndSender_IdNot(chat.getId(), currentUser.getId());
        }
        chatInfo.setUnreadCount(unreadCount);

        return chatInfo;
    }
}
