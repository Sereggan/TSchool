DROP TABLE IF EXISTS authorities;

CREATE TABLE authorities
(
    id   bigint NOT NULL,
    role varchar(255) DEFAULT NULL,
    PRIMARY KEY (id)
) 
 ;


LOCK TABLES authorities WRITE;
INSERT INTO authorities
VALUES (1, 'ROLE_EMPLOYEE'),
       (2, 'ROLE_CLIENT');
UNLOCK TABLES;

DROP TABLE IF EXISTS users;

CREATE TABLE users
(
    id               bigint NOT NULL,
    user_city        varchar(255) DEFAULT NULL,
    user_country     varchar(255) DEFAULT NULL,
    user_flat        int          DEFAULT NULL,
    user_house       varchar(255) DEFAULT NULL,
    user_postal_code int          DEFAULT NULL,
    user_street      varchar(255) DEFAULT NULL,
    user_birthday    date         DEFAULT NULL,
    user_email       varchar(255) DEFAULT NULL,
    user_last_name   varchar(255) DEFAULT NULL,
    user_password    varchar(255) DEFAULT NULL,
    user_name        varchar(255) DEFAULT NULL,
    PRIMARY KEY (id),
    CONSTRAINT UK_33uo7vet9c79ydfuwg1w848f UNIQUE  (user_email),
    CONSTRAINT UK_k8d0f2n7n88w1a16yhua64onx UNIQUE  (user_name)
) 
 ;


LOCK TABLES users WRITE;
INSERT INTO users
VALUES (1, '1', '1', 1, '1', 1, '1', '1999-04-30', 'user@user.ru', 'Nikolaychuk',
        '$2a$10$LOqePml/koRGsk2YAIOFI.1YNKZg7EsQ5BAIuYP1nWOyYRl21dlne', 'Sergey'),
       (2, '1', '1', 1, '1', 1, '1', '1999-04-30', 'admin@admin.ru', 'Unschikov',
        '$2a$10$LOqePml/koRGsk2YAIOFI.1YNKZg7EsQ5BAIuYP1nWOyYRl21dlne', 'Kirill');
UNLOCK TABLES;

DROP TABLE IF EXISTS user_authority;

CREATE TABLE user_authority
(
    user_id      bigint NOT NULL,
    authority_id bigint NOT NULL,
    PRIMARY KEY (user_id, authority_id)
   ,
    CONSTRAINT FKhi46vu7680y1hwvmnnuh4cybx FOREIGN KEY (user_id) REFERENCES users (id),
    CONSTRAINT FKil6f39w6fgqh4gk855pstsnmy FOREIGN KEY (authority_id) REFERENCES authorities (id)
) 
 ;

CREATE INDEX FKil6f39w6fgqh4gk855pstsnmy ON user_authority (authority_id);

LOCK TABLES user_authority WRITE;
INSERT INTO user_authority
VALUES (1, 1),
       (2, 2);
UNLOCK TABLES;


