DELETE FROM user_roles;
DELETE FROM votes;
DELETE FROM meals;
DELETE FROM users;
DELETE FROM RESTAURANTS;

ALTER SEQUENCE GLOBAL_SEQ RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@gmail.com', 'password'),
       ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 100000),
       ('ADMIN', 100001),
       ('USER', 100001);

INSERT INTO RESTAURANTS (name)
VALUES ('McDonalds'),
       ('Burger King');

INSERT INTO meals (name, date, price, RESTAURANT_ID)
VALUES ('Cheeseburger', today, 50*100, 100002),
       ('Big Mack', today, 150*100, 100002),
       ('Cheeseburger', today, 45*100, 100003),
       ('Big King', today, 170*100, 100003),
       ('Water', today, 20*100, 100002),
       ('Juice', today, 70*100, 100003);