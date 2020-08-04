package ru.lunchvoter.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.lunchvoter.MealTestData.*;

class MealServiceTest extends AbstractServiceTest{

    @Autowired
    private MealService service;

    @Test
    void get() {
    }

    @Test
    void getAll() {
        assertThat(service.getAll(100002, LocalDate.of(2020, Month.AUGUST, 4))).isEqualTo(Arrays.asList(MEAL1, MEAL2, MEAL5));
    }

    @Test
    void delete() {
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }
}