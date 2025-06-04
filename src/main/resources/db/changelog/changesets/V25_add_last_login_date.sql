-- liquibase formatted sql

-- changeset iva.semenov:1748718523988-2
ALTER TABLE users ADD last_login_date TIMESTAMP WITHOUT TIME ZONE;

