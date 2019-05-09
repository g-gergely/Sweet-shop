TRUNCATE TABLE categories CASCADE; ALTER SEQUENCE categories_id_seq RESTART WITH 1;
TRUNCATE TABLE products CASCADE; ALTER SEQUENCE products_id_seq RESTART WITH 1;
TRUNCATE TABLE suppliers CASCADE; ALTER SEQUENCE suppliers_id_seq RESTART WITH 1;

INSERT INTO suppliers (name, description)
VALUES ('Grannys', 'Traditional, home-made biscuits');
INSERT INTO suppliers (name, description)
VALUES ('Cake Wonders', 'A wide variety of delicious cakes.');
INSERT INTO suppliers (name, description)
VALUES ('Sweeter Sweets', 'Candies - not only for children!');
INSERT INTO suppliers (name, description)
VALUES ('Choco', 'All kinds of fine dark and milk chocolate.');
INSERT INTO suppliers (name, description)
VALUES ('Sweet Desire', 'Special sweets for special occasions.');


INSERT INTO categories (name, department, description)
VALUES ('Biscuit', 'Pastry', 'Various types of sweet baked cakes.');
INSERT INTO categories (name, department, description)
VALUES ('Cake', 'Pastry', 'Various types of sweet baked cakes.');
INSERT INTO categories (name, department, description)
VALUES ('Cupcake', 'Pastry', 'Various types of sweet baked cakes.');
INSERT INTO categories (name, department, description)
VALUES ('Candy', 'Sweets', 'Sweet, sugary food (chocolate or candies).');
INSERT INTO categories (name, department, description)
VALUES ('Chocolate', 'Sweets', 'Sweet, sugary food (chocolate or candies).');


INSERT INTO products (name, default_price, currency, description, category_id, supplier_id)
VALUES ('Crispy cookies', 9, 'USD', 'If something is as crispy as an apple, it must be healthy too!', 1, 1);
INSERT INTO products (name, default_price, currency, description, category_id, supplier_id)
VALUES ('Macaroons', 7, 'USD', 'Light - not just in weight but calories as well.', 1, 1);
INSERT INTO products (name, default_price, currency, description, category_id, supplier_id)
VALUES ('Pancake with honey', 18, 'USD', 'Soft as a pillow, with the treasure of bees.', 1, 1);
INSERT INTO products (name, default_price, currency, description, category_id, supplier_id)
VALUES ('Biscuit Collection', 22, 'USD', 'Finest of our wares, best for tea in the afternoons.', 1, 1);
INSERT INTO products (name, default_price, currency, description, category_id, supplier_id)
VALUES ('Cheesecake', 30, 'USD', 'Cheesy dreams!', 2, 2);
INSERT INTO products (name, default_price, currency, description, category_id, supplier_id)
VALUES ('Chocolate cake', 32, 'USD', 'Dark as night but sweet as our children s smile.', 2, 2);
INSERT INTO products (name, default_price, currency, description, category_id, supplier_id)
VALUES ('Candy-pops', 3, 'USD', 'Candies on a pole, available in various flavours!', 4, 5);
INSERT INTO products (name, default_price, currency, description, category_id, supplier_id)
VALUES ('Fruit candies', 2, 'USD', ' They have it in their name: FRUIT!', 4, 3);
INSERT INTO products (name, default_price, currency, description, category_id, supplier_id)
VALUES ('Rubber bear fruit gums', 2, 'USD', 'So cute that you would like to die for it.', 4, 3);
INSERT INTO products (name, default_price, currency, description, category_id, supplier_id)
VALUES ('Gummy worms', 2, 'USD', 'They ll live longer than Grey Worm.', 4, 3);
INSERT INTO products (name, default_price, currency, description, category_id, supplier_id)
VALUES ('Chocolate selection', 15, 'USD', 'You will get a little from everything... but you will want more.', 5, 5);
INSERT INTO products (name, default_price, currency, description, category_id, supplier_id)
VALUES ('Heart-shape chocolate selection', 17, 'USD', 'Almost as effective as oyster...', 5,5);
INSERT INTO products (name, default_price, currency, description, category_id, supplier_id)
VALUES ('Dark chocolate with coffee', 10, 'USD', 'Coffee? You said COFFEE?', 5, 4);
INSERT INTO products (name, default_price, currency, description, category_id, supplier_id)
VALUES ('Milk chocolate with hazelnut', 8, 'USD', 'It is soo good that it will have you wrapped in haze.', 5, 4);
INSERT INTO products (name, default_price, currency, description, category_id, supplier_id)
VALUES ('Dark chocolate with pistachio', 9, 'USD', 'Chocolate with melted pistachio.', 5, 4);
INSERT INTO products (name, default_price, currency, description, category_id, supplier_id)
VALUES ('Milk chocolate with raspberry', 7, 'USD', 'Julius Cesar s favourite!', 5, 4);
INSERT INTO products (name, default_price, currency, description, category_id, supplier_id)
VALUES ('Blueberry cupcake', 12, 'USD', 'We didn t make it in a microwave!', 3, 5);
INSERT INTO products (name, default_price, currency, description, category_id, supplier_id)
VALUES ('Chocolate cupcake', 11, 'USD', 'Only with the best Swiss chocolate!', 3, 5);
INSERT INTO products (name, default_price, currency, description, category_id, supplier_id)
VALUES ('Vanilla cupcake', 10, 'USD', 'The dream of every princess!', 3, 5);
INSERT INTO products (name, default_price, currency, description, category_id, supplier_id)
VALUES ('Fantastic Five', 35, 'USD', 'Some only dream of cake - we bake it happen!', 2, 2);


