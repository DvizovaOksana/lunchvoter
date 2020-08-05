package ru.lunchvoter;

import ru.lunchvoter.model.Meal;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static java.time.LocalDateTime.now;
import static java.time.LocalDateTime.of;
import static ru.lunchvoter.model.AbstractBaseEntity.START_SEQ;
import static java.time.LocalDate.of;
import static ru.lunchvoter.RestaurantTestData.*;

public class MealTestData {
    public static final TestMatcher<Meal> MEAL_MATCHER = TestMatcher.usingFieldsWithIgnoringComparator(Meal.class,"restaurant");

    public static final int NOT_FOUND = 10;
    public static final int MEAL1_ID = START_SEQ + 4;

    public static final Meal MEAL1 = new Meal(MEAL1_ID, of(2020, Month.AUGUST, 4), "Cheeseburger", 50*100);
    public static final Meal MEAL2 = new Meal(MEAL1_ID + 1, of(2020, Month.AUGUST, 4), "Big Mack", 150*100);
    public static final Meal MEAL3 = new Meal(MEAL1_ID + 2, of(2020, Month.AUGUST, 4), "Cheeseburger", 45*100);
    public static final Meal MEAL4 = new Meal(MEAL1_ID + 3, of(2020, Month.AUGUST, 4), "Big King", 170*100);
    public static final Meal MEAL5 = new Meal(MEAL1_ID + 4, of(2020, Month.AUGUST, 4), "Water", 20*100);
    public static final Meal MEAL6 = new Meal(MEAL1_ID + 5, of(2020, Month.AUGUST, 4), "Juice", 70*100);

    public static final Meal MEAL7 = new Meal(MEAL1_ID + 6, of(2020, Month.AUGUST, 5), "Pizza", 500*100);
    public static final Meal MEAL8 = new Meal(MEAL1_ID + 7, of(2020, Month.AUGUST, 5), "Pasta", 350*100);
    {
        MEAL1.setRestaurant(RESTAURANT1);
        MEAL3.setRestaurant(RESTAURANT1);
        MEAL5.setRestaurant(RESTAURANT1);
        MEAL8.setRestaurant(RESTAURANT1);

        MEAL2.setRestaurant(RESTAURANT1);
        MEAL4.setRestaurant(RESTAURANT1);
        MEAL6.setRestaurant(RESTAURANT1);
        MEAL7.setRestaurant(RESTAURANT1);
    }

    public static final List<Meal> REST1_MEALS = List.of(MEAL5, MEAL3, MEAL1);
    public static final List<Meal> REST2_MEALS = List.of(MEAL6, MEAL4, MEAL2);

    public static Meal getNew() {
        return new Meal(null, now().toLocalDate(), "NEW BIG TASTY", 300*100);
    }

    public static Meal getUpdated() {
        return new Meal(MEAL1_ID, MEAL1.getDate(), "UPDATED CHEESEBURGER", 55*100);
    }
}
