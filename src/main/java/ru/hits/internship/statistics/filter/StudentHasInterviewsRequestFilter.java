package ru.hits.internship.statistics.filter;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Component;
import ru.hits.internship.common.filters.Filter;
import ru.hits.internship.interview.entity.InterviewEntity;
import ru.hits.internship.statistics.dto.StudentFilter;
import ru.hits.internship.user.model.entity.role.StudentEntity;

@Component("statsStudentHasInterviewsRequestFilter")
public class StudentHasInterviewsRequestFilter implements Filter<StudentEntity, StudentFilter> {
    @Override
    public boolean isApplicable(StudentFilter filter) {
        return filter.hasInterviews() != null;
    }

    @Override
    public Predicate toPredicate(Root<StudentEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder, StudentFilter filter) {
        Join<InterviewEntity, StudentEntity> interviewJoin = root.join("interviews", JoinType.LEFT);

        if (Boolean.TRUE.equals(filter.hasInterviews())) {
            return criteriaBuilder.isNotNull(interviewJoin.get("id"));
        } else {
            return criteriaBuilder.isNull(interviewJoin.get("id"));
        }
    }
}
