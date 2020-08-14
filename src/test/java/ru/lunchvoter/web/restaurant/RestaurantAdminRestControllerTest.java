package ru.lunchvoter.web.restaurant;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.lunchvoter.MealTestData;
import ru.lunchvoter.RestaurantTestData;
import ru.lunchvoter.model.Meal;
import ru.lunchvoter.model.Restaurant;
import ru.lunchvoter.model.User;
import ru.lunchvoter.service.MealService;
import ru.lunchvoter.service.RestaurantService;
import ru.lunchvoter.service.UserService;
import ru.lunchvoter.util.exception.NotFoundException;
import ru.lunchvoter.util.json.JsonUtil;
import ru.lunchvoter.web.AbstractControllerTest;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.lunchvoter.TestUtil.readFromJson;
import static ru.lunchvoter.MealTestData.*;
import static ru.lunchvoter.RestaurantTestData.*;
import static ru.lunchvoter.UserTestData.*;
import static ru.lunchvoter.UserTestData.getUpdated;

class RestaurantAdminRestControllerTest extends AbstractControllerTest {

    public static final String REST_URL = RestaurantAdminRestController.REST_URL + '/';
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

    @Test
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + REST1_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(RESTAURANT_MATCHER.contentJson(RESTAURANT1));
    }

    @Test
    void create() throws Exception {
        Restaurant newRestaurant = RestaurantTestData.getNew();

        ResultActions actions = perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newRestaurant)))
                .andDo(print())
                .andExpect(status().isCreated());

        Restaurant created = readFromJson(actions, Restaurant.class);
        int newId = created.getId();
        newRestaurant.setId(newId);

        RESTAURANT_MATCHER.assertMatch(created, newRestaurant);
        RESTAURANT_MATCHER.assertMatch(restaurantService.get(newId), newRestaurant);
    }

    @Test
    void update() throws Exception {
        Restaurant updatedRestaurant = RestaurantTestData.getUpdated();

        perform(MockMvcRequestBuilders.put(REST_URL + REST1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updatedRestaurant)))
                .andDo(print())
                .andExpect(status().isNoContent());

        RESTAURANT_MATCHER.assertMatch(restaurantService.get(REST1_ID), updatedRestaurant);
    }

    @Test
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL + REST1_ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
        assertThrows(NotFoundException.class, () -> restaurantService.get(REST1_ID));
    }

    @Test
    void addMeal() throws Exception {
        Meal newMeal = MealTestData.getNew();

        ResultActions actions = perform(MockMvcRequestBuilders.post(REST_URL_MEALS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newMeal)))
                .andExpect(status().isCreated());

        Meal created = readFromJson(actions, Meal.class);
        int newId = created.getId();
        newMeal.setId(newId);

        MEAL_MATCHER.assertMatch(created, newMeal);
        MEAL_MATCHER.assertMatch(mealService.get(newId, REST1_ID), newMeal);
    }

    @Test
    void updateMeal() throws Exception {
        Meal updatedMeal = MealTestData.getUpdated();

        perform(MockMvcRequestBuilders.put(REST_URL_MEALS + MEAL1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updatedMeal)))
                .andExpect(status().isNoContent());

        MEAL_MATCHER.assertMatch(mealService.get(MEAL1_ID, REST1_ID), updatedMeal);
    }

    @Test
    void deleteMeal() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL_MEALS + MEAL1_ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
        assertThrows(NotFoundException.class, () -> mealService.get(MEAL1_ID, REST1_ID));
    }
}