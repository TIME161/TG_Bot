CREATE TABLE notification_task (
                                   chat_id BIGINT PRIMARY KEY NOT NULL,
                                   notification_text VARCHAR(255) NOT NULL,
                                   notification_time TIMESTAMP NOT NULL
);

CREATE INDEX idx_notification_task_chat_id ON notification_task (chat_id);