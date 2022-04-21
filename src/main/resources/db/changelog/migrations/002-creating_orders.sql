CREATE TABLE orders
(
    id           INT PRIMARY KEY AUTO_INCREMENT,
    date         TIMESTAMP   NOT NULL DEFAULT NOW(),
    customer_id  INT,
    product_name VARCHAR(64) NOT NULL,
    amount       INT         NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES customers (id)
);