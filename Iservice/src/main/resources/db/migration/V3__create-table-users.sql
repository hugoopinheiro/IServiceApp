CREATE TABLE IF NOT EXISTS users (
    user_id SERIAL PRIMARY KEY,
    address_id INTEGER,
    name VARCHAR(100),
    user_name VARCHAR(100) , -- "userName" field in the Java entity
    email VARCHAR(100) UNIQUE,
    password VARCHAR(100),
    contact VARCHAR(11),
    active BOOLEAN DEFAULT TRUE,
    permissions VARCHAR(15),
    role VARCHAR(15),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_address FOREIGN KEY (address_id) REFERENCES address(address_id)
);
