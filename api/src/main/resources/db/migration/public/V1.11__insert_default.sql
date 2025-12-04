-- Usuário main (ID 1)
INSERT INTO system_users
(name, email, password)
VALUES
('Lucas', 'fcolucasmaia@gmail.com', 'Comp@123');

-- Cargo main (ID 1)
INSERT INTO system_roles (name, description) VALUES ('Super administrador', 'Cargo máximo, contém todas as permissões');

-- Permissões default
INSERT INTO system_permissions (name, description) VALUES ('criar.lotes', 'Permissão para criar lotes'); -- (ID 1)
INSERT INTO system_permissions (name, description) VALUES ('visualizar.lotes', 'Permissão para visualizar os lotes criados'); -- (ID 2)
INSERT INTO system_permissions (name, description) VALUES ('editar.lotes', 'Permissão para atualizar as informações dos lotes criados'); -- (ID 3)
INSERT INTO system_permissions (name, description) VALUES ('excluir.lotes', 'Permissão para excluir os lotes criados'); -- (ID 4)
INSERT INTO system_permissions (name, description) VALUES ('processar.gabaritos', 'Permissão para processar gabaritos'); -- (ID 5)
INSERT INTO system_permissions (name, description) VALUES ('visualizar.gabaritos', 'Permissão para visualizar os gabaritos processados'); -- (ID 6)
INSERT INTO system_permissions (name, description) VALUES ('excluir.gabaritos', 'Permissão para excluir os gabaritos processados'); -- (ID 7)

-- Relação do usuário main com o cargo main
INSERT INTO system_user_roles (system_user_id, role_id) VALUES (1, 1);

-- Relação do cargo main com todas as permissões default
INSERT INTO system_role_permissions (role_id, permission_id) VALUES (1, 1);
INSERT INTO system_role_permissions (role_id, permission_id) VALUES (1, 2);
INSERT INTO system_role_permissions (role_id, permission_id) VALUES (1, 3);
INSERT INTO system_role_permissions (role_id, permission_id) VALUES (1, 4);
INSERT INTO system_role_permissions (role_id, permission_id) VALUES (1, 5);
INSERT INTO system_role_permissions (role_id, permission_id) VALUES (1, 6);
INSERT INTO system_role_permissions (role_id, permission_id) VALUES (1, 7);

