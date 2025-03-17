-- liquibase formatted sql

-- changeset bob:1742189255653-1
CREATE TABLE companies
(
    id          UUID     NOT NULL,
    description VARCHAR(500) NOT NULL,
    name        VARCHAR(50) NOT NULL,
    curator_id  UUID,
    CONSTRAINT companies_pkey PRIMARY KEY (id)
);

-- changeset bob:1742189255653-2
CREATE TABLE curators
(
    id         UUID NOT NULL,
    user_id    UUID NOT NULL,
    company_id UUID,
    CONSTRAINT curators_pkey PRIMARY KEY (id)
);

-- changeset bob:1742189255653-3
CREATE TABLE deans
(
    id      UUID NOT NULL,
    user_id UUID NOT NULL,
    CONSTRAINT deans_pkey PRIMARY KEY (id)
);

-- changeset bob:1742189255653-4
CREATE TABLE groups
(
    id     UUID     NOT NULL,
    number VARCHAR(15) NOT NULL,
    CONSTRAINT groups_pkey PRIMARY KEY (id)
);

-- changeset bob:1742189255653-5
CREATE TABLE interview_comments
(
    id           UUID     NOT NULL,
    content      VARCHAR(500) NOT NULL,
    author_id    UUID     NOT NULL,
    interview_id UUID     NOT NULL,
    CONSTRAINT interview_comments_pkey PRIMARY KEY (id)
);

-- changeset bob:1742189255653-6
CREATE TABLE interviews
(
    id         UUID     NOT NULL,
    status     VARCHAR(255) NOT NULL,
    company_id UUID     NOT NULL,
    stack_id   UUID     NOT NULL,
    student_id UUID     NOT NULL,
    CONSTRAINT interviews_pkey PRIMARY KEY (id)
);

-- changeset bob:1742189255653-7
CREATE TABLE practices
(
    id          UUID                    NOT NULL,
    created_at  TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    is_approved BOOLEAN                     NOT NULL,
    is_paid     BOOLEAN                     NOT NULL,
    updated_at  TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    company_id  UUID                    NOT NULL,
    student_id  UUID                    NOT NULL,
    CONSTRAINT practices_pkey PRIMARY KEY (id)
);

-- changeset bob:1742189255653-8
CREATE TABLE report_comments
(
    id        UUID     NOT NULL,
    content   VARCHAR(500) NOT NULL,
    author_id UUID     NOT NULL,
    report_id UUID     NOT NULL,
    CONSTRAINT report_comments_pkey PRIMARY KEY (id)
);

-- changeset bob:1742189255653-9
CREATE TABLE reports
(
    id          UUID                    NOT NULL,
    created_at  TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    filename    VARCHAR(50)                NOT NULL,
    is_approved BOOLEAN                     NOT NULL,
    updated_at  TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT reports_pkey PRIMARY KEY (id)
);

-- changeset bob:1742189255653-10
CREATE TABLE stacks
(
    id   UUID     NOT NULL,
    name VARCHAR(30) NOT NULL,
    CONSTRAINT stacks_pkey PRIMARY KEY (id)
);

-- changeset bob:1742189255653-11
CREATE TABLE students
(
    id       UUID NOT NULL,
    user_id  UUID NOT NULL,
    group_id UUID NOT NULL,
    CONSTRAINT students_pkey PRIMARY KEY (id)
);

-- changeset bob:1742189255653-12
CREATE TABLE teachers
(
    id      UUID NOT NULL,
    user_id UUID NOT NULL,
    CONSTRAINT teachers_pkey PRIMARY KEY (id)
);

-- changeset bob:1742189255653-13
CREATE TABLE users
(
    id         UUID     NOT NULL,
    email      VARCHAR(30) NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    last_name  VARCHAR(50) NOT NULL,
    password   VARCHAR(255) NOT NULL,
    CONSTRAINT users_pkey PRIMARY KEY (id)
);

-- changeset bob:1742189255653-14
ALTER TABLE users
    ADD CONSTRAINT uk6dotkott2kjsp8vw4d0m25fb7 UNIQUE (email);

-- changeset bob:1742189255653-15
ALTER TABLE curators
    ADD CONSTRAINT uk938v5nrtjvocl3dqw3q2a1bg0 UNIQUE (company_id);

-- changeset bob:1742189255653-16
ALTER TABLE groups
    ADD CONSTRAINT ukd9ccxrcjvo4yyg5fnqqclifoy UNIQUE (number);

-- changeset bob:1742189255653-17
ALTER TABLE stacks
    ADD CONSTRAINT ukgu2vhvtgdcisuae4dy1cw2fdt UNIQUE (name);

-- changeset bob:1742189255653-18
ALTER TABLE companies
    ADD CONSTRAINT uktipgoqn4b7sblb39a0qv92imu UNIQUE (curator_id);

-- changeset bob:1742189255653-19
ALTER TABLE report_comments
    ADD CONSTRAINT fk1i80qi03utu4wxo5vjvs4n4y6 FOREIGN KEY (report_id) REFERENCES reports (id) ON DELETE NO ACTION;

-- changeset bob:1742189255653-20
ALTER TABLE practices
    ADD CONSTRAINT fk2fof4w0p331fimv6e5iggx9h3 FOREIGN KEY (company_id) REFERENCES companies (id) ON DELETE NO ACTION;

-- changeset bob:1742189255653-21
ALTER TABLE interviews
    ADD CONSTRAINT fk3wfekf7fqr7knygjv0aa8cnsi FOREIGN KEY (student_id) REFERENCES students (id) ON DELETE NO ACTION;

-- changeset bob:1742189255653-22
ALTER TABLE curators
    ADD CONSTRAINT fk5r4j2i9ppmhd6gifx7xa61gsx FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE NO ACTION;

-- changeset bob:1742189255653-23
ALTER TABLE interviews
    ADD CONSTRAINT fk6aeg2yr4839ib2mqsix37i7jn FOREIGN KEY (stack_id) REFERENCES stacks (id) ON DELETE NO ACTION;

-- changeset bob:1742189255653-24
ALTER TABLE practices
    ADD CONSTRAINT fk7qr7d7rwte4ltr2ror9d1cfhe FOREIGN KEY (student_id) REFERENCES students (id) ON DELETE NO ACTION;

-- changeset bob:1742189255653-25
ALTER TABLE curators
    ADD CONSTRAINT fk9oy2crj87bs5wv0t5l2cce0vm FOREIGN KEY (company_id) REFERENCES companies (id) ON DELETE NO ACTION;

-- changeset bob:1742189255653-26
ALTER TABLE deans
    ADD CONSTRAINT fkaufg775upxkg71xhysyqje2nh FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE NO ACTION;

-- changeset bob:1742189255653-27
ALTER TABLE teachers
    ADD CONSTRAINT fkb8dct7w2j1vl1r2bpstw5isc0 FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE NO ACTION;

-- changeset bob:1742189255653-28
ALTER TABLE students
    ADD CONSTRAINT fkdt1cjx5ve5bdabmuuf3ibrwaq FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE NO ACTION;

-- changeset bob:1742189255653-29
ALTER TABLE interview_comments
    ADD CONSTRAINT fkdyov4q70ks8jtengfjrr90qt2 FOREIGN KEY (author_id) REFERENCES users (id) ON DELETE NO ACTION;

-- changeset bob:1742189255653-30
ALTER TABLE report_comments
    ADD CONSTRAINT fki8p0d6l24jm0hokvim8wspptq FOREIGN KEY (author_id) REFERENCES users (id) ON DELETE NO ACTION;

-- changeset bob:1742189255653-31
ALTER TABLE interview_comments
    ADD CONSTRAINT fkjjuhu54tlgsj8q78na5pgqf9s FOREIGN KEY (interview_id) REFERENCES interviews (id) ON DELETE NO ACTION;

-- changeset bob:1742189255653-32
ALTER TABLE interviews
    ADD CONSTRAINT fkmb5mr7s42c7vfk0ophe1eio20 FOREIGN KEY (company_id) REFERENCES companies (id) ON DELETE NO ACTION;

-- changeset bob:1742189255653-33
ALTER TABLE students
    ADD CONSTRAINT fkmsev1nou0j86spuk5jrv19mss FOREIGN KEY (group_id) REFERENCES groups (id) ON DELETE NO ACTION;

-- changeset bob:1742189255653-34
ALTER TABLE companies
    ADD CONSTRAINT fkt3cic12vih2jftco3mv5cgwx9 FOREIGN KEY (curator_id) REFERENCES curators (id) ON DELETE NO ACTION;

