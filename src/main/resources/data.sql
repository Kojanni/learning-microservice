DROP TABLE IF EXISTS users;

CREATE TABLE users
(
    id         INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(250) NOT NULL,
    middle_name VARCHAR(250) NOT NULL,
    last_name  VARCHAR(250) NOT NULL,
    age   INT DEFAULT NULL
);

INSERT INTO users (first_name, middle_name, last_name, age)
VALUES ('Ivan', 'Vladimirovich', 'Pushka', 35),
       ('Julia', 'Egorovna', 'Belaiya', 22),
       ('Svetlana', 'Olegovna', 'Cho', 37);
