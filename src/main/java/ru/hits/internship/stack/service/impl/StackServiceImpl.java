package ru.hits.internship.stack.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.hits.internship.common.exceptions.AlreadyExistsException;
import ru.hits.internship.common.exceptions.NotFoundException;
import ru.hits.internship.stack.entity.StackEntity;
import ru.hits.internship.stack.mapper.StackMapper;
import ru.hits.internship.stack.models.CreateStackDto;
import ru.hits.internship.stack.models.StackDto;
import ru.hits.internship.stack.models.UpdateStackDto;
import ru.hits.internship.stack.repository.StackRepository;
import ru.hits.internship.stack.service.StackService;
import ru.hits.internship.stack.specification.StackSpecification;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StackServiceImpl implements StackService {

    private final StackRepository repository;
    private final StackMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public List<StackDto> getAllStacks(String query) {
        Specification<StackEntity> spec = StackSpecification.hasName(query);
        List<StackEntity> stacks = repository.findAll(spec);

        return stacks.stream().map(mapper::map).toList();
    }

    @Override
    @Transactional
    public StackDto createStack(CreateStackDto createStackDto) {
        if (repository.existsByName(createStackDto.getName())) {
            throw new AlreadyExistsException("Стек с именем \"%s\" уже существует".formatted(createStackDto.getName()));
        }

        StackEntity stack = mapper.map(createStackDto);
        StackEntity savedStack = repository.save(stack);

        return mapper.map(savedStack);
    }

    @Override
    @Transactional
    public StackDto updateStack(UUID stackId, UpdateStackDto updateStackDto) {
        if (repository.existsByNameAndIdNot(updateStackDto.getName(), stackId)) {
            throw new AlreadyExistsException("Стек с именем \"%s\" уже существует".formatted(updateStackDto.getName()));
        }

        StackEntity stack = repository.findById(stackId)
                .orElseThrow(() -> new NotFoundException("Стек с id %s не найден".formatted(stackId)));

        mapper.update(stack, updateStackDto);
        StackEntity savedStack = repository.save(stack);

        return mapper.map(savedStack);
    }

    @Override
    @Transactional
    public void deleteStack(UUID stackId) {
        StackEntity stack = repository.findById(stackId)
                .orElseThrow(() -> new NotFoundException("Стек с id %s не найден".formatted(stackId)));

        repository.delete(stack);
    }
}
