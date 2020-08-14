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

    public static final Meal MEAL9 = new Meal(MEAL1_ID + 8, LocalDate.now(), "Super Lunch BK", 400*100);
    public static final Meal MEAL10 = new Meal(MEAL1_ID + 9, LocalDate.now(), "Business Lunch MD", 400*100);

//    100012,Super Lunch BK,2020-08-14,40000,100003
//    100013,Business Lunch MD,2020-08-14,40000,100002

    {
        MEAL1.setRestaurant(RESTAURANT1);
        MEAL3.setRestaurant(RESTAURANT1);
        MEAL5.setRestaurant(RESTAURANT1);
        MEAL8.setRestaurant(RESTAURANT1);
        MEAL10.setRestaurant(RESTAURANT1);

        MEAL2.setRestaurant(RESTAURANT2);
        MEAL4.setRestaurant(RESTAURANT2);
        MEAL6.setRestaurant(RESTAURANT2);
        MEAL7.setRestaurant(RESTAURANT2);
        MEAL9.setRestaurant(RESTAURANT2);
    }

    public static final List<Meal> REST1_MEALS = List.of(MEAL10, MEAL8, MEAL5, MEAL3, MEAL1);
    public static final List<Meal> REST2_MEALS = List.of(MEAL9, MEAL7, MEAL6, MEAL4, MEAL2);

    public static Meal getNew() {
        Meal meal = new Meal(null, LocalDate.now(), "NEW BIG TASTY", 300*100);
        meal.setRestaurant(RESTAURANT1);
        return meal;
    }

    public static Meal getUpdated() {
        Meal updated = new Meal(MEAL1_ID, MEAL1.getDate(), "UPDATED CHEESEBURGER", 55*100);
        updated.setRestaurant(RESTAURANT1);
        return updated;
    }
}
