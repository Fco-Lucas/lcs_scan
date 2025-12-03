CREATE TABLE IF NOT EXISTS system_audit_logs (
    id BIGSERIAL PRIMARY KEY,
    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    system_user_id BIGINT NOT NULL, CONSTRAINT fk_sal_system_user FOREIGN KEY (system_user_id) REFERENCES system_users(id) ON DELETE SET NULL,
    action VARCHAR(100) NOT NULL,
    old_data JSONB NULL,
    new_data JSONB NULL,
    ip_address VARCHAR(45) NULL,
    user_agent TEXT NULL
);
