CREATE TABLE IF NOT EXISTS users (
    user_id SERIAL PRIMARY KEY,
    address_id INTEGER,
    name VARCHAR(100) NOT NULL,
    user_name VARCHAR(100) NOT NULL, -- "userName" field in the Java entity
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    contact VARCHAR(11) NOT NULL,
    active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_address FOREIGN KEY (address_id) REFERENCES address(address_id)
);
