-- liquibase formatted sql

-- changeset shuml:1746699201698-1
-- ALTER TABLE report_comments DROP CONSTRAINT fk1i80qi03utu4wxo5vjvs4n4y6;

-- changeset shuml:1746699201698-2
-- ALTER TABLE interview_comments DROP CONSTRAINT fkdyov4q70ks8jtengfjrr90qt2;

-- changeset shuml:1746699201698-3
-- ALTER TABLE report_comments DROP CONSTRAINT fki8p0d6l24jm0hokvim8wspptq;

-- changeset shuml:1746699201698-4
-- ALTER TABLE interview_comments DROP CONSTRAINT fkjjuhu54tlgsj8q78na5pgqf9s;

-- changeset shuml:1746699201698-5
DROP TABLE IF EXISTS interview_comments CASCADE;
DROP TABLE IF EXISTS report_comments    CASCADE;

