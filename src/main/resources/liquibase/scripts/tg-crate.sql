CREATE TABLE notification_task (
                                   id BIGINT PRIMARY KEY NOT NULL,
                                   chat_id BIGINT NOT NULL,
                                   notification_text VARCHAR(255) NOT NULL,
                                   notification_time TIMESTAMP NOT NULL
);

CREATE INDEX idx_notification_task_id ON notification_task (id);