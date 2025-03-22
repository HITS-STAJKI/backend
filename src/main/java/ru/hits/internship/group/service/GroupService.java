package ru.hits.internship.group.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.hits.internship.common.models.Pagination.PagedListDto;
import ru.hits.internship.group.dto.CreateGroupDto;
import ru.hits.internship.group.dto.GroupDto;
import ru.hits.internship.group.dto.GroupFilter;
import ru.hits.internship.group.dto.UpdateGroupDto;
import java.util.UUID;

public interface GroupService {
    GroupDto createGroup(CreateGroupDto createGroupDto);

    PagedListDto<GroupDto> getGroups(GroupFilter groupFilter, Pageable pageable);

    GroupDto updateGroup(UUID id, UpdateGroupDto updateGroupDto);

    void deleteGroup(UUID id);
}
