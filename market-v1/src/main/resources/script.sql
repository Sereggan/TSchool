
DROP TABLE IF EXISTS `authority`;
CREATE TABLE `authority`
(
    `id`   bigint NOT NULL,
    `role` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

LOCK TABLES `authority` WRITE;

INSERT INTO `authority`
VALUES (1, 'ROLE_CLIENT'),
       (2, 'ROLE_EMPLOYEE');
UNLOCK TABLES;

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`
(
    `id`            bigint       NOT NULL,
    `user_birthday` date DEFAULT NULL,
    `user_email`    varchar(255) NOT NULL,
    `user_lastName` varchar(255) NOT NULL,
    `user_name`     varchar(255) NOT NULL,
    `user_password` varchar(255) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


LOCK TABLES `users` WRITE;
INSERT INTO `users`
VALUES (1, '1999-04-30', 'user@user.ru', 'Nikolaychuk', 'Sergey',
        '$2a$10$LOqePml/koRGsk2YAIOFI.1YNKZg7EsQ5BAIuYP1nWOyYRl21dlne'),
       (2, '1999-03-30', 'admin@admin.ru', 'Unchikov', 'Kirill',
        '$2a$10$LOqePml/koRGsk2YAIOFI.1YNKZg7EsQ5BAIuYP1nWOyYRl21dlne');

UNLOCK TABLES;


DROP TABLE IF EXISTS `user_authority`;
CREATE TABLE `user_authority`
(
    `user_id`      bigint NOT NULL,
    `authority_id` bigint NOT NULL,
    PRIMARY KEY (`user_id`, `authority_id`),
    KEY `FKgvxjs381k6f48d5d2yi11uh89` (`authority_id`),
    CONSTRAINT `FKgvxjs381k6f48d5d2yi11uh89` FOREIGN KEY (`authority_id`) REFERENCES `authority` (`id`),
    CONSTRAINT `FKhi46vu7680y1hwvmnnuh4cybx` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

LOCK TABLES `user_authority` WRITE;

INSERT INTO `user_authority`
VALUES (1, 1),
       (2, 2);

UNLOCK TABLES;
