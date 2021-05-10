CREATE TABLE `users`
(
    `id`       INT         NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `password` VARCHAR(255) NOT NULL,
    `salary`   int NULL,
    `name`     VARCHAR(45) NULL
);
CREATE TABLE `pets`
(
    `id`       INT         NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `nickname` VARCHAR(45) NOT NULL,
    `user_id`  int NULL,
    foreign key (user_id) references users(id)
);