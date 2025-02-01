CREATE TABLE address (
    address_id SERIAL PRIMARY KEY,
    street VARCHAR(200) ,
    cep VARCHAR(10) ,
    complement VARCHAR(200),
    state VARCHAR(50) ,
    house_number VARCHAR(10)
);