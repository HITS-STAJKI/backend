-- liquibase formatted sql

-- changeset shuml:1742798381496-17
ALTER TABLE languages
    ADD CONSTRAINT uc_languages_name UNIQUE (name);

-- changeset shuml:1742798381496-1
ALTER TABLE interview_comments
    ALTER COLUMN content TYPE VARCHAR(255) USING (content::VARCHAR(255));

-- changeset shuml:1742798381496-2
ALTER TABLE report_comments
    ALTER COLUMN content TYPE VARCHAR(255) USING (content::VARCHAR(255));

-- changeset shuml:1742798381496-3
ALTER TABLE interview_comments
    ALTER COLUMN created_at SET NOT NULL;

-- changeset shuml:1742798381496-4
ALTER TABLE report_comments
    ALTER COLUMN created_at SET NOT NULL;

-- changeset shuml:1742798381496-5
ALTER TABLE companies
    ALTER COLUMN description TYPE VARCHAR(255) USING (description::VARCHAR(255));

-- changeset shuml:1742798381496-6
ALTER TABLE users
    ALTER COLUMN email TYPE VARCHAR(255) USING (email::VARCHAR(255));

-- changeset shuml:1742798381496-7
ALTER TABLE users
    ALTER COLUMN first_name TYPE VARCHAR(255) USING (first_name::VARCHAR(255));

-- changeset shuml:1742798381496-8
ALTER TABLE users
    ALTER COLUMN last_name TYPE VARCHAR(255) USING (last_name::VARCHAR(255));

-- changeset shuml:1742798381496-9
ALTER TABLE companies
    ALTER COLUMN logo_filename TYPE VARCHAR(255) USING (logo_filename::VARCHAR(255));

-- changeset shuml:1742798381496-10
ALTER TABLE companies
    ALTER COLUMN logo_filename SET NOT NULL;

-- changeset shuml:1742798381496-11
ALTER TABLE interview_comments
    ALTER COLUMN modified_at SET NOT NULL;

-- changeset shuml:1742798381496-12
ALTER TABLE report_comments
    ALTER COLUMN modified_at SET NOT NULL;

-- changeset shuml:1742798381496-13
ALTER TABLE companies
    ALTER COLUMN name TYPE VARCHAR(255) USING (name::VARCHAR(255));

-- changeset shuml:1742798381496-14
ALTER TABLE languages
    ALTER COLUMN name TYPE VARCHAR(255) USING (name::VARCHAR(255));

-- changeset shuml:1742798381496-15
ALTER TABLE stacks
    ALTER COLUMN name TYPE VARCHAR(255) USING (name::VARCHAR(255));

-- changeset shuml:1742798381496-16
ALTER TABLE groups
    ALTER COLUMN number TYPE VARCHAR(255) USING (number::VARCHAR(255));

