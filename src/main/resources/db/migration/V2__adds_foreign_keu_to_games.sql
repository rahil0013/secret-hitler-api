ALTER TABLE admins drop column role;

ALTER TABLE admins add column role_id bigint(20);

ALTER TABLE admins ADD CONSTRAINT fk_role_id FOREIGN KEY (role_id) REFERENCES roles(id);