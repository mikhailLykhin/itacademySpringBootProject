CREATE TABLE `roles`
(
    `id`       INT         NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name`     VARCHAR(45) NULL
);
CREATE TABLE `users_roles`
(
    `user_id`  INT NOT NULL,
    `role_id`  INT NOT NULL,
    PRIMARY KEY (user_id, role_id),
        CONSTRAINT FK_user FOREIGN KEY (user_id)
        REFERENCES users (id) ON DELETE CASCADE ,
    CONSTRAINT FK_role FOREIGN KEY (role_id)
        REFERENCES roles (id) ON DELETE CASCADE
);