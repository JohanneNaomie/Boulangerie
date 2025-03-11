
-- Insertion des donnees

-- Unites
INSERT INTO bakery_units (name_unit) VALUES 
('kilogramme'), ('litre'), ('piece'), ('gramme'), ('millilitre'), ('boite');

-- Ingredients
INSERT INTO bakery_ingredients (name_ingredient, id_unit, stock) VALUES 
('Farine', 1, 100.00), ('Sucre', 1, 50.00), ('Lait', 2, 30.00), 
('Beurre', 1, 20.00), ('Oeuf', 3, 200.00), ('Chocolat noir', 1, 20.00), 
('Levure', 1, 10.00), ('Sel', 4, 5.00), ('Creme', 2, 15.00), 
('Fruits rouges', 4, 8.00);

-- Prix des ingredients
INSERT INTO bakery_ingredient_prices (id_ingredient, price) VALUES 
(1, 1.50), (2, 0.90), (3, 0.75), (4, 2.50), (5, 0.30), 
(6, 5.00), (7, 0.50), (8, 0.10), (9, 1.50), (10, 4.00);

-- Produits
INSERT INTO bakery_products (name_product, conservation_time_h) VALUES 
('Pain', 24.00), ('Croissant', 12.00), ('Gateau', 48.00), 
('Tarte aux fruits rouges', 24.00), ('Eclair au chocolat', 12.00), 
('Pain complet', 36.00), ('Macarons assortis', 48.00), 
('Baguette tradition', 24.00);

-- Prix des produits
INSERT INTO bakery_product_prices (id_product, price) VALUES 
(1, 3.50), (2, 2.50), (3, 15.00), (4, 12.50), (5, 3.00), 
(6, 2.00), (7, 15.00), (8, 1.20);

-- Composition des produits
INSERT INTO bakery_composition_products (id_product, id_ingredient, quantity) VALUES 
(1, 1, 1.00), (1, 2, 0.10), (2, 1, 0.50), (2, 4, 0.20), 
(3, 5, 5.00), (4, 10, 0.50), (4, 9, 0.30), 
(5, 6, 0.20), (5, 1, 0.50), (6, 1, 1.00), 
(6, 7, 0.01), (7, 5, 10.00), (7, 2, 0.30), 
(8, 1, 0.25), (8, 7, 0.005);

-- Categories
INSERT INTO bakery_categories (name_category) VALUES 
('Viennoiseries'), ('Patisseries'), ('Pain'), 
('Tartes'), ('Pains speciaux'), ('Petits gateaux');

-- Produits et categories
INSERT INTO bakery_product_categories (id_product, id_category) VALUES 
(1, 3), (2, 1), (3, 2), (4, 4), (5, 6), 
(6, 5), (7, 6), (8, 5);

-- Parfums
INSERT INTO bakery_parfums (name_parfum) VALUES 
('Vanille'), ('Chocolat'), ('Fraise'), 
('Caramel'), ('Noisette'), ('Pistache'), 
('Citron'), ('Cassis');

-- Produits et parfums
INSERT INTO bakery_product_parfums (id_product, id_parfum, quantity) VALUES 
(3, 1, 0.10), (3, 2, 0.20), (3, 3, 0.15), 
(7, 6, 0.15), (7, 7, 0.10), (7, 8, 0.20), 
(5, 5, 0.05), (4, 8, 0.25);

-- Productions
INSERT INTO bakery_productions (id_product, quantity) VALUES 
(1, 50.00), (2, 30.00), (3, 10.00), 
(4, 15.00), (5, 50.00), (6, 40.00), 
(7, 25.00), (8, 100.00);

INSERT INTO bakery_month 
VALUES
    (1, 'Janvier'),
    (2, 'Février'),
    (3, 'Mars'),
    (4, 'Avril'),
    (5, 'Mai'),
    (6, 'Juin'),
    (7, 'Juillet'),
    (8, 'Août'),
    (9, 'Septembre'),
    (10, 'Octobre'),
    (11, 'Novembre'),
    (12, 'Décembre');

-- Insérer des données dans la table bakery_clients
INSERT INTO bakery_clients (id_client, name)
VALUES 
    (1, 'autre'),
    (2, 'Jean Dupont'),
    (3, 'Marie Curie'),
    (4, 'Paul Durand'),
    (5, 'Emma Martin'),
    (6, 'Lucas Morel'),
    (7, 'Chloé Bernard'),
    (8, 'Thomas Lefèvre');

INSERT INTO bakery_sexe (sexe) 
VALUES 
    ('male'),
    ('female');
    

-- Insertion des données dans la table seller
INSERT INTO bakery_sellers (name,id_sexe)
VALUES
    ('Alice Dupont',2),
    ('Jean Moreau',1),
    ('Pauline Martin',2),
    ('Lucas Bernard',1);


-- Insertion des données dans la table bakery_sales
INSERT INTO bakery_sales (id_client, id_pp, quantity, total_price, date_sales, id_seller)
VALUES
    -- Ventes pour le client 'autre' (id_client = 1)
    (1, 1, 3.00, 15.750, '2025-01-05', 1),
    (1, 2, 2.50, 12.375, '2024-12-25', 1),

    -- Ventes pour Jean Dupont (id_client = 2)
    (2, 3, 3.00, 18.750, '2024-12-15', 2),
    (2, 5, 2.00, 13.500, '2025-01-01', 2),

    -- Ventes pour Marie Curie (id_client = 3)
    (3, 1, 5.00, 26.500, '2024-12-22', 3),
    (3, 2, 2.00, 10.750, '2024-12-28', 3),

    -- Ventes pour Paul Durand (id_client = 4)
    (4, 5, 4.00, 27.000, '2024-12-19', 4),
    (4, 6, 3.00, 21.750, '2025-01-03', 4),

    -- Ventes pour Emma Martin (id_client = 5)
    (5, 1, 3.00, 15.000, '2025-01-08', 1),
    (5, 2, 4.00, 20.500, '2024-12-13', 1),

    -- Ventes pour Lucas Morel (id_client = 6)
    (6, 5, 2.00, 14.750, '2024-12-21', 2),
    (6, 6, 4.00, 26.000, '2025-01-02', 2),

    -- Ventes pour Chloé Bernard (id_client = 7)
    (7, 1, 1.00, 5.000, '2024-12-24', 3),
    (7, 2, 2.50, 13.750, '2024-12-18', 3),

    -- Ventes pour Thomas Lefèvre (id_client = 8)
    (8, 5, 3.00, 19.000, '2024-12-26', 4),
    (8, 8, 5.00, 35.000, '2025-01-15', 4);