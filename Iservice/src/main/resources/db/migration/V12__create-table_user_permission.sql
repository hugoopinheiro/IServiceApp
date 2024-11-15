CREATE TABLE IF NOT EXISTS user_permission (
    user_id BIGINT NOT NULL,
    id_permission BIGINT NOT NULL,
    PRIMARY KEY (user_id, id_permission),
    CONSTRAINT fk_permission FOREIGN KEY (id_permission) REFERENCES permission(id),
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(user_id)
);
