package ru.hits.internship.chat.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hits.internship.chat.model.chat.ChatInfoDto;
import ru.hits.internship.chat.model.message.SendMessageToStudentsRequest;
import ru.hits.internship.chat.service.ChatService;
import ru.hits.internship.chat.service.MessageService;
import ru.hits.internship.common.models.response.Response;
import ru.hits.internship.user.model.dto.user.AuthUser;

@RestController
@RequestMapping("/api/v1/chats")
@RequiredArgsConstructor
public class ChatController {
    private final ChatService chatService;
    private final MessageService messageService;

    @Operation(summary = "Получить информацию о своем чате (для студента)")
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/my")
    @PreAuthorize("hasRole('STUDENT')")
    public ChatInfoDto getMyChatInfo(@AuthenticationPrincipal AuthUser authUser) {
        return chatService.getMyChatInfo(authUser.id());
    }

    @Operation(summary = "Отправить сообщения определённым студентам")
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/studentMessages")
    @PreAuthorize("hasAnyRole('DEAN', 'EDUCATIONAL_PROGRAM_LEAD')")
    public Response sendMessages(
            @AuthenticationPrincipal AuthUser user,
            @Valid @RequestBody SendMessageToStudentsRequest sendRequest
    ) {
        return messageService.sendMessageToStudents(sendRequest);
    }
}
