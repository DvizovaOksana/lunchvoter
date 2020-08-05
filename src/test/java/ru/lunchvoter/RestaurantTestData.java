package ru.lunchvoter;

import ru.lunchvoter.model.Meal;
import ru.lunchvoter.model.Restaurant;

import java.util.List;

import static java.time.LocalDateTime.now;
import static ru.lunchvoter.MealTestData.*;
import static ru.lunchvoter.model.AbstractBaseEntity.START_SEQ;

public class RestaurantTestData {
    public static final int REST1_ID = START_SEQ + 2;
    public static final int REST2_ID = REST1_ID + 1;

    public static final Restaurant RESTAURANT1 = new Restaurant(REST1_ID, "McDonalds");
    public static final Restaurant RESTAURANT2 = new Restaurant(REST2_ID, "Burger King");

    public static final List<Restaurant> RESTAURANTS = List.of(RESTAURANT1, RESTAURANT2);

    public static Restaurant getNew() {
        Restaurant restaurant = new Restaurant(null, "White Rabbit");
        return restaurant;
    }

    public static Restaurant getUpdated() {
        Restaurant updated = new Restaurant(REST1_ID, "UPDATED McDonalds");
        return updated;
    }
}
