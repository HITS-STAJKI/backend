ALTER TABLE reports
    DROP COLUMN is_approved,
    ADD COLUMN grade INTEGER CHECK (grade >= 2 AND grade <= 5);