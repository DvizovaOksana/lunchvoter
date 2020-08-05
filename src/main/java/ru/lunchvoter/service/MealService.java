package ru.lunchvoter.service;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.lunchvoter.dao.MealDao;
import ru.lunchvoter.dao.RestaurantDao;
import ru.lunchvoter.model.Meal;
import ru.lunchvoter.model.Restaurant;
import ru.lunchvoter.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;

import static ru.lunchvoter.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MealService {
    private final MealDao mealDao;
    private final RestaurantDao restaurantDao;

    public MealService(MealDao mealDao, RestaurantDao restaurantDao) {
        this.mealDao = mealDao;
        this.restaurantDao = restaurantDao;
    }

    public Meal get(int id, int restaurantId) throws NotFoundException {
        return checkNotFoundWithId(mealDao.findById(id)
                .filter(meal -> meal.getRestaurant().getId() == restaurantId)
                .orElse(null), id);
    }

    public List<Meal> getAll(int restaurantId, LocalDate date){
        return mealDao.getAll(restaurantId, date);
    }

    public void delete(int id, int restaurantId) {
        checkNotFoundWithId(mealDao.delete(id, restaurantId) != 0, id);
    }

    public Meal create(Meal meal, Integer restaurantId){
        Assert.notNull(meal, "meal must not be null");
        Assert.notNull(restaurantId, "restaurantId must not be null");
        if (!meal.isNew() && get(meal.getId(), restaurantId) == null) {
            return null;
        }
        meal.setRestaurant(restaurantDao.getOne(restaurantId));
        return mealDao.save(meal);
    }

    public Meal update(Meal meal, Integer restaurantId) {
        return checkNotFoundWithId(create(meal, restaurantId), meal.getId());
    }
}
