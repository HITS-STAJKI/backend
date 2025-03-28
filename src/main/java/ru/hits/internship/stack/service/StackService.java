package ru.hits.internship.stack.service;

import ru.hits.internship.stack.models.CreateStackDto;
import ru.hits.internship.stack.models.StackDto;
import ru.hits.internship.stack.models.UpdateStackDto;

import java.util.List;
import java.util.UUID;

public interface StackService {

    List<StackDto> getAllStacks(String query);

    StackDto createStack(CreateStackDto createStackDto);

    StackDto updateStack(UUID stackId, UpdateStackDto updateStackDto);

    void deleteStack(UUID stackId);
}
