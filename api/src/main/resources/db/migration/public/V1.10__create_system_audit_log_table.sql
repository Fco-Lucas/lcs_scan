CREATE TABLE IF NOT EXISTS system_audit_logs (
    id BIGSERIAL PRIMARY KEY,
    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,

    -- Quem executou
    system_user_id BIGINT NOT NULL,
    CONSTRAINT fk_sal_system_user FOREIGN KEY (system_user_id)
        REFERENCES system_users(id) ON DELETE SET NULL,

    -- Qual ação foi executada (string curta)
    action VARCHAR(100) NOT NULL,

    -- Opcional: a qual tenant essa ação se refere
    tenant_id BIGINT NULL,
    CONSTRAINT fk_sal_tenant FOREIGN KEY (tenant_id)
        REFERENCES customers(id) ON DELETE SET NULL,

    -- Dados antes (JSON)
    old_data JSONB NULL,

    -- Dados depois (JSON)
    new_data JSONB NULL,

    -- Informação adicional opcional
    ip_address VARCHAR(45) NULL,
    user_agent TEXT NULL
);
