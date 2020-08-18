package ru.lunchvoter.web.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.lunchvoter.model.User;
import ru.lunchvoter.service.UserService;
import ru.lunchvoter.to.UserTo;
import ru.lunchvoter.util.UserUtil;
import ru.lunchvoter.util.exception.NotFoundException;
import ru.lunchvoter.util.json.JsonUtil;
import ru.lunchvoter.web.AbstractControllerTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.lunchvoter.TestUtil.readFromJson;
import static ru.lunchvoter.UserTestData.*;

class ProfileRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = ProfileRestController.REST_URL + '/';

    @Autowired
    private UserService userService;

    @Test
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(USER_MATCHER.contentJson(USER));
    }

    @Test
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL))
                .andExpect(status().isNoContent());
        assertThrows(NotFoundException.class, () -> userService.get(USER_ID));
    }

    @Test
    void update() throws Exception {
        UserTo updatedUserTo = new UserTo(null, "Updated Name", "updated@gmail.com", "updatedPass");

        perform(MockMvcRequestBuilders.put(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updatedUserTo)))
                .andExpect(status().isNoContent());

        USER_MATCHER.assertMatch(userService.get(USER_ID), UserUtil.updateFromTo(new User(USER), updatedUserTo));
    }
}