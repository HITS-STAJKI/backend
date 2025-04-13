package ru.hits.internship.user.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.hits.internship.common.models.pagination.PagedListDto;
import ru.hits.internship.user.model.entity.role.RoleEntity;

import java.util.UUID;
import java.util.function.Function;

public interface RoleRepository<R extends RoleEntity, ID> extends JpaRepository<R, ID> {
    Page<R> findAllByUserIdNot(UUID excludedUserId, Pageable pageable);

    default <D> PagedListDto<D> findAll(UUID userId, Pageable pageable, Function<R, D> mapper) {
        Page<R> rolePage = findAllByUserIdNot(userId, pageable);
        Page<D> roleDtoPage = rolePage.map(mapper);

        return new PagedListDto<>(roleDtoPage);
    }
}
