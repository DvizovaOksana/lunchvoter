package ru.lunchvoter.web.restaurant;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.lunchvoter.TestUtil;
import ru.lunchvoter.VoteTestData;
import ru.lunchvoter.model.Restaurant;
import ru.lunchvoter.model.Vote;
import ru.lunchvoter.web.AbstractControllerTest;

import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.lunchvoter.RestaurantTestData.*;
import static ru.lunchvoter.TestUtil.userHttpBasic;
import static ru.lunchvoter.UserTestData.USER;

class RestaurantUserRestControllerTest extends AbstractControllerTest {
    public static final String REST_URL = RestaurantUserRestController.REST_URL + '/';
    public static final String REST_URL_MEALS = REST_URL + REST1_ID + "/meals/";

    @Test
    void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL)
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(RESTAURANT_MATCHER.contentJson(RESTAURANTS));
    }

    @Test
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + REST1_ID)
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(RESTAURANT_MATCHER.contentJson(RESTAURANT1));
    }

    @Test
    void getWithMeals() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + REST1_ID + "?withDishes = true")
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(result -> assertThat(TestUtil.readFromJsonMvcResult(result, Restaurant.class)).usingRecursiveComparison()
                                .ignoringFields("meals.restaurant").ignoringAllOverriddenEquals().isEqualTo(getRestaurantWithActualMeals()));
    }

    @Test
    void vote() throws Exception {
        perform(MockMvcRequestBuilders.post(REST_URL + REST1_ID + "/vote")
                .with(userHttpBasic(USER)))
                .andExpect(status().isCreated())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(VoteTestData.VOTE_MATCHER.contentJson(VoteTestData.getActualVote()));
    }

    @Test
    @Transactional(propagation = Propagation.NEVER)
    public void voteChanged() throws Exception {
        perform(MockMvcRequestBuilders.post(REST_URL + REST1_ID + "/vote")
                .with(userHttpBasic(USER)));
        ResultActions actions = perform(MockMvcRequestBuilders.post(REST_URL + REST1_ID + "/vote")
                .with(userHttpBasic(USER)))
                .andDo(print());

        if (LocalTime.now().isAfter(Vote.DECISION_TIME)) {
            actions.andExpect(status().isUnprocessableEntity());
        } else {
            actions.andExpect(status().isOk());
        }
    }
}