CREATE TABLE pet (
pet_id SERIAL PRIMARY KEY,
name VARCHAR(50) NOT NULL,
type VARCHAR(50) NULL
);

INSERT INTO pet (name, type) VALUES
('Meep', 'Mouse'),
('Slithpers', 'Snake'),
('Noodles', 'Dog');