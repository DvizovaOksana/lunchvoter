package ru.lunchvoter.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
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
    void create() {
    }

    @Test
    void update() {
    }
}