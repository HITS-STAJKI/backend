package ru.hits.internship.practice.specification;

import org.springframework.data.jpa.domain.Specification;
import ru.hits.internship.practice.entity.PracticeEntity;

import java.util.List;
import java.util.UUID;

public class PracticeSpecification {
    public static Specification<PracticeEntity> hasStudentName(String studentName) {
        return (root, query, criteriaBuilder) -> {
            if (studentName == null || studentName.isEmpty()) {
                return criteriaBuilder.conjunction();
            }

            return criteriaBuilder.like(root.get("student").get("user").get("fullName"), "%" + studentName + "%");
        };
    }

    public static Specification<PracticeEntity> hasGroupInGroupIds(List<UUID> groupIds) {
        return (root, query, criteriaBuilder) -> {
            if (groupIds == null || groupIds.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            return root.get("student").get("group").get("id").in(groupIds);
        };
    }

    public static Specification<PracticeEntity> hasCompanyId(UUID companyId) {
        return (root, query, criteriaBuilder) -> {
            if (companyId == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("company").get("id"), companyId);
        };
    }

    public static Specification<PracticeEntity> hasReport(Boolean withReport) {
        return (root, query, criteriaBuilder) -> {
            if (withReport == null) {
                return criteriaBuilder.conjunction();
            } else if (withReport) {
                return criteriaBuilder.isNotNull(root.get("report").get("fileId"));
            } else {
                return criteriaBuilder.isNull(root.get("report").get("fileId"));
            }
        };
    }

    public static Specification<PracticeEntity> isReportApproved(Boolean isReportApproved) {
        return (root, query, criteriaBuilder) -> {
            if (isReportApproved == null) {
                return criteriaBuilder.conjunction();
            }
            if (isReportApproved) {
                return criteriaBuilder.and(
                        criteriaBuilder.isNotNull(root.get("report")),
                        criteriaBuilder.isTrue(root.get("report").get("isApproved"))
                );
            } else {
                return criteriaBuilder.or(
                        criteriaBuilder.isNull(root.get("report")),
                        criteriaBuilder.isFalse(root.get("report").get("isApproved"))
                );
            }
        };
    }

    public static Specification<PracticeEntity> isPracticeApproved(Boolean isPracticeApproved) {
        return (root, query, criteriaBuilder) -> {
            if (isPracticeApproved == null) {
                return criteriaBuilder.conjunction();
            }
            if (isPracticeApproved) {
                return criteriaBuilder.isTrue(root.get("isApproved"));
            } else {
                return criteriaBuilder.isFalse(root.get("isApproved"));
            }
        };
    }

    public static Specification<PracticeEntity> isArchived(Boolean isArchived) {
        return (root, query, criteriaBuilder) -> {
            if (isArchived == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("isArchived"), isArchived);
        };
    }
}
