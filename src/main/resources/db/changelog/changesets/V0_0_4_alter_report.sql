ALTER TABLE reports
    DROP COLUMN filename;

ALTER TABLE reports
    ADD COLUMN file_id UUID;