-- Скрипт инициализации БД persons_jee для проекта Java EE «Управление персоналом»
-- Кодировка файла — UTF-8 (BOM-less). Запуск:
--   psql -U postgres -h localhost -d persons_jee -f init_db.sql

DROP TABLE IF EXISTS persons;
DROP TABLE IF EXISTS roles;

CREATE TABLE roles (
    id       serial PRIMARY KEY,
    rolename varchar(50) NOT NULL
);

CREATE TABLE persons (
    id        serial PRIMARY KEY,
    roleid    integer NOT NULL REFERENCES roles(id) ON DELETE RESTRICT,
    firstname varchar(50),
    lastname  varchar(50),
    phone     varchar(30),
    email     varchar(100)
);

INSERT INTO roles (rolename) VALUES
    ('Директор'),
    ('Главный инженер'),
    ('Программист'),
    ('Бухгалтер'),
    ('Менеджер');

INSERT INTO persons (roleid, firstname, lastname, phone, email) VALUES
    (1, 'Иван',    'Петров',    '+7 (900) 123-45-67', 'ivan.petrov@example.com'),
    (2, 'Мария',   'Сидорова',  '+7 (900) 234-56-78', 'maria.s@example.com'),
    (3, 'Алексей', 'Иванов',    '+7 (900) 345-67-89', 'a.ivanov@example.com'),
    (3, 'Елена',   'Кузнецова', '+7 (900) 456-78-90', 'e.kuznetsova@example.com'),
    (4, 'Дмитрий', 'Смирнов',   '+7 (900) 567-89-01', 'd.smirnov@example.com');
