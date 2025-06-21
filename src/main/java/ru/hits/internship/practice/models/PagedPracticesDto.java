package ru.hits.internship.practice.models;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import ru.hits.internship.common.models.pagination.PagedListDto;
import ru.hits.internship.user.model.dto.role.response.StudentDto;

@AllArgsConstructor
@Data
@Schema(description = "dto списка практик с информацией о студенте")
public class PagedPracticesDto {
    private StudentDto student;
    private PagedListDto<PracticeDto> practices;
}
