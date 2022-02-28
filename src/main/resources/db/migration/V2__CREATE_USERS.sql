CREATE TABLE user
(
    id            BIGINT      NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name          VARCHAR(15) NOT NULL,
    username      VARCHAR(50) NOT NULL UNIQUE,
    password_hash VARCHAR(100) NOT NULL
);

CREATE TABLE sequence_user
(
    next_val BIGINT
);

INSERT INTO sequence_user
VALUES (1);

CREATE SEQUENCE sequence_user;

CREATE TABLE role
(
    id   BIGINT      NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(15) NOT NULL UNIQUE
);

CREATE TABLE sequence_role
(
    next_val BIGINT
);

INSERT INTO sequence_role
VALUES (1);

CREATE SEQUENCE sequence_role;

CREATE TABLE users_roles
(
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, role_id)
);



