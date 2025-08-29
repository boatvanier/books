CREATE TABLE IF NOT EXISTS users (
                               id SERIAL PRIMARY KEY,
                               user_name VARCHAR(50) UNIQUE NOT NULL,
                               email VARCHAR(100) UNIQUE NOT NULL,
                               password VARCHAR(255) NOT NULL,
                               role VARCHAR(20) DEFAULT 'CUSTOMER',
                               first_name VARCHAR(255) NOT NULL,
                               last_name VARCHAR(255) NOT NULL,
                               created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
                           );
INSERT INTO users (user_name, email, password, role, first_name, last_name)
VALUES
  ('admin', 'admin@bookstore.com', '$2a$12$m8tepDr9AqgHmsSJfCIKZOH41Lx.ZwJSiHjQBdTU4tYaHkFXfLHly', 'ADMIN','admin','admin');

-- 2. Books table
CREATE TABLE IF NOT EXISTS books (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255),
    price DECIMAL(10,2) NOT NULL,
    stock INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
INSERT INTO books (title, author, price, stock)
VALUES
  ('Clean Code', 'Robert C. Martin', 35.99, 10),
  ('Spring in Action', 'Craig Walls', 42.50, 5),
  ('The Pragmatic Programmer', 'Andy Hunt & Dave Thomas', 39.95, 7);


-- 3. Cart items table (many-to-one: user → cart items, book → cart items)
CREATE TABLE IF NOT EXISTS cart_items (
    id SERIAL PRIMARY KEY,
    user_id INT REFERENCES users(id) ON DELETE CASCADE,
    book_id INT REFERENCES books(id),
    quantity INT NOT NULL,
    added_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 4. Orders table
CREATE TABLE IF NOT EXISTS orders (
    id SERIAL PRIMARY KEY,
    user_id INT REFERENCES users(id) ON DELETE SET NULL,
    total_amount DECIMAL(10,2),
    status VARCHAR(30) DEFAULT 'PLACED',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
