CREATE TABLE IF NOT EXISTS system_user_roles (
    system_user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    PRIMARY KEY (system_user_id, role_id),
    CONSTRAINT fk_sur_user FOREIGN KEY (system_user_id) REFERENCES system_users(id) ON DELETE CASCADE,
    CONSTRAINT fk_sur_role FOREIGN KEY (role_id) REFERENCES system_roles(id) ON DELETE CASCADE
);
