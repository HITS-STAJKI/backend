-- liquibase formatted sql

-- changeset sonya:make-student-group-nullable
ALTER TABLE students ALTER COLUMN group_id DROP NOT NULL;
