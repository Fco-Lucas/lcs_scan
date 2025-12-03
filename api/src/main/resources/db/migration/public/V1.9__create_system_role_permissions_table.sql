CREATE TABLE IF NOT EXISTS system_role_permissions (
    role_id BIGINT NOT NULL,
    permission_id BIGINT NOT NULL,
    PRIMARY KEY (role_id, permission_id),
    CONSTRAINT fk_srp_role FOREIGN KEY (role_id) REFERENCES system_roles(id) ON DELETE CASCADE,
    CONSTRAINT fk_srp_perm FOREIGN KEY (permission_id) REFERENCES system_permissions(id) ON DELETE CASCADE
);
