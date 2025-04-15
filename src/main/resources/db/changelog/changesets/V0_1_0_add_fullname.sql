-- liquibase formatted sql

-- changeset shuml:1744731880126-3
CREATE TABLE interviews_languages (interview_entity_id UUID NOT NULL, languages_id UUID NOT NULL);


-- changeset shuml:1744731880126-4
ALTER TABLE users ADD full_name VARCHAR(255);

UPDATE users
SET full_name = 'Unknown'
WHERE full_name IS NULL;

-- changeset shuml:1744731880126-5
ALTER TABLE users ALTER COLUMN  full_name SET NOT NULL;

-- changeset shuml:1744731880126-6
ALTER TABLE interviews_languages ADD CONSTRAINT fk_intlan_on_interview_entity FOREIGN KEY (interview_entity_id) REFERENCES interviews (id);

-- changeset shuml:1744731880126-7
ALTER TABLE interviews_languages ADD CONSTRAINT fk_intlan_on_language_entity FOREIGN KEY (languages_id) REFERENCES languages (id);

-- changeset shuml:1744731880126-8
ALTER TABLE users DROP COLUMN first_name;
ALTER TABLE users DROP COLUMN last_name;

-- changeset shuml:1744731880126-1
ALTER TABLE files ALTER COLUMN name TYPE VARCHAR(255) USING (name::VARCHAR(255));

-- changeset shuml:1744731880126-2
ALTER TABLE reports ALTER COLUMN  updated_at DROP NOT NULL;

