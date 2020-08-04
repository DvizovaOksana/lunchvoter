package ru.lunchvoter.service;

import org.springframework.stereotype.Service;
import ru.lunchvoter.dao.MealDao;
import ru.lunchvoter.dao.RestaurantDao;

@Service
public class RestaurantService {
    private final RestaurantDao restaurantDao;
    private final MealDao mealDao;

    public RestaurantService(RestaurantDao restaurantDao, MealDao mealDao) {
        this.restaurantDao = restaurantDao;
        this.mealDao = mealDao;
    }
}
