package ru.lunchvoter.web.restaurant;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.lunchvoter.service.MealService;
import ru.lunchvoter.service.RestaurantService;
import ru.lunchvoter.web.AbstractControllerTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.lunchvoter.RestaurantTestData.*;

class RestaurantUserRestControllerTest extends AbstractControllerTest {
    public static final String REST_URL = RestaurantUserRestController.REST_URL + '/';
    public static final String REST_URL_MEALS = REST_URL + REST1_ID + "/meals/";

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private MealService mealService;

    @Test
    void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(RESTAURANT_MATCHER.contentJson(RESTAURANTS));
    }
}