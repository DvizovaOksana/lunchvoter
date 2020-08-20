DELETE FROM user_roles;
DELETE FROM votes;
DELETE FROM meals;
DELETE FROM users;
DELETE FROM RESTAURANTS;

ALTER SEQUENCE GLOBAL_SEQ RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@gmail.com', '{noop}password'),
       ('Admin', 'admin@gmail.com', '{noop}admin');

INSERT INTO user_roles (role, user_id)
VALUES ('ROLE_USER', 100000),
       ('ROLE_ADMIN', 100001),
       ('ROLE_USER', 100001);

INSERT INTO RESTAURANTS (name)
VALUES ('McDonalds'),
       ('Burger King');

INSERT INTO meals (name, date, price, RESTAURANT_ID)
VALUES ('Cheeseburger', '2020-08-04', 50*100, 100002),
       ('Big Mack', '2020-08-04', 150*100, 100002),
       ('Cheeseburger', '2020-08-04', 45*100, 100003),
       ('Big King', '2020-08-04', 170*100, 100003),
       ('Water', '2020-08-04', 20*100, 100002),
       ('Juice', '2020-08-04', 70*100, 100003),
       ('Pizza', '2020-08-05', 500*100, 100003),
       ('Pasta', '2020-08-05', 350*100, 100002),
       ('Super Lunch BK', now, 400*100, 100003),
       ('Business Lunch MD', now, 400*100, 100002)
       ;

INSERT INTO VOTES(USER_ID, DATE, RESTAURANT_ID)
VALUES (100000, '2020-08-04', 100002),
       (100001, '2020-08-04', 100003),
       (100000, '2020-08-05', 100003),
       (100001, '2020-08-05', 100003);