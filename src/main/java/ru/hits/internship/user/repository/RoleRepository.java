package ru.hits.internship.user.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.hits.internship.common.models.pagination.PagedListDto;
import ru.hits.internship.user.model.entity.role.RoleEntity;
import ru.hits.internship.user.specification.RoleSpecification;

import java.util.UUID;
import java.util.function.Function;

public interface RoleRepository<R extends RoleEntity, ID> extends JpaRepository<R, ID>, JpaSpecificationExecutor<R> {
    default <D> PagedListDto<D> findAll(UUID userId, String fullName, Pageable pageable, Function<R, D> mapper) {
        Specification<R> spec = RoleSpecification.likeFullName(fullName);

        Page<R> rolePage = findAll(spec, pageable);
        Page<D> roleDtoPage = rolePage.map(mapper);

        return new PagedListDto<>(roleDtoPage);
    }

    default <D> PagedListDto<D> findAll(Specification<R> specification, Pageable pageable, Function<R, D> mapper) {

        Page<R> rolePage = findAll(specification, pageable);
        Page<D> roleDtoPage = rolePage.map(mapper);

        return new PagedListDto<>(roleDtoPage);
    }
}
