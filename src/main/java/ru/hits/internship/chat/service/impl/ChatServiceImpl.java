package ru.hits.internship.chat.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.hits.internship.chat.entity.ChatEntity;
import ru.hits.internship.chat.mapper.ChatMapper;
import ru.hits.internship.chat.model.chat.ChatDto;
import ru.hits.internship.chat.repository.ChatRepository;
import ru.hits.internship.chat.service.ChatService;
import ru.hits.internship.common.exceptions.BadRequestException;
import ru.hits.internship.common.exceptions.NotFoundException;
import ru.hits.internship.user.model.entity.role.StudentEntity;
import ru.hits.internship.user.repository.StudentRepository;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {
    private final ChatRepository chatRepository;
    private final StudentRepository studentRepository;
    private final ChatMapper chatMapper;

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
}
