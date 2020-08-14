package ru.lunchvoter.service;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.lunchvoter.dao.RestaurantDao;
import ru.lunchvoter.model.Restaurant;
import ru.lunchvoter.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;

import static ru.lunchvoter.util.ValidationUtil.checkNotFoundWithId;

@Service
public class RestaurantService {
    private final RestaurantDao dao;

    public RestaurantService(RestaurantDao dao) {
        this.dao = dao;
    }

    public Restaurant create(Restaurant restaurant){
        Assert.notNull(restaurant, "restaurant must not be null");
        return dao.save(restaurant);
    }

    public Restaurant get(int restaurantId) throws NotFoundException{
        return checkNotFoundWithId(dao.findById(restaurantId)
                .orElse(null), restaurantId);
    }

    public List<Restaurant> getAll(){
        return dao.findAll(Sort.by("name"));
    }

    public Restaurant getWithDishesForDate(int id, LocalDate date){
        return checkNotFoundWithId(dao.getWithDishesForDate(id, date)
                .orElse(null), id);
    }

    public List<Restaurant> getAllWithDishesForDate(LocalDate date){
        return dao.getAllWithDishesForDate(date);
    }

    public void update(Restaurant restaurant){
        Assert.notNull(restaurant, "restaurant must not be null");
        checkNotFoundWithId(dao.save(restaurant), restaurant.getId());
    }

    public void delete(int id){
        checkNotFoundWithId(dao.delete(id) != 0, id);
    }

}
