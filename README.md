# Graduation project - LunchVoter

Design and implement a REST API using Hibernate/Spring/SpringMVC (or Spring-Boot) without frontend.

The task is:

Build a voting system for deciding where to have lunch.

2 types of users: admin and regular users
Admin can input a restaurant and it's lunch menu of the day (2-5 items usually, just a dish name and price)
Menu changes each day (admins do the updates)
Users can vote on which restaurant they want to have lunch at
Only one vote counted per user
If user votes again the same day:
If it is before 11:00 we asume that he changed his mind.
If it is after 11:00 then it is too late, vote can't be changed
Each restaurant provides new menu each day.

As a result, provide a link to github repository. It should contain the code, README.md with API documentation and couple curl commands to test it.

P.S.: Make sure everything works with latest version that is on github :)

P.P.S.: Asume that your API will be used by a frontend developer to build frontend on top of that.

## REST API


### Admin Users API
Base URL: http://localhost:8080/lunchvoter/rest/admin/users

| Description | Method | Curl command                                          | Response code |
|-------------|--------|-----------------------------------------------------------------------------------------|-----|
| Get all     |   GET  | `curl -s http://localhost:8080/lunchvoter/rest/admin/users --user admin@gmail.com:admin` | 200 |
| Get with id |   GET  | `curl -s http://localhost:8080/lunchvoter/rest/admin/users/100000 --user admin@gmail.com:admin` | 200 |
| Get by email| GET | `curl -s http://localhost:8080/lunchvoter/rest/admin/users/by?email=admin@gmail.com --user admin@gmail.com:admin` | 200 |
| Add new User|   POST  | `curl -s -X POST -d '{"name":"New user","email":"new@gmail.com","password":"newpass","roles":["ROLE_USER"]}' -H 'Content-Type:application/json' http://localhost:8080/lunchvoter/rest/admin/users --user admin@gmail.com:admin` | 201 |
| Update      | PUT | `curl -s -X PUT -d '{"id":100001,"name":"Updated","email":"admin@gmail.com","password":"updatedpass","roles":["ROLE_USER","ROLE_ADMIN"]}' -H 'Content-type: application/json' http://localhost:8080/lunchvoter/rest/admin/users/100001 --user admin@gmail.com:admin` | 204 |
| Delete      | DELETE | `curl -s -X DELETE http://localhost:8080/lunchvoter/rest/admin/users/100000 --user admin@gmail.com:admin` | 204 |


### User Profile API
Base URL: http://localhost:8080/lunchvoter/rest/profile/

| Description | Method | Curl command                                          | Response code |
|-------------|--------|-------------------------------------------------------|----|
| Get own     |   GET  | `curl -s http://localhost:8080/lunchvoter/rest/profile/ --user user@gmail.com:password` | 200 |
| Update      |   PUT  | `curl -s http://localhost:8080/lunchvoter/rest/profile -X PUT -H 'Content-Type:application/json;charset=UTF-8' --user user@gmail.com:password -d '{"name":"UpdatedUser", "email":"userUpdated@gmail.com", "password":"updatedPass"}'` | 204 |
| Delete      | DELETE | `curl -s http://localhost:8080/lunchvoter/rest/profile/ -X DELETE --user user@gmail.com:password` | 204 |


### Admin Restaurants API
Base URL: http://localhost:8080/lunchvoter/rest/admin/restaurants

| Description | Method | Curl command                                          | Response code |
|-------------|--------|-------------------------------------------------------|----|
| Get all restaurants     |   GET  | `curl -s http://localhost:8080/lunchvoter/rest/admin/restaurants --user admin@gmail.com:admin` | 200 |
| Get restaurant with id |   GET  | `curl -s http://localhost:8080/lunchvoter/rest/admin/restaurants/100003 --user admin@gmail.com:admin` | 200 |
| Create restaurant     |   POST | `curl -s -X POST -d '{"name":"newRestaurant"}' -H 'Content-Type:application/json' http://localhost:8080/lunchvoter/rest/admin/restaurants --user admin@gmail.com:admin` | 201 |
| Update restaurant     | PUT    | `curl -s -X PUT -d '{"name":"Updated Restaurant"}' -H 'Content-type: application/json' http://localhost:8080/lunchvoter/rest/admin/restaurants/100003 --user admin@gmail.com:admin` | 204 |
| Delete restaurant     | DELETE | `curl -s -X DELETE http://localhost:8080/lunchvoter/rest/admin/restaurants/100003 --user admin@gmail.com:admin` | 204 |
| Create meal for restaurant with id |   POST | `curl -s -X POST -d '{"name":"newMeal", "price":"500000", "date":"2020-08-17"}' -H 'Content-Type:application/json' http://localhost:8080/lunchvoter/rest/admin/restaurants/100002/meals --user admin@gmail.com:admin` | 201 |
| Update meal for restaurant with id | PUT    | `curl -s -X PUT -d '{"name":"updatedMeal", "price":"99999", "date":"2020-08-17"}' -H 'Content-type: application/json' http://localhost:8080/lunchvoter/rest/admin/restaurants/100002/meals/100004 --user admin@gmail.com:admin` | 204 |
| Delete meal for restaurant with id | DELETE | `curl -s -X DELETE http://localhost:8080/lunchvoter/rest/admin/restaurants/100002/meals/100004 --user admin@gmail.com:admin` | 204 |


### User Restaurants API
Base URL: http://localhost:8080/lunchvoter/rest/restaurants

| Description | Method | Curl command                                          | Response code |
|-------------|--------|-------------------------------------------------------|----|
| Get all restaurants with meals for today |   GET  | `curl -s http://localhost:8080/lunchvoter/rest/restaurants --user user@gmail.com:password` | 200 |
| Get restaurant with meals with id |   GET  | `curl -s http://localhost:8080/lunchvoter/rest/restaurants/100002 --user user@gmail.com:password` | 200 |
| Vote for restaurant     |   POST | `curl -s  -X POST -H 'Content-Type:application/json' http://localhost:8080/lunchvoter/rest/restaurants/100002/vote --user user@gmail.com:password` | 201 |
| Change the vote today (before decision time) |  POST | `curl -s  -X POST -H 'Content-Type:application/json' http://localhost:8080/lunchvoter/rest/restaurants/100002/vote --user user@gmail.com:password` | 200 |
| Change the vote today (after decision time)  |  POST | `curl -s  -X POST -H 'Content-Type:application/json' http://localhost:8080/lunchvoter/rest/restaurants/100002/vote --user user@gmail.com:password` | 422 |


### Admin Votes API
Base URL: http://localhost:8080/lunchvoter/rest/admin/votes

| Description | Method | Curl command                                          | Response code |
|-------------|--------|-------------------------------------------------------|----|
| Get all votes by user with restaurants |   GET  | `curl -s http://localhost:8080/lunchvoter/rest/admin/votes/user/100000 --user admin@gmail.com:admin` | 200 |
| Get vote by user for specified date with restaurants |   GET  | `curl -s http://localhost:8080/lunchvoter/rest/admin/votes/user/100000?date=2020-08-05 --user admin@gmail.com:admin` | 200 |
| Get all votes for restaurant with users |   GET | `curl -s http://localhost:8080/lunchvoter/rest/admin/votes/restaurant/100002 --user admin@gmail.com:admin` | 200 |
| Get all votes for restaurant for specified date with users | GET  | `curl -s  http://localhost:8080/lunchvoter/rest/admin/votes/restaurant/100002?date=2020-08-04 -H 'Content-type: application/json' --user admin@gmail.com:admin` | 200 |


### User Votes API
Base URL: http://localhost:8080/lunchvoter/rest/votes

| Description | Method | Curl command                                          | Response code |
|-------------|--------|-------------------------------------------------------|----|
| Get own votes with restaurants |   GET  | `curl -s http://localhost:8080/lunchvoter/rest/votes --user user@gmail.com:password` | 200 |
| Get own vote for specified date with restaurants |   GET  | `curl -s http://localhost:8080/lunchvoter/rest/votes?date=2020-08-04 --user user@gmail.com:password` | 200 |
