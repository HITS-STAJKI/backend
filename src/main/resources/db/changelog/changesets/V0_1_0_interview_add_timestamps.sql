ALTER TABLE report_comments
    ADD COLUMN IF NOT EXISTS created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT now(),
    ADD COLUMN IF NOT EXISTS modified_at TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT now();