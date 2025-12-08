CREATE TABLE IF NOT EXISTS device
(
    id        BIGSERIAL PRIMARY KEY,
    name      VARCHAR(255) NOT NULL ,
    type      VARCHAR(50) NOT NULL,
    location  VARCHAR(255) ,
    user_id   BIGINT NOT NULL,

    CONSTRAINT fk_device_user
        FOREIGN KEY (user_id)
            REFERENCES users (id)
            ON DELETE CASCADE
);

CREATE INDEX IF NOT EXISTS idx_device_user_id
    ON device (user_id);
