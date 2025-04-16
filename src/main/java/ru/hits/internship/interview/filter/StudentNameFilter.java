package ru.hits.internship.interview.filter;

import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Component;
import ru.hits.internship.common.filters.Filter;
import ru.hits.internship.interview.entity.InterviewEntity;
import ru.hits.internship.interview.models.InterviewFilter;
import ru.hits.internship.user.model.entity.UserEntity;
import ru.hits.internship.user.model.entity.role.StudentEntity;

@Component("interviewFilterStudentName")
public class StudentNameFilter implements Filter<InterviewEntity, InterviewFilter> {

    @Override
    public boolean isApplicable(InterviewFilter filter) {
        return filter.getStudentName() != null && !filter.getStudentName().isBlank();
    }

    @Override
    public Predicate toPredicate(Root<InterviewEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder, InterviewFilter filter) {
        Join<InterviewEntity, StudentEntity> studentJoin = root.join("student");
        Join<StudentEntity, UserEntity> userJoin = studentJoin.join("user");

        return criteriaBuilder.like(criteriaBuilder.lower(userJoin.get("fullName")),
                "%" + filter.getStudentName().toLowerCase() + "%");
    }
}
