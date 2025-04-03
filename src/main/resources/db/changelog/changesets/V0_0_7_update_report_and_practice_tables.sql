-- liquibase formatted sql

ALTER TABLE reports
    ALTER COLUMN updated_at DROP NOT NULL;

-- changeset bob:1743598865982-1
ALTER TABLE report_comments
    ALTER COLUMN modified_at DROP NOT NULL;

-- changeset bob:1743595309038-1
ALTER TABLE reports
    ADD author_id UUID;

-- changeset bob:1743595309038-2
ALTER TABLE reports
    ALTER COLUMN author_id SET NOT NULL;

-- changeset bob:1743595309038-3
ALTER TABLE practices
    ADD report_id UUID;

-- changeset bob:1743595309038-4
ALTER TABLE practices
    ADD CONSTRAINT uc_practices_report UNIQUE (report_id);

-- changeset bob:1743595309038-5
ALTER TABLE practices
    ADD CONSTRAINT FK_PRACTICES_ON_REPORT FOREIGN KEY (report_id) REFERENCES reports (id);

-- changeset bob:1743595309038-6
ALTER TABLE reports
    ADD CONSTRAINT FK_REPORTS_ON_AUTHOR FOREIGN KEY (author_id) REFERENCES users (id);
