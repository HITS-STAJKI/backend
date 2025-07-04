package ru.hits.internship.chat.service;

import org.springframework.data.domain.Pageable;
import ru.hits.internship.chat.model.message.EditMessageRequest;
import ru.hits.internship.chat.model.message.MessageDto;
import ru.hits.internship.chat.model.message.SendMessageRequest;
import ru.hits.internship.chat.model.message.SendMessageToStudentsRequest;
import ru.hits.internship.common.models.pagination.PagedListDto;
import ru.hits.internship.common.models.response.Response;

import java.util.UUID;

public interface MessageService {
    MessageDto sendMessage(UUID chatId, UUID userId, SendMessageRequest sendMessageRequest);
    Response sendMessageToStudents(UUID userId, SendMessageToStudentsRequest sendRequest);
    MessageDto editMessage(UUID messageId, EditMessageRequest editMessageRequest);
    void deleteMessage(UUID messageId);
    PagedListDto<MessageDto> getMessagesByChatId(UUID chatId, UUID userId, Pageable pageable);
}
