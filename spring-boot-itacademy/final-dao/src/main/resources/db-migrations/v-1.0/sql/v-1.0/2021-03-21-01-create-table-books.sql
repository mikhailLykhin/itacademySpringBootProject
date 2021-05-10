CREATE TABLE `books`
(
    `id`       INT         NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `isbn` VARCHAR(45) NULL,
    `name` VARCHAR(45)NULL,
    `author` VARCHAR(45) NULL,
    `picture` VARCHAR(100) NULL,
    `description` VARCHAR(10000) NULL
);