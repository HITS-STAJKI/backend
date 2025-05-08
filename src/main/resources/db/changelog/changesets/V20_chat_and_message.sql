-- liquibase formatted sql

-- changeset shuml:1746698032391-1
CREATE TABLE chats (id UUID NOT NULL, student_id UUID NOT NULL, CONSTRAINT pk_chats PRIMARY KEY (id));

-- changeset shuml:1746698032391-2
CREATE TABLE messages (id UUID NOT NULL, content VARCHAR(255) NOT NULL, created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL, modified_at TIMESTAMP WITHOUT TIME ZONE, chat_id UUID NOT NULL, sender_id UUID NOT NULL, is_read BOOLEAN NOT NULL, CONSTRAINT pk_messages PRIMARY KEY (id));

-- changeset shuml:1746698032391-3
ALTER TABLE chats ADD CONSTRAINT uc_chats_student UNIQUE (student_id);

-- changeset shuml:1746698032391-5
CREATE INDEX idx_message_chat_id_is_read ON messages(chat_id, is_read);

-- changeset shuml:1746698032391-7
ALTER TABLE chats ADD CONSTRAINT FK_CHATS_ON_STUDENT FOREIGN KEY (student_id) REFERENCES students (id);
CREATE INDEX idx_chat_student_id ON chats(student_id);

-- changeset shuml:1746698032391-8
ALTER TABLE messages ADD CONSTRAINT FK_MESSAGES_ON_CHAT FOREIGN KEY (chat_id) REFERENCES chats (id);

-- changeset shuml:1746698032391-9
ALTER TABLE messages ADD CONSTRAINT FK_MESSAGES_ON_SENDER FOREIGN KEY (sender_id) REFERENCES users (id);
CREATE INDEX idx_message_sender_id ON messages(sender_id);

