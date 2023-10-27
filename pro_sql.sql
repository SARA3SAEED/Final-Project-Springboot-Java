-- Insert data into the Product table
INSERT INTO product (product_id,barcod, product_name, description, price)
VALUES 
    (1,123456789, 'Milk', 'milk', 10),
    (2,987654321, 'bread', 'bread', 20),
    (3,123456798, 'egg', 'egg', 30);

-- Insert data into the Order table
INSERT INTO orders ( order_id,Quantity, price ,orderno, order_status,total)
VALUES 
    (1,2,4,1004, 'New', 8),
    ( 2,3,2,1006, 'Completed',6),
    ( 3,6,2,1012, 'Completed',12);

-- Insert data into the OrderDetails table
INSERT INTO order_details ( orderno, barcod)
VALUES 
    (1,1),  -- 2 units of Product1 in Order1
    (1,2),  -- 1 unit of Product2 in Order1
    (2,3),  -- 3 units of Product3 in Order2
    (3,2);  

-- Insert data into the Payment table
INSERT INTO payment (totall)
VALUES 
    (50),
    (75),
    (30);

-- Insert data into the Cash and Credit tables
INSERT INTO cash (cash_id, cashamount, payment_id)
VALUES 
    (1, 50,1),
    (2, 30,2);

ALTER TABLE credit MODIFY expirationdate VARCHAR(10);
INSERT INTO credit (credit_id, creditcardnumber, cvv, expirationdate, payment_id)
VALUES 
    (1, 123456, 123, '2023-12-25', 2),
    (2, 987654, 456, '2023-12-26', 3);
    
INSERT INTO roles (name) VALUES
('ROLE_USER'),
('ROLE_CASHIER');

INSERT INTO users (username, password) VALUES
('john_doe', 'password123'),
('cashier1', 'cashierpassword');

INSERT INTO user_roles (user_id, role_id) VALUES
(1, 1), -- User John Doe has ROLE_USER
(2, 2); -- Cashier1 has ROLE_CASHIER

    