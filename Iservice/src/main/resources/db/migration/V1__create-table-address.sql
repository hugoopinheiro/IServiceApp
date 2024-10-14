CREATE TABLE address (
    address_id SERIAL PRIMARY KEY NOT NULL UNIQUE,
    cep varchar(255) NOT NULL,
    complement varchar(255) NOT NULL,
    house_number varchar(255) NOT NULL,
    neighborhood varchar(255) NOT NULL,
    street varchar(255) NOT NULL,
    state varchar(255) NOT NULL
);
