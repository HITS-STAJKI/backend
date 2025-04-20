-- liquibase formatted sql

-- changeset semdev:1745061218451-1
ALTER TABLE students ADD is_academ BOOLEAN DEFAULT FALSE;
ALTER TABLE students ADD is_graduated BOOLEAN DEFAULT FALSE;

-- changeset semdev:1745061218451-2
ALTER TABLE students ALTER COLUMN  is_academ SET NOT NULL;

-- changeset semdev:1745061218451-4
ALTER TABLE students ALTER COLUMN  is_graduated SET NOT NULL;
ALTER TABLE curators DROP CONSTRAINT uk938v5nrtjvocl3dqw3q2a1bg0;

