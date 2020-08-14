package ru.lunchvoter.web.restaurant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.lunchvoter.model.Restaurant;
import ru.lunchvoter.model.User;
import ru.lunchvoter.service.MealService;
import ru.lunchvoter.service.RestaurantService;
import ru.lunchvoter.web.user.AbstractUserController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = RestaurantUserRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantUserRestController {
    static final String REST_URL = "/rest/restaurants";
    static final String REST_URL_MEALS = "/{restaurantId}/meals";

    protected final Logger log = LoggerFactory.getLogger(getClass());

    private final RestaurantService restaurantService;
    private final MealService mealService;

    @Autowired
    public RestaurantUserRestController(RestaurantService restaurantService, MealService mealService) {
        this.restaurantService = restaurantService;
        this.mealService = mealService;
    }

    @GetMapping
    public List<Restaurant> getAll() {
        log.info("getAll restaurants for today {}", LocalDate.now());
        return restaurantService.getAllWithDishesForDate(LocalDate.now());
    }


}
