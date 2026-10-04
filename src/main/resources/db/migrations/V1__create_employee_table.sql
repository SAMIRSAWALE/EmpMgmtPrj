CREATE TABLE employees (
                          id BIGSERIAL PRIMARY KEY ,
                          first_name VARCHAR(100) NOT NULL,
                          last_name VARCHAR(100) NOT NULL,
                          email VARCHAR(150) NOT NULL ,
                          phone VARCHAR(20),
                          salary NUMERIC(12,2),
                          password VARCHAR(255)
);