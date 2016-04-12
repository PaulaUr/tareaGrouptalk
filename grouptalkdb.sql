drop database if exists grouptalkdb;
create database grouptalkdb;

use grouptalkdb;

CREATE TABLE users (
    id BINARY(16) NOT NULL,
    loginid VARCHAR(15) NOT NULL UNIQUE,
    password BINARY(16) NOT NULL,
    email VARCHAR(255) NOT NULL,
    fullname VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);
CREATE TABLE groups (
    id BINARY(16) NOT NULL,
    name VARCHAR(15) NOT NULL,
    PRIMARY KEY (id)
);
CREATE TABLE theme (
    id BINARY(16) NOT NULL,
    userid BINARY(16) NOT NULL,
    groupid BINARY(16) NOT NULL,
    titulo VARCHAR(255) NOT NULL,
    coments VARCHAR(500) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (userid) REFERENCES users(id) on delete cascade,
    FOREIGN KEY (groupid) REFERENCES groups(id) on delete cascade
);
CREATE TABLE respuesta(
    id BINARY(16) NOT NULL,
    themeid BINARY(16) NOT NULL,
    userid BINARY(16) NOT NULL,
    contenido VARCHAR(500) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (userid) REFERENCES users(id) on delete cascade,
    FOREIGN KEY (themeid) REFERENCES theme(id) on delete cascade
);

CREATE TABLE relaciones_grupo (
    grupoid BINARY(16) NOT NULL,
    userid BINARY(16) NOT NULL,
    PRIMARY KEY (grupoid, userid),
    FOREIGN KEY (userid) REFERENCES users(id) on delete cascade,
    FOREIGN KEY (grupoid) REFERENCES groups(id) on delete cascade
);
CREATE TABLE user_roles (
    userid BINARY(16) NOT NULL,
    role ENUM ('registrado','administrador'),
    PRIMARY KEY (userid, role),
    FOREIGN KEY (userid) REFERENCES users(id) on delete cascade
);

CREATE TABLE auth_tokens (
    userid BINARY(16) NOT NULL,
    token BINARY(16) NOT NULL,
    PRIMARY KEY (token),
    FOREIGN KEY (userid) REFERENCES users(id) on delete cascade
);
