package ru.lunchvoter.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.lunchvoter.model.User;
import ru.lunchvoter.model.Vote;
import ru.lunchvoter.util.exception.LateVoteException;
import ru.lunchvoter.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.lunchvoter.VoteTestData.*;
import static ru.lunchvoter.UserTestData.*;
import static ru.lunchvoter.RestaurantTestData.*;

class VoteServiceTest extends AbstractServiceTest {

    @Autowired
    private VoteService service;

    @Test
    void getVotesByUser() {
        List<Vote> votes = service.getVotesByUser(USER_ID);
        VOTE_MATCHER.assertMatch(votes, VOTES_USER);
    }

    @Test
    void findByUserIdAndDate() {
        Vote vote = service.getByUserIdAndDate(USER_ID, DATE1);
        VOTE_MATCHER.assertMatch(vote, VOTE1_USER);
    }

    @Test
    void findByUserIdAndRestaurantAndDate() {
        Vote vote = service.getByUserIdAndRestaurantAndDate(ADMIN_ID, RESTAURANT2.getId(), DATE2);
        Assertions.assertThat(vote).isEqualTo(VOTE2_ADMIN);
    }

    @Test
    void getVotesByRestaurantAndDate() {
        List<Vote> votes = service.getVotesByRestaurantAndDate(RESTAURANT2.getId(), DATE2);
        Assertions.assertThat(votes).isEqualTo(VOTES_REST2_DATE2);
    }

    @Test
    void vote() {
        Vote vote = service.vote(USER_ID, RESTAURANT1.getId());
        Vote newVote = service.getByUserIdAndDate(USER_ID, LocalDate.now());

        Assertions.assertThat(vote).isEqualTo(newVote);
    }

    @Test
    void voteChanged() {
        if (LocalTime.now().isAfter(LocalTime.of(11, 0)))
            assertThrows(LateVoteException.class, () -> {
                service.vote(USER_ID, REST1_ID);
                service.vote(USER_ID, REST1_ID);
            });
    }

    @Test
    void save() {
        Vote newVote = getNewVoteByUser();
        Vote created = service.save(newVote.getUser().getId(), newVote.getRestaurant().getId());
        int newId = created.getId();
        newVote.setId(newId);
        Assertions.assertThat(created).isEqualTo(newVote);
        Assertions.assertThat(service.getByUserIdAndDate(newVote.getUser().getId(), LocalDate.now())).isEqualTo(newVote);
    }
}