-- liquibase formatted sql

-- changeset shuml:1745248049434-1
ALTER TABLE files ADD content_type VARCHAR(255);
ALTER TABLE files ADD object_key VARCHAR(255);

-- changeset shuml:1745248049434-2
ALTER TABLE files ALTER COLUMN  content_type SET NOT NULL;

-- changeset shuml:1745248049434-4
ALTER TABLE files ALTER COLUMN  object_key SET NOT NULL;

-- changeset shuml:1745248049434-5
ALTER TABLE curators ADD CONSTRAINT uc_curators_company UNIQUE (company_id);

-- changeset shuml:1745248049434-6
ALTER TABLE interviews_languages DROP CONSTRAINT interviews_languages_pkey;

