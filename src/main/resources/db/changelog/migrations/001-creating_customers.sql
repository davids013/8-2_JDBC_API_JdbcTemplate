CREATE TABLE customers
(
    id           INT PRIMARY KEY AUTO_INCREMENT,
    name         VARCHAR(32) NOT NULL,
    surname      VARCHAR(32),
    age          INT         NOT NULL CHECK ( age > 0 AND age < 110 ),
    phone_number VARCHAR(32) UNIQUE
);