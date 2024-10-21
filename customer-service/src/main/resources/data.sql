DROP TABLE IF EXISTS customer;
DROP TABLE IF EXISTS order_history;

CREATE TABLE customer (
                          id int AUTO_INCREMENT primary key,
                          name VARCHAR(50),
                          balance int,
                          shipping_address VARCHAR(200)
);

CREATE TABLE order_history (
                               id int AUTO_INCREMENT primary key,
                               customer_id int,
                               product_code VARCHAR(20),
                               product_category VARCHAR(20),
                               quantity int,
                               price int,
                               order_status VARCHAR(20),
                               order_date TIMESTAMP,
                               foreign key (customer_id) references customer(id)
);

insert into customer(name, balance, shipping_address)
values
    ('Alice', 5000, '123 Main St'),
    ('Bob', 3000, '456 Oak Ave'),
    ('Charlie', 2000, '789 Pine Rd');