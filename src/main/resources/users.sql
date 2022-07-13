CREATE SCHEMA users;

CREATE TABLE users.users(
    id SERIAL PRIMARY KEY,
    name VARCHAR(40),
    age INTEGER,
    group_Id INTEGER NOT NULL,
    login VARCHAR(50),
    password VARCHAR(50)
);

INSERT INTO users.users(name, age, group_Id, login, password) VALUES ('Steven', 35, 1, 'steven', 'steven');
INSERT INTO users.users(name, age, group_Id, login, password) VALUES ('Neena', 25, 1, 'neena', 'neena');
INSERT INTO users.users(name, age, group_Id, login, password) VALUES ('Lex', 30, 1, 'lex', 'lex');