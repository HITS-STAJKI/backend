CREATE TABLE files
(
    id         UUID                        NOT NULL,
    name       VARCHAR(30)                 NOT NULL,
    size       BIGINT                      NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT files_pkey PRIMARY KEY (id)
);