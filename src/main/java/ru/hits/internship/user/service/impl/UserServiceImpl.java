package ru.hits.internship.user.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.hits.internship.common.Foundation;
import ru.hits.internship.common.exceptions.NotFoundException;
import ru.hits.internship.common.models.pagination.PagedListDto;
import ru.hits.internship.common.models.response.Response;
import ru.hits.internship.user.mapper.UserMapper;
import ru.hits.internship.user.model.common.UserRole;
import ru.hits.internship.user.model.dto.user.UserDetailsDto;
import ru.hits.internship.user.model.dto.user.UserDto;
import ru.hits.internship.user.model.dto.user.UserEditDto;
import ru.hits.internship.user.model.dto.user.UserShortDto;
import ru.hits.internship.user.model.entity.UserEntity;
import ru.hits.internship.user.model.entity.role.*;
import ru.hits.internship.user.repository.*;
import ru.hits.internship.user.service.UserService;

import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public PagedListDto<UserDto> getAllUsers(UUID userId, Pageable pageable) {
        Page<UserEntity> userPage = userRepository.findAllByIdNot(userId, pageable);
        Page<UserDto> userDtoPage = userPage.map(UserMapper.INSTANCE::toDto);

        return new PagedListDto<>(userDtoPage);
    }

    @Override
    public UserShortDto updateUser(UUID userId, UserEditDto editDto) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(UserEntity.class, userId));

        UserEntity updatedUser = UserMapper.INSTANCE.updateUser(user, editDto);
        userRepository.save(updatedUser);

        return UserMapper.INSTANCE.toShortDto(updatedUser);
    }

    @Override
    public Response deleteRole(UUID userId, UUID roleId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(UserEntity.class, userId));

        RoleEntity role = user.getRoles().stream()
                .filter(r -> r.getId().equals(roleId))
                .findFirst()
                .orElseThrow(() -> new NotFoundException(RoleEntity.class, roleId));

        user.getRoles().remove(role);
        userRepository.save(user);

        return new Response("Роль успешно удалена", HttpStatus.OK.value());
    }

    @Override
    public UserDetailsDto getUserDetailsById(UUID id) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(UserEntity.class, id));

        Map<UserRole, RoleEntity> rolesByType = user.getRoles().stream()
                .collect(Collectors.toMap(RoleEntity::getUserRole, Function.identity()));

        DeanEntity dean = Foundation.safeCast(rolesByType.get(UserRole.DEAN), DeanEntity.class);
        TeacherEntity teacher = Foundation.safeCast(rolesByType.get(UserRole.TEACHER), TeacherEntity.class);
        CuratorEntity curator = Foundation.safeCast(rolesByType.get(UserRole.CURATOR), CuratorEntity.class);
        StudentEntity student = Foundation.safeCast(rolesByType.get(UserRole.STUDENT), StudentEntity.class);

        return UserMapper.INSTANCE.toDetailsDto(user, dean, teacher, curator, student);
    }
}
