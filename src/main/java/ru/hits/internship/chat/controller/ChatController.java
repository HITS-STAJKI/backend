package ru.hits.internship.chat.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hits.internship.chat.model.chat.ChatInfoDto;
import ru.hits.internship.chat.service.ChatService;
import ru.hits.internship.user.model.dto.user.AuthUser;

@RestController
@RequestMapping("/api/v1/chats")
@RequiredArgsConstructor
@Tag(name = "Чат студента")
public class ChatController {
    private final ChatService chatService;

    @Operation(summary = "Получить информацию о своем чате (для студента)")
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/my")
    @PreAuthorize("hasRole('STUDENT')")
    public ChatInfoDto getMyChatInfo(@AuthenticationPrincipal AuthUser authUser) {
        return chatService.getMyChatInfo(authUser.id());
    }
}
