package ru.hits.internship.group.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.hits.internship.common.exceptions.NotFoundException;
import ru.hits.internship.common.models.pagination.PagedListDto;
import ru.hits.internship.group.dto.CreateGroupDto;
import ru.hits.internship.group.dto.GroupDto;
import ru.hits.internship.group.dto.GroupFilter;
import ru.hits.internship.group.dto.UpdateGroupDto;
import ru.hits.internship.group.entity.GroupEntity;
import ru.hits.internship.group.mapper.GroupMapper;
import ru.hits.internship.group.repository.GroupRepository;
import ru.hits.internship.group.service.GroupService;
import ru.hits.internship.group.validator.GroupValidator;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {
    private final GroupRepository groupRepository;
    private final GroupValidator groupValidator;
    private final GroupMapper groupMapper;

    @Override
    @Transactional
    public GroupDto createGroup(CreateGroupDto createGroupDto) {
        groupValidator.checkGroupAlreadyExists(createGroupDto.getNumber());

        GroupEntity group = groupMapper.toEntity(createGroupDto);
        GroupEntity savedGroup = groupRepository.save(group);

        // TODO: Согласовать мапперы для студентов и подключить к мапперу групп для корректного маппинга
        return groupMapper.toDto(savedGroup);
    }

    @Override
    @Transactional(readOnly = true)
    public PagedListDto<GroupDto> getGroups(GroupFilter groupFilter, Pageable pageable) {
        // TODO: Реализовать с учетом фильтрации и пагинации, на данный момент - только с учетом pageable

        Page<GroupEntity> groupsPage = groupRepository.findAll(pageable);

        return new PagedListDto<>(groupsPage.map(groupMapper::toDto));
    }

    @Override
    @Transactional
    public GroupDto updateGroup(UUID id, UpdateGroupDto updateGroupDto) {
        GroupEntity group = groupRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Группа с id %s не найдена".formatted(id)));

        groupValidator.checkGroupAlreadyExistsNotSame(updateGroupDto.getNumber(), id);

        groupMapper.updateGroupEntity(group, updateGroupDto);

        GroupEntity savedGroup = groupRepository.save(group);

        // TODO: Согласовать мапперы для студентов и подключить к мапперу групп для корректного маппинга
        return groupMapper.toDto(savedGroup);
    }

    @Override
    @Transactional
    public void deleteGroup(UUID id) {
        GroupEntity group = groupRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Группа с id %s не найдена".formatted(id)));

        groupRepository.delete(group);
    }
}
