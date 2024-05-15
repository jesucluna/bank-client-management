CREATE TABLE bank_client (
    id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    "name" TEXT NOT NULL,
    last_name TEXT NOT NULL,
    address TEXT,
    phone TEXT,
    email TEXT UNIQUE NOT NULL,
    birth_date DATE,
    creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO bank_client ("name", last_name, address, phone, email, birth_date, creation_date) VALUES
    ('John', 'Doe', '123 Main St', '123-456-7890', 'john@example.com', '1990-01-15', '2024-05-10 10:00:00'),
    ('Alice', 'Smith', '456 Elm St', '987-654-3210', 'alice@example.com', '1985-08-20', '2024-05-09 09:30:00'),
    ('Bob', 'Johnson', '789 Oak St', '555-555-5555', 'bob@example.com', '1978-03-25', '2024-05-08 11:45:00'),
    ('Mary', 'Brown', '321 Pine St', '111-222-3333', 'mary@example.com', '1992-11-10', '2024-05-07 08:15:00'),
    ('Michael', 'Davis', '654 Cedar St', '444-333-2222', 'michael@example.com', '1980-07-05', '2024-05-06 12:00:00'),
    ('Emily', 'Wilson', '987 Birch St', '777-888-9999', 'emily@example.com', '1995-04-30', '2024-05-05 14:20:00'),
    ('David', 'Martinez', '147 Maple St', '666-777-8888', 'david@example.com', '1987-09-12', '2024-05-04 16:30:00'),
    ('Sarah', 'Garcia', '258 Walnut St', '999-000-1111', 'sarah@example.com', '1998-12-28', '2024-05-03 18:40:00'),
    ('Jessica', 'Rodriguez', '369 Cherry St', '222-111-0000', 'jessica@example.com', '1983-06-17', '2024-05-02 20:50:00'),
    ('Daniel', 'Lopez', '741 Spruce St', '333-444-5555', 'daniel@example.com', '1991-02-08', '2024-05-01 22:00:00'),
	('Jennifer', 'Taylor', '852 Oak St', '111-222-3333', 'jennifer@example.com', '1994-07-12', '2024-04-30 09:30:00'),
    ('William', 'Anderson', '963 Elm St', '444-555-6666', 'william@example.com', '1982-04-18', '2024-04-29 11:45:00'),
    ('Emma', 'Clark', '741 Pine St', '777-888-9999', 'emma@example.com', '1997-11-25', '2024-04-28 14:00:00'),
    ('Christopher', 'Walker', '369 Maple St', '222-333-4444', 'christopher@example.com', '1989-09-30', '2024-04-27 16:15:00'),
    ('Ava', 'Hill', '147 Birch St', '555-666-7777', 'ava@example.com', '1990-12-03', '2024-04-26 18:30:00'),
    ('Matthew', 'Young', '258 Cedar St', '888-999-0000', 'matthew@example.com', '1986-02-15', '2024-04-25 20:45:00'),
    ('Olivia', 'Scott', '963 Walnut St', '333-444-5555', 'olivia@example.com', '1993-05-20', '2024-04-24 22:00:00'),
    ('James', 'King', '852 Cherry St', '666-777-8888', 'james@example.com', '1981-08-08', '2024-04-23 10:15:00'),
    ('Isabella', 'Green', '741 Spruce St', '999-000-1111', 'isabella@example.com', '1996-10-10', '2024-04-22 12:30:00'),
    ('Alexander', 'Allen', '369 Oak St', '111-222-3333', 'alexander@example.com', '1984-03-05', '2024-04-21 14:45:00');