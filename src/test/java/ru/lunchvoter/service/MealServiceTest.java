package ru.lunchvoter.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.lunchvoter.RestaurantTestData;
import ru.lunchvoter.model.Meal;
import ru.lunchvoter.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.lunchvoter.MealTestData.*;
import static ru.lunchvoter.RestaurantTestData.*;

class MealServiceTest extends AbstractServiceTest{

    @Autowired
    private MealService service;

    @Test
    void get() {
        Meal actual = service.get(MEAL1_ID, REST1_ID);
        MEAL_MATCHER.assertMatch(actual, MEAL1);
    }

    @Test
    void getAll() {
        List<Meal> meals = service.getAll(100002, LocalDate.of(2020, Month.AUGUST, 4));
        assertThat(meals).isEqualTo(Arrays.asList(MEAL1, MEAL2, MEAL5));
    }

    @Test
    void delete() {
        service.delete(MEAL1_ID, REST1_ID);
        assertThrows(NotFoundException.class, () -> service.get(MEAL1_ID, REST1_ID));
    }

    @Test
    void deleteNotFound() throws Exception {
        assertThrows(NotFoundException.class, () -> service.delete(NOT_FOUND, REST1_ID));
    }

    @Test
    void deleteNotOwn() throws Exception {
        assertThrows(NotFoundException.class, () -> service.delete(MEAL1_ID, REST2_ID));
    }

    @Test
    void create() {
        Meal meal = getNew();
        Meal created = service.create(meal, meal.getRestaurant().getId());
        meal.setId(created.getId());
        MEAL_MATCHER.assertMatch(created, meal);
        MEAL_MATCHER.assertMatch(service.get(created.getId(), created.getRestaurant().getId()), meal);
    }

    @Test
    void update() {
        Meal meal = getUpdated();
        service.update(meal, meal.getRestaurant().getId());
        MEAL_MATCHER.assertMatch(service.get(meal.getId(), meal.getRestaurant().getId()), meal);
    }

    @Test
    void updateNotOwn() throws Exception {
        Meal meal = MEAL1;
        meal.setRestaurant(RESTAURANT1);
        NotFoundException exception = assertThrows(NotFoundException.class, () -> service.update(meal, REST2_ID));
        //Assertions.assertEquals("Not found entity with id=" + MEAL1_ID, exception.getMessage());
    }
}