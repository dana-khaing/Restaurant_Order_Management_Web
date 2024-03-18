
-- MenuItems table
CREATE TABLE MenuItems (
    ItemID INT PRIMARY KEY,
    Category VARCHAR(50),
    DishName VARCHAR(100),
    Description TEXT
    Price DECIMAL(10, 2) NOT NULL
);

-- menu data
INSERT INTO MenuItems  (ItemID, Category, DishName, Description, Price)
VALUES
    ( 1, 'Appetizers', 'Tlayudas (Vegetarian option)', 'Large, thin, crispy tortillas with vegetable toppings.', 6.50 ),
    ( 2, 'Appetizers', 'Tostadas de Chapulines ', 'Tostadas with guacamole and seasoned chapulines.', 7.99 ),
    ( 3, 'Appetizers', 'Guacamole', 'Fresh avocados, tomatoes, onions, cilantro, lime juice, salt, and pepper.', 6.50)
    ( 4, 'Main Courses', 'Mole Negro with Chicken', 'Slow-cooked chicken in dark mole sauce.', 11.99),
    ( 5, 'Main Courses', 'Shrimp ceviche', 'Shrimp marinated in lime juice, mixed with tomatoes, onions, cilantro, and avocado.', 12.99), 
    ( 6, 'Main Courses', 'Tacos al pastor', 'Marinated and grilled pork served in corn tortillas with pineapple and cilantro.', 9.99);
    ( 7, 'Main Courses', 'Tlayudas con Tasajo', 'Grilled and seasoned thinly sliced beef on tlayudas.', 12.99),
    ( 8, 'Main Courses', 'Enfrijoladas', 'Rolled tortillas filled with chicken and black bean sauce.', 11.99),
    ( 9, 'Sides', 'Chiles Rellenos de Quesillo', 'Poblano peppers stuffed with Oaxacan cheese.', 10.99),
    ( 10, 'Sides', 'Sikil P''ak (Pumpkin Seed Dip)', 'Dip made with pumpkin seeds, tomatoes, and spices.', 8.99),
    ( 11, 'Desserts', 'Chocolate Oaxaqueño', 'Oaxacan-style hot chocolate.', 6.99),
    ( 12, 'Desserts', 'Nicuatole', 'Traditional Oaxacan dessert with masa and chocolate.', 7.99),
    ( 13, 'Desserts', 'Tres Leches Cake', 'Sponge cake soaked in three kinds of milk, topped with whipped cream and fresh berries.', 8.99),
    ( 14, 'Desserts', 'Mango Sorbet', 'Refreshing mango sorbet.', 5.99)
    ( 15, 'Beverages', 'Mezcal Cocktail', 'Various mezcal-based cocktails.', 9.99),
    ( 16, 'Beverages', 'Horchata', 'Rice and based horchata.', 4.99);
    ( 17, 'Beverages', 'Sodas', 'Different types of sodas', 2.80);


-- table for allergens 
CREATE TABLE Allergens (
    AllergenID INT PRIMARY KEY,
    AllergenName VARCHAR(50)
);

-- link menu items with allergens 
CREATE TABLE MenuItemAllergens (
    ItemID INT,
    AllergenID INT,
    FOREIGN KEY (ItemID) REFERENCES MenuItems(ItemID),
    FOREIGN KEY (AllergenID) REFERENCES Allergens(AllergenID)
);

-- allergens data 
INSERT INTO Allergens (AllergenID, AllergenName)
VALUES
    (1, 'Gluten'),
    (2, 'Dairy'),
    (3, 'Shellfish'),
    (4, 'Nuts'), 
    (5, 'Eggs');

--  allergens for specific menu items
INSERT INTO MenuItemAllergens (ItemID, AllergenID)
VALUES
    (1, 1),  -- Tlayudas = gluten
    (2, 2),  -- Tostadas de Chapulines = dairy
    (4, 1),  -- Mole Negro with Chicken = gluten
    (4, 2),  -- Mole Negro with Chicken = dairy
    (4, 4),  -- Mole Negro = nuts
    (5, 3),  -- Shrimp Ceviche = shellfish
    (6, 1),  -- Tacos al pastor = gluten
    (7, 1),  -- Tlayudas con Tasajo = gluten
    (8, 1),  -- Enfrijoladas = gluten
    (9, 2),  -- Chiles Rellenos de Quesillo = dairy
    (10, 4), -- Sikil P'ak = nuts
    (11, 2), -- Chocolate Oaxaqueño = dairy
    (12, 2), -- Nicuatole = diary
    (13, 1), -- Tres Leches = gluten
    (13, 2), -- Tres leches = dairy
    (13, 5), -- Tres leches = eggs
    (16, 4), -- Horchata = nuts


-- table for calories 
CREATE TABLE CalorieInformation (
    ItemID INT PRIMARY KEY,
    Calories INT,
    FOREIGN KEY (ItemID) REFERENCES MenuItems(ItemID)
);

-- calorie information for specific menu items
INSERT INTO CalorieInformation (ItemID, Calories)
VALUES
    (1, 400),   -- Tlayudas (Veggie)
    (2, 350),   -- Chapulines Tostadas
    (3, 200),   -- Guacamole
    (4, 500),   -- Mole Negro with Chicken
    (5, 300),   -- Shrimp Ceviche
    (6, 450),   -- Tacos al Pastor
    (7, 400),   -- Tlayudas con Tasajo
    (8, 350),   -- Enfrijoladas
    (9, 300),   -- Chiles Rellenos de Quesillo
    (10, 150),  -- Sikil P'ak (Pumpkin Seed Dip)
    (11, 250),  -- Chocolate Oaxaqueño
    (12, 300),  -- Nicuatole
    (13, 350),  -- Tres Leches Cake
    (14, 120),  -- Mango Sorbet
    (15, NULL), -- Mezcal Cocktail 
    (16, 150),  -- Horchata
    (17, 150);  -- Sodas


