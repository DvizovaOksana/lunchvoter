package ru.lunchvoter.web.vote;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.lunchvoter.model.Vote;
import ru.lunchvoter.service.VoteService;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(value = VoteAdminRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteAdminRestController {
    static final String REST_URL = "/rest/admin/votes";

    protected final Logger log = LoggerFactory.getLogger(getClass());

    private final VoteService voteService;

    @Autowired
    public VoteAdminRestController(VoteService voteService) {
        this.voteService = voteService;
    }

    @GetMapping(value = "/user/{userId}")
    public List<Vote> getForUserAndDate(@PathVariable int userId,
                                        @RequestParam(required = false)
                                        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        log.info("get all votes for user {} and date {}", userId, date);
        return (date == null) ?
                voteService.getAllByUser(userId) :
                Collections.singletonList(voteService.getByUserAndDate(userId, date));
    }

    @GetMapping(value = "/restaurant/{restaurantId}")
    public List<Vote> getAllForRestaurantAndDate(@PathVariable int restaurantId,
                                                 @RequestParam(required = false)
                                                 @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        log.info("get all votes for restaurant {} and date {}", restaurantId, date);
        return (date == null) ?
                voteService.getAllByRestaurant(restaurantId) :
                voteService.getAllByRestaurantAndDate(restaurantId, date);
    }
}
