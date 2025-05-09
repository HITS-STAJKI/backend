ALTER TABLE companies
    DROP COLUMN logo_filename,
    ADD COLUMN file_id UUID;
