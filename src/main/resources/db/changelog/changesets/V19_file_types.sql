-- liquibase formatted sql

-- changeset shuml:1746689656220-1
ALTER TABLE files ADD type VARCHAR(255);

-- changeset shuml:1746689656220-2
ALTER TABLE files ALTER COLUMN  type SET NOT NULL;

