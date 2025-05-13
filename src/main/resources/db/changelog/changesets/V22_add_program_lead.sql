-- liquibase formatted sql

-- changeset iva.semenov:1747161083027-1
CREATE TABLE educational_program_leads (id UUID NOT NULL, user_id UUID NOT NULL, CONSTRAINT pk_educational_program_leads PRIMARY KEY (id));

-- changeset iva.semenov:1747161083027-2
ALTER TABLE educational_program_leads ADD CONSTRAINT FK_EDUCATIONAL_PROGRAM_LEADS_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);

