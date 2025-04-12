package ru.hits.internship.partner.controller;

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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hits.internship.common.models.pagination.PagedListDto;
import ru.hits.internship.common.models.response.Response;
import ru.hits.internship.group.dto.GroupFilter;
import ru.hits.internship.partner.models.CompanyPartnerDto;
import ru.hits.internship.partner.models.CreateCompanyPartnerDto;
import ru.hits.internship.partner.models.PartnerFilter;
import ru.hits.internship.partner.models.ShortCompanyPartnerDto;
import ru.hits.internship.partner.models.UpdateCompanyPartnerDto;
import ru.hits.internship.partner.service.CompanyPartnerService;

import java.util.UUID;

@RestController
@Tag(name = "Компании-партнеры", description = "Отвечает за работу с компаниями-партнерами")
@RequiredArgsConstructor
@RequestMapping("/api/v1/partner")
// TODO: не забыть про логотип и куратора
public class CompanyPartnerController {
    private final CompanyPartnerService companyPartnerService;

    @Operation(
            summary = "Получение списка партнеров хитса",
            description = "Позволяет получить список компаний-партнеров с пагинацией"
    )
    @GetMapping("/list")
    public PagedListDto<ShortCompanyPartnerDto> getPartners(@Valid @ParameterObject PartnerFilter partnerFilter,
            @ParameterObject @PageableDefault(sort = "name", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        return companyPartnerService.getCompanyPartners(partnerFilter, pageable);
    }

    @Operation(
            summary = "Получение подробной информации о партнере",
            description = "Позволяет получить подробную информацию о партнере"
    )
    @GetMapping("/{partnerId}")
    public CompanyPartnerDto getPartnerInfo(
            @Parameter(description = "Идентификатор партнера") @PathVariable UUID partnerId
    ) {
        return companyPartnerService.getCompanyPartner(partnerId);
    }

    @Operation(
            summary = "Обновление информации о партнере",
            description = "Позволяет получить обновить информацию о партнере"
    )
    @SecurityRequirement(name = "bearerAuth")
    @PutMapping("/{partnerId}")
    public CompanyPartnerDto updatePartnerInfo(
            @Parameter(description = "Идентификатор партнера") @PathVariable UUID partnerId,
            @RequestBody @Valid UpdateCompanyPartnerDto updateCompanyPartnerDto
    ) {
        return companyPartnerService.updateCompanyPartner(partnerId, updateCompanyPartnerDto);
    }

    @Operation(
            summary = "Создание партнера",
            description = "Позволяет создать нового партнера"
    )
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping
    public CompanyPartnerDto createPartner(
            @RequestBody @Valid CreateCompanyPartnerDto createCompanyPartnerDto
    ) {
        return companyPartnerService.createCompanyPartner(createCompanyPartnerDto);
    }

    @Operation(
            summary = "Удаление партнера",
            description = "Позволяет удалить партнера"
    )
    @SecurityRequirement(name = "bearerAuth")
    @DeleteMapping("/{partnerId}")
    public Response deletePartner(
            @Parameter(description = "Идентификатор партнера") @PathVariable UUID partnerId
    ) {
        companyPartnerService.deleteCompanyPartner(partnerId);

        return new Response("Партнер успешно удален", HttpStatus.OK.value());
    }
}
