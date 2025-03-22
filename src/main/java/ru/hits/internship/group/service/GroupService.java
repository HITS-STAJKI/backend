package ru.hits.internship.group.service;

import ru.hits.internship.group.dto.CreateGroupDto;
import ru.hits.internship.group.dto.GroupDto;
import ru.hits.internship.group.dto.UpdateGroupDto;
import java.util.UUID;

public interface GroupService {
    GroupDto createGroup(CreateGroupDto createGroupDto);

    GroupDto updateGroup(UUID id, UpdateGroupDto updateGroupDto);

    void deleteGroup(UUID id);
}
