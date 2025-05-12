-- liquibase formatted sql

-- changeset sonya:20250512-1
ALTER TABLE practices ADD COLUMN stack_id UUID;

-- changeset sonya:20250512-2
ALTER TABLE practices ADD CONSTRAINT fk_practice_stack FOREIGN KEY (stack_id) REFERENCES stacks(id);

-- changeset sonya:20250512-3
-- TODO: После заполнения stack_id во всех existing practices:
-- ALTER TABLE practices ALTER COLUMN stack_id SET NOT NULL;