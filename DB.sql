BEGIN;

DROP TABLE IF EXISTS product CASCADE;
CREATE TABLE product (id bigserial PRIMARY KEY, title VARCHAR(255), cost int);
INSERT INTO product (title, cost) VALUES
('box', 10),
('sphere', 20),
('maul', 100),
('door', 50),
('tools', 250),
('laptop', 422),
('table', 42),
('mouse', 35),
('keyboard', 66);


COMMIT;