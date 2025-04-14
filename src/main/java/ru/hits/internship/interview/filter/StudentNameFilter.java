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
        String[] nameParts = filter.getStudentName().trim().split("\\s+", 2);
        String firstPart = nameParts[0];
        String secondPart = nameParts.length > 1 ? nameParts[1] : "";

        Join<InterviewEntity, StudentEntity> studentJoin = root.join("student");
        Join<StudentEntity, UserEntity> userJoin = studentJoin.join("user");

        return criteriaBuilder.or(
                toPredicateWhenFirstNameIsFirstPart(criteriaBuilder, userJoin, firstPart, secondPart),
                toPredicateWhenLastNameIsFirstPart(criteriaBuilder, userJoin, firstPart, secondPart)
        );
    }

    private Predicate toPredicateWhenFirstNameIsFirstPart(
            CriteriaBuilder criteriaBuilder, Join<StudentEntity, UserEntity> userJoin,
            String firstPart, String secondPart
    ) {
        Predicate firstNamePredicate = criteriaBuilder.like(criteriaBuilder.lower(userJoin.get("firstName")), "%" + firstPart.toLowerCase() + "%");
        Predicate lastNamePredicate = criteriaBuilder.like(criteriaBuilder.lower(userJoin.get("lastName")), "%" + secondPart.toLowerCase() + "%");

        if (secondPart.isEmpty()) {
            return firstNamePredicate;
        } else {
            return criteriaBuilder.and(firstNamePredicate, lastNamePredicate);
        }
    }

    private Predicate toPredicateWhenLastNameIsFirstPart(
            CriteriaBuilder criteriaBuilder, Join<StudentEntity, UserEntity> userJoin,
            String firstPart, String secondPart
    ) {
        Predicate firstNamePredicate = criteriaBuilder.like(criteriaBuilder.lower(userJoin.get("firstName")), "%" + secondPart.toLowerCase() + "%");
        Predicate lastNamePredicate = criteriaBuilder.like(criteriaBuilder.lower(userJoin.get("lastName")), "%" + firstPart.toLowerCase() + "%");

        if (secondPart.isEmpty()) {
            return lastNamePredicate;
        } else {
            return criteriaBuilder.and(firstNamePredicate, lastNamePredicate);
        }
    }
}
