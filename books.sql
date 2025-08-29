--Create tables

-- 1. Users table
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    user_name VARCHAR(50) UNIQUE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(20) DEFAULT 'CUSTOMER',
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 2. Books table
CREATE TABLE books (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255),
    price DECIMAL(10,2) NOT NULL,
    stock INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 3. Cart items table (many-to-one: user → cart items, book → cart items)
CREATE TABLE cart_items (
    id SERIAL PRIMARY KEY,
    user_id INT REFERENCES users(id) ON DELETE CASCADE,
    book_id INT REFERENCES books(id),
    quantity INT NOT NULL,
    added_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 4. Orders table
CREATE TABLE orders (
    id SERIAL PRIMARY KEY,
    user_id INT REFERENCES users(id) ON DELETE SET NULL,
    total_amount DECIMAL(10,2),
    status VARCHAR(30) DEFAULT 'PLACED',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 5. Order items table (many-to-one: order → order_items, book → order_items)
CREATE TABLE order_items (
    id SERIAL PRIMARY KEY,
    order_id INT REFERENCES orders(id) ON DELETE CASCADE,
    book_id INT REFERENCES books(id),
    quantity INT NOT NULL,
    price_at_purchase DECIMAL(10,2) NOT NULL
);

--Sample data

-- 1. Users
INSERT INTO users (user_name, email, password, role, first_name, last_name)
VALUES 
  ('alice', 'alice@example.com', 'password123', 'CUSTOMER','alice', 'brown'),
  ('bob', 'bob@example.com', 'securepass', 'CUSTOMER','bob','white'),
  ('admin', 'admin@bookstore.com', '"$2a$12$m8tepDr9AqgHmsSJfCIKZOH41Lx.ZwJSiHjQBdTU4tYaHkFXfLHly"', 'ADMIN','admin','admin');

-- 2. Books
INSERT INTO books (title, author, price, stock)
VALUES
  ('Clean Code', 'Robert C. Martin', 35.99, 10),
  ('Spring in Action', 'Craig Walls', 42.50, 5),
  ('The Pragmatic Programmer', 'Andy Hunt & Dave Thomas', 39.95, 7),
  ('Effective Java', 'Joshua Bloch', 45.00, 3);

-- 3. Cart items (alice adds 2 books)
INSERT INTO cart_items (user_id, book_id, quantity)
VALUES 
  (1, 1, 1),  -- Alice adds Clean Code
  (1, 2, 2);  -- Alice adds 2 copies of Spring in Action

-- 4. Orders (Alice places 1 order)
INSERT INTO orders (user_id, total_amount, status)
VALUES 
  (1, 120.99, 'PLACED');

-- 5. Order items (corresponding to order above)
INSERT INTO order_items (order_id, book_id, quantity, price_at_purchase)
VALUES 
  (1, 1, 1, 35.99),    -- Clean Code x1
  (1, 2, 2, 42.50);    -- Spring in Action x2
