CREATE TABLE IF NOT EXISTS customer_users (
    id BIGSERIAL PRIMARY KEY,
    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    id_customers BIGINT NOT NULL,
    CONSTRAINT fk_customer_users_customers FOREIGN KEY (id_customers) REFERENCES customers(id),
    login VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    status VARCHAR(12) NOT NULL DEFAULT 'ACTIVE' CHECK (status IN ('ACTIVE', 'INACTIVE'))
);
