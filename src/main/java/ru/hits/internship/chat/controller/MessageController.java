package ru.hits.internship.chat.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hits.internship.chat.model.message.EditMessageRequest;
import ru.hits.internship.chat.model.message.MessageDto;
import ru.hits.internship.chat.model.message.SendMessageRequest;
import ru.hits.internship.chat.service.MessageService;
import ru.hits.internship.common.models.pagination.PagedListDto;
import ru.hits.internship.common.models.response.Response;
import ru.hits.internship.user.model.dto.user.AuthUser;
import java.util.UUID;

@RestController
@Tag(name = "Сообщения в пользовательском чате", description = "Отвечает за работу с сообщениями в чате")
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/chats/{chatId}/messages")
public class MessageController {

    private final MessageService messageService;

    @Operation(summary = "Отправить сообщение в чат")
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping
    @PreAuthorize("@macf.isChatOfStudent(#chatId, #user.id) or hasRole('DEAN')")
    public MessageDto sendMessage(
            @AuthenticationPrincipal AuthUser user,
            @PathVariable @Parameter(description = "Id чата") UUID chatId,
            @Valid @RequestBody SendMessageRequest sendMessageRequest
    ) {
        return messageService.sendMessage(chatId, user.id(), sendMessageRequest);
    }

    @Operation(summary = "Отредактировать сообщение в чате")
    @SecurityRequirement(name = "bearerAuth")
    @PutMapping("/{messageId}")
    @PreAuthorize("@macf.isOwner(#messageId, #user.id)")
    public MessageDto editMessage(
            @AuthenticationPrincipal AuthUser user,
            @PathVariable @Parameter(description = "Id чата") UUID chatId,
            @PathVariable @Parameter(description = "Id сообщения") UUID messageId,
            @Valid @RequestBody EditMessageRequest editMessageRequest
            ) {
        return messageService.editMessage(messageId, editMessageRequest);
    }

    @Operation(summary = "Удалить сообщение из чата")
    @SecurityRequirement(name = "bearerAuth")
    @DeleteMapping("/{messageId}")
    @PreAuthorize("@macf.isOwner(#messageId, #user.id)")
    public Response deleteMessage(
            @AuthenticationPrincipal AuthUser user,
            @PathVariable @Parameter(description = "Id чата") UUID chatId,
            @PathVariable @Parameter(description = "Id сообщения") UUID messageId
    ) {
        messageService.deleteMessage(messageId);
        return new Response("Сообщение успешно удалено", HttpStatus.OK.value());
    }

    @Operation(summary = "Получить список сообщений чата")
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/list")
    @PreAuthorize("@macf.isChatOfStudent(#chatId, #user.id) or hasRole('DEAN')")
    public PagedListDto<MessageDto> getMessagesList(
            @AuthenticationPrincipal AuthUser user,
            @PathVariable @Parameter(description = "Id чата") UUID chatId,
            @ParameterObject @PageableDefault(sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        return messageService.getMessagesByChatId(chatId, user.id(), pageable);
    }
}
