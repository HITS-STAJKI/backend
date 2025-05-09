-- liquibase formatted sql

-- changeset shuml:1746796917562-1
CREATE TABLE chat_read_states (id UUID NOT NULL, chat_id UUID NOT NULL, user_id UUID NOT NULL, last_read_at TIMESTAMP WITHOUT TIME ZONE NOT NULL, CONSTRAINT pk_chat_read_states PRIMARY KEY (id));

-- changeset shuml:1746796917562-2
ALTER TABLE chat_read_states ADD CONSTRAINT uc_eb718da33c6c0a64d96483332 UNIQUE (user_id, chat_id);

-- changeset shuml:1746796917562-3
CREATE INDEX idx_message_chat_created_sender ON messages(chat_id, created_at, sender_id);

-- changeset shuml:1746796917562-4
ALTER TABLE chat_read_states ADD CONSTRAINT FK_CHAT_READ_STATES_ON_CHAT FOREIGN KEY (chat_id) REFERENCES chats (id);

-- changeset shuml:1746796917562-5
ALTER TABLE chat_read_states ADD CONSTRAINT FK_CHAT_READ_STATES_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);

-- changeset shuml:1746796917562-9
ALTER TABLE messages DROP COLUMN is_read;

