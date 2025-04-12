package ru.hits.internship.user.service;

import ru.hits.internship.user.model.dto.role.request.create.DeanCreateDto;
import ru.hits.internship.user.model.dto.role.response.DeanDto;

public interface DeanService {
    DeanDto createDean(DeanCreateDto createDto);
}
