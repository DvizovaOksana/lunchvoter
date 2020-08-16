package ru.lunchvoter.web.vote;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.lunchvoter.model.Vote;
import ru.lunchvoter.web.AbstractControllerTest;
import ru.lunchvoter.web.restaurant.RestaurantUserRestController;

import java.time.LocalDate;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.lunchvoter.VoteTestData.*;
import static ru.lunchvoter.UserTestData.*;
import static ru.lunchvoter.RestaurantTestData.*;
import static ru.lunchvoter.TestUtil.*;

class VoteAdminRestControllerTest extends AbstractControllerTest {

    public static final String REST_URL = VoteAdminRestController.REST_URL + '/';

    @Test
    void getForUser() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "user/" + USER_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect((result) -> assertThat(readListFromJsonMvcResult(result, Vote.class)).isEqualTo(VOTES_USER));
    }

    @Test
    void getForUserAndDate() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "user/" + USER_ID)
                .param("date", DATE1.toString()))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect((result) -> assertThat(readListFromJsonMvcResult(result, Vote.class))
                        .isEqualTo(Collections.singletonList(VOTE1_USER)));
    }

    @Test
    void getAllForRestaurant() throws Exception{
        perform(MockMvcRequestBuilders.get(REST_URL + "restaurant/" + REST2_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect((result) -> assertThat(readListFromJsonMvcResult(result, Vote.class)).isEqualTo(VOTES_RESTAURANT2));
    }

    @Test
    void getAllForRestaurantAndDate() throws Exception{
        perform(MockMvcRequestBuilders.get(REST_URL + "restaurant/" + REST2_ID)
                .param("date", DATE2.toString()))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect((result) -> assertThat(readListFromJsonMvcResult(result, Vote.class))
                        .isEqualTo(VOTES_REST2_DATE2));

    }
}