package ru.lunchvoter.service;

import org.springframework.stereotype.Service;
import ru.lunchvoter.dao.MealDao;
import ru.lunchvoter.dao.RestaurantDao;

@Service
public class MealService {
    private final MealDao mealDao;
    private final RestaurantDao restaurantDao;

    public MealService(MealDao mealDao, RestaurantDao restaurantDao) {
        this.mealDao = mealDao;
        this.restaurantDao = restaurantDao;
    }
}
