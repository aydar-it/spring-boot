CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    name TEXT,
    age INT4
);

CREATE TABLE products (
    id BIGSERIAL PRIMARY KEY,
    title TEXT,
    price INT4
);