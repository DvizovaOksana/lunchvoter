package ru.lunchvoter;

import ru.lunchvoter.model.Meal;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static java.time.LocalDateTime.of;
import static ru.lunchvoter.model.AbstractBaseEntity.START_SEQ;
import static java.time.LocalDate.of;

public class MealTestData {
    public static final int NOT_FOUND = 10;
    public static final int MEAL1_ID = START_SEQ + 4;
//100004,Cheeseburger,2020-08-04,5000,100002
//            100005,Big Mack,2020-08-04,15000,100002
//            100006,Cheeseburger,2020-08-04,4500,100003
//            100007,Big King,2020-08-04,17000,100003
//            100008,Water,2020-08-04,2000,100002
//            100009,Juice,2020-08-04,7000,100003


    public static final Meal MEAL1 = new Meal(MEAL1_ID, of(2020, Month.AUGUST, 4), "Cheeseburger", 50*100);
    public static final Meal MEAL2 = new Meal(MEAL1_ID + 1, of(2020, Month.AUGUST, 4), "Big Mack", 15000);
    public static final Meal MEAL3 = new Meal(MEAL1_ID + 2, of(2020, Month.AUGUST, 4), "Cheeseburger", 4500);
    public static final Meal MEAL4 = new Meal(MEAL1_ID + 3, of(2020, Month.AUGUST, 4), "Big King", 17000);
    public static final Meal MEAL5 = new Meal(MEAL1_ID + 4, of(2020, Month.AUGUST, 4), "Water", 2000);
    public static final Meal MEAL6 = new Meal(MEAL1_ID + 5, of(2020, Month.AUGUST, 4), "Juice", 7000);

    public static final List<Meal> MEALS = List.of(MEAL6, MEAL5, MEAL4, MEAL3, MEAL2, MEAL1);

    public static Meal getNew() {
        return new Meal(null, of(2020, Month.FEBRUARY, 1), "BIG TASTY", 300*100);
    }

    public static Meal getUpdated() {
        return new Meal(MEAL1_ID, MEAL1.getDate(), "Обновленный завтрак", 200);
    }

}
