package ru.lunchvoter.web.restaurant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.lunchvoter.model.Restaurant;
import ru.lunchvoter.model.Vote;
import ru.lunchvoter.service.RestaurantService;
import ru.lunchvoter.service.VoteService;
import ru.lunchvoter.web.SecurityUtil;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = RestaurantUserRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantUserRestController {
    static final String REST_URL = "/rest/restaurants";

    protected final Logger log = LoggerFactory.getLogger(getClass());

    private final RestaurantService restaurantService;
    private final VoteService voteService;

    @Autowired
    public RestaurantUserRestController(RestaurantService restaurantService, VoteService voteService) {
        this.restaurantService = restaurantService;
        this.voteService = voteService;
    }

    @GetMapping
    public List<Restaurant> getAll() {
        log.info("getAll restaurants for today ({})", LocalDate.now());
        return restaurantService.getAllWithMealsForDate(LocalDate.now());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Restaurant get(@PathVariable("id") int id,
                          @RequestParam(value = "withDishes", required = false, defaultValue = "true") boolean withDishes) {
        log.info("get restaurant {} withDishes = {}", id, withDishes);
        if (withDishes) {
            return restaurantService.getWithMealsForDate(id, LocalDate.now());
        } else {
            return restaurantService.get(id);
        }
    }

    @PostMapping(value = "/{id}/vote", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vote> vote(@PathVariable("id") int id) {
        log.info("vote for restaurant {} by user {}", id, SecurityUtil.authUserId());
        Vote vote = voteService.vote(SecurityUtil.authUserId(), id);
        if (vote != null){
            URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path(REST_URL + "/{id}")
                    .buildAndExpand(vote.getId()).toUri();
            return ResponseEntity.created(uriOfNewResource).body(vote);
        }
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
    }
}
