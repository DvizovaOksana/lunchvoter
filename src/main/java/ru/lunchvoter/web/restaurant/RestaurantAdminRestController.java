package ru.lunchvoter.web.restaurant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.lunchvoter.model.Meal;
import ru.lunchvoter.model.Restaurant;
import ru.lunchvoter.service.MealService;
import ru.lunchvoter.service.RestaurantService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static ru.lunchvoter.util.ValidationUtil.assureIdConsistent;
import static ru.lunchvoter.util.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = RestaurantAdminRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantAdminRestController {
    static final String REST_URL = "/rest/admin/restaurants";
    static final String REST_URL_MEALS = "/{restaurantId}/meals";

    protected final Logger log = LoggerFactory.getLogger(getClass());

    private final RestaurantService restaurantService;
    private final MealService mealService;

    @Autowired
    public RestaurantAdminRestController(RestaurantService restaurantService, MealService mealService) {
        this.restaurantService = restaurantService;
        this.mealService = mealService;
    }

    @GetMapping
    public List<Restaurant> getAll() {
        log.info("getAll restaurants");
        return restaurantService.getAll();
    }

    @GetMapping("/{id}")
    public Restaurant get(@PathVariable int id){
        log.info("get restaurant with id {}", id);
        return restaurantService.get(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity<Restaurant> create(@Valid @RequestBody Restaurant restaurant){
        log.info("create restaurant {}", restaurant);
        checkNew(restaurant);
        Restaurant created = restaurantService.create(restaurant);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT, reason = "updated")
    public void update(@Valid @RequestBody Restaurant restaurant, @PathVariable int id){
        log.info("update restaurant {} with id={}", restaurant, id);
        assureIdConsistent(restaurant, id);
        restaurantService.update(restaurant);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT, reason = "deleted")
    public void delete(@PathVariable int id){
        log.info("delete restaurant with id {}", id);
        restaurantService.delete(id);
    }

    @PostMapping(REST_URL_MEALS)
    public ResponseEntity<Meal> addMeal(@Valid @RequestBody Meal meal,
                                        @PathVariable int restaurantId) {
        log.info("add meal {} for a restaurant {}", meal, restaurantId);
        checkNew(meal);
        Meal newMeal = mealService.create(meal, restaurantId);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + REST_URL_MEALS + "/{mealId}")
                .buildAndExpand(restaurantId, newMeal.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(newMeal);
    }

    @PutMapping(REST_URL_MEALS + "/{mealId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT, reason = "updated")
    public void updateMeal(@Valid @RequestBody Meal meal,
                           @PathVariable int restaurantId,
                           @PathVariable int mealId) {
        log.info("update meal {} for a restaurant {}", meal, restaurantId);
        assureIdConsistent(meal, mealId);
        mealService.update(meal, restaurantId);
    }

    @DeleteMapping(REST_URL_MEALS + "/{mealId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT, reason = "deleted")
    public void deleteMeal(@PathVariable int restaurantId,
                           @PathVariable int mealId)  {
        log.info("delete meal {} for a restaurant {}", mealId, restaurantId);
        mealService.delete(mealId, restaurantId);
    }
}
