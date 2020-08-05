package ru.lunchvoter;

import ru.lunchvoter.model.Restaurant;

import static ru.lunchvoter.model.AbstractBaseEntity.START_SEQ;

public class RestaurantTestData {
    public static final int REST1_ID = START_SEQ + 2;
    public static final int REST2_ID = REST1_ID + 1;

    public static final Restaurant RESTAURANT1 = new Restaurant(REST1_ID, "McDonalds");
    public static final Restaurant RESTAURANT2 = new Restaurant(REST2_ID, "Burger King");


}
