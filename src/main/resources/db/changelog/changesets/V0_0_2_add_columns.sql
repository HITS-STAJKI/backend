ALTER TABLE report_comments
    ADD COLUMN created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP;

ALTER TABLE interview_comments
    ADD COLUMN created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP;

ALTER TABLE report_comments
    ADD COLUMN modified_at TIMESTAMP;

ALTER TABLE interview_comments
    ADD COLUMN modified_at TIMESTAMP;

ALTER TABLE companies
    ADD COLUMN logo_filename VARCHAR(50);

ALTER TABLE practices
    ADD COLUMN is_archived BOOLEAN NOT NULL DEFAULT FALSE;

