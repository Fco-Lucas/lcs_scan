CREATE TABLE IF NOT EXISTS system_user_permissions (
    id BIGSERIAL PRIMARY KEY,
    system_user_id BIGINT NOT NULL,
    permission_id BIGINT NOT NULL,
    granted BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_sup_user FOREIGN KEY (system_user_id) REFERENCES system_users(id) ON DELETE CASCADE,
    CONSTRAINT fk_sup_perm FOREIGN KEY (permission_id) REFERENCES system_permissions(id) ON DELETE CASCADE,
    CONSTRAINT uq_system_user_permission UNIQUE (system_user_id, permission_id)
);
