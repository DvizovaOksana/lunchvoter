package ru.lunchvoter.web.vote;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.lunchvoter.model.Vote;
import ru.lunchvoter.service.VoteService;
import ru.lunchvoter.web.AbstractControllerTest;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.lunchvoter.VoteTestData.*;
import static ru.lunchvoter.TestUtil.*;

class VoteRestControllerTest extends AbstractControllerTest {
    @Autowired
    protected VoteService service;

    @Test
    void getAllOwn() throws Exception {
        perform(MockMvcRequestBuilders.get(VoteRestController.REST_URL))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect((result) -> assertThat(readListFromJsonMvcResult(result, Vote.class)).isEqualTo(VOTES_USER));
    }

    @Test
    void getAllOwnForDate() throws Exception {
        perform(MockMvcRequestBuilders.get(VoteRestController.REST_URL)
                .param("date", DATE1.toString()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect((result) -> assertThat(readListFromJsonMvcResult(result, Vote.class)).isEqualTo(Collections.singletonList(VOTE1_USER)));
    }
}