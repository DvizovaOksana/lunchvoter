package ru.lunchvoter.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.lunchvoter.RestaurantTestData;
import ru.lunchvoter.model.Restaurant;
import ru.lunchvoter.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.lunchvoter.RestaurantTestData.*;
import static ru.lunchvoter.MealTestData.*;

class RestaurantServiceTest extends AbstractServiceTest {

    @Autowired
    private RestaurantService service;

    @Test
    void create() {
        Restaurant restaurant = RestaurantTestData.getNew();
        Restaurant created = service.create(restaurant);
        Integer newId = created.getId();
        restaurant.setId(newId);
        RESTAURANT_MATCHER.assertMatch(created, restaurant);
        RESTAURANT_MATCHER.assertMatch(service.get(newId), restaurant);
    }

    @Test
    void get() {
        Restaurant restaurant = service.get(REST1_ID);
        RESTAURANT_MATCHER.assertMatch(RESTAURANT1, restaurant);
    }

    @Test
    void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(10));
    }

    @Test
    void getAll() {
        RESTAURANT_MATCHER.assertMatch(service.getAll(), RESTAURANTS);
    }

    @Test
    void getWithDishesForDate() {
        RESTAURANT_MATCHER.assertMatch(service.getWithDishesForDate(REST1_ID, MEAL1.getDate()), RESTAURANT1);
    }

    @Test
    void getAllWithDishesForDate() {
        RESTAURANT_MATCHER.assertMatch(service.getAllWithDishesForDate(MEAL1.getDate()), List.of(RESTAURANT2, RESTAURANT1));
    }

    @Test
    void getAllWithDishesForDateForDateNotPresent() {
        RESTAURANT_MATCHER.assertMatch(Collections.emptyList(), service.getAllWithDishesForDate(LocalDate.of(1990, 10, 9)));
    }

    @Test
    void update() {
        Restaurant restaurant = RestaurantTestData.getUpdated();
        service.update(restaurant);
        RESTAURANT_MATCHER.assertMatch(service.get(REST1_ID), restaurant);
    }

    @Test
    void delete() {
        service.delete(REST1_ID);
        assertThrows(NotFoundException.class, () -> service.get(REST1_ID));
    }

    @Test
    void deleteNotFound() {
        assertThrows(NotFoundException.class, () -> service.delete(10));
    }
}