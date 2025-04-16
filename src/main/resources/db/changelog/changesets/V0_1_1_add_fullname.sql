-- liquibase formatted sql

-- changeset shuml:1744731880126-4
ALTER TABLE users ADD full_name VARCHAR(255);

UPDATE users
SET full_name = 'Unknown'
WHERE full_name IS NULL;

-- changeset shuml:1744731880126-5
ALTER TABLE users ALTER COLUMN  full_name SET NOT NULL;

-- changeset shuml:1744731880126-8
ALTER TABLE users DROP COLUMN first_name;
ALTER TABLE users DROP COLUMN last_name;

-- changeset shuml:1744731880126-1
ALTER TABLE files ALTER COLUMN name TYPE VARCHAR(255) USING (name::VARCHAR(255));

-- changeset shuml:1744731880126-2
ALTER TABLE reports ALTER COLUMN  updated_at DROP NOT NULL;

