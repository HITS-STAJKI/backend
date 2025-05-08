package ru.hits.internship.chat.service;

import ru.hits.internship.chat.model.chat.ChatDto;
import java.util.UUID;

public interface ChatService {
    ChatDto createChat(UUID studentId);
}
