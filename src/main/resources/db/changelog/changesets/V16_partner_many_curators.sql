ALTER TABLE companies DROP COLUMN IF EXISTS curator_id;
ALTER TABLE curators DROP CONSTRAINT uc_curators_company;