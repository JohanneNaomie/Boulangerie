-- Créer la base de données
CREATE DATABASE bakery;

-- Se connecter à la base de données
\c bakery;

-- Création des tables
CREATE TABLE bakery_units (
    id_unit SERIAL PRIMARY KEY,
    name_unit VARCHAR(50) NOT NULL
);

CREATE TABLE bakery_ingredients (
    id_ingredient SERIAL PRIMARY KEY,
    name_ingredient VARCHAR(100) NOT NULL,
    id_unit INT REFERENCES bakery_units(id_unit),
    stock DECIMAL(10, 2) NOT NULL,
    last_update TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE bakery_ingredient_prices (
    id_price SERIAL PRIMARY KEY,
    id_ingredient INT REFERENCES bakery_ingredients(id_ingredient),
    date_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    price DECIMAL(12, 3) NOT NULL
);

CREATE TABLE bakery_products (
    id_product SERIAL PRIMARY KEY,
    name_product VARCHAR(100) NOT NULL,
    conservation_time_h DECIMAL(10, 2)
);

CREATE TABLE bakery_product_prices (
    id_price SERIAL PRIMARY KEY,
    id_product INT REFERENCES bakery_products(id_product),
    price DECIMAL(12, 3) NOT NULL,
    date_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE bakery_composition_products (
    id_composition SERIAL PRIMARY KEY,
    id_product INT REFERENCES bakery_products(id_product),
    id_ingredient INT REFERENCES bakery_ingredients(id_ingredient),
    quantity DECIMAL(10, 2) NOT NULL
);

CREATE TABLE bakery_productions (
    id_production SERIAL PRIMARY KEY,
    id_product INT REFERENCES bakery_products(id_product),
    quantity DECIMAL(10, 2) NOT NULL,
    date_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE bakery_categories (
    id_category SERIAL PRIMARY KEY,
    name_category VARCHAR(100) NOT NULL
);

CREATE TABLE bakery_parfums (
    id_parfum SERIAL PRIMARY KEY,
    name_parfum VARCHAR(100) NOT NULL
);

CREATE TABLE bakery_product_categories (
    id_pc SERIAL PRIMARY KEY,
    id_product INT REFERENCES bakery_products(id_product),
    id_category INT REFERENCES bakery_categories(id_category)
);

CREATE TABLE bakery_product_parfums (
    id_pp SERIAL PRIMARY KEY,
    id_product INT REFERENCES bakery_products(id_product),
    id_parfum INT REFERENCES bakery_parfums(id_parfum),
    quantity DECIMAL(10, 2) NOT NULL 
);

CREATE TABLE bakery_month (
    id_month int not null PRIMARY KEY,
    month VARCHAR(20)
);
CREATE TABLE bakery_recommended (
    id_recommended SERIAL PRIMARY KEY,
    id_product INT REFERENCES bakery_products ,
    id_month INT NOT NULL REFERENCES bakery_month,
    annee INT NOT NULL 
);

CREATE TABLE bakery_clients(
    id_client SERIAL PRIMARY KEY ,
    name VARCHAR(50)
);

CREATE TABLE bakery_sexe(
    id_sexe SERIAL PRIMARY KEY,
    sexe VARCHAR(20)
);
-- Création de la table seller
CREATE  TABLE bakery_sellers (
    id_seller SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    id_sexe INT REFERENCES bakery_sexe(id_sexe)
);


CREATE TABLE bakery_sales (
    id_sale SERIAL PRIMARY KEY,
    id_client INT NOT NULL REFERENCES bakery_clients(id_client) DEFAULT 1,
    id_seller INT NOT NULL REFERENCES bakery_sellers(id_seller),
    id_pp INT REFERENCES bakery_product_parfums(id_pp),
    quantity DECIMAL(10, 2) NOT NULL,
    total_price DECIMAL(12, 3) NOT NULL,
    date_sales TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);






-- -- A faire
-- -- Move Stock Table
-- CREATE TABLE bakery_move_stocks (
--     id_move SERIAL PRIMARY KEY,
--     id_ingredient INT REFERENCES bakery_ingredients(id_ingredient),
--     date_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP, 
--     quantity INT NOT NULL
-- );
