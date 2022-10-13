CREATE TABLE IF NOT EXISTS products (
    identifier INTEGER GENERATED BY DEFAULT AS IDENTITY,
    name VARCHAR(128) NOT NULL,
    price INTEGER,

    PRIMARY KEY (identifier)
);

-- CREATE TABLE test.products
-- (
--     identifier INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
--     name VARCHAR(30) NOT NULL ,
--     price INTEGER
-- );