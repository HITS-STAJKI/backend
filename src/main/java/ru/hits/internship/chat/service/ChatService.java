package ru.hits.internship.chat.service;

import ru.hits.internship.chat.model.chat.ChatInfoDto;
import ru.hits.internship.user.model.entity.role.StudentEntity;

import java.util.UUID;

public interface ChatService {
    void createChat(StudentEntity student);

    ChatInfoDto getMyChatInfo(UUID id);
}
