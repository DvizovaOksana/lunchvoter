package ru.lunchvoter;

import ru.lunchvoter.model.Vote;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static ru.lunchvoter.model.AbstractBaseEntity.START_SEQ;
import static ru.lunchvoter.RestaurantTestData.*;
import static ru.lunchvoter.UserTestData.*;

public class VoteTestData {
    public static final TestMatcher<Vote> VOTE_MATCHER = TestMatcher.usingFieldsWithIgnoringComparator(Vote.class, "user");

    public static final int VOTE1_ID = START_SEQ + 14;

    public static final LocalDate DATE1 = LocalDate.of(2020,8,4);
    public static final LocalDate DATE2 = LocalDate.of(2020,8,5);

    public static final Vote VOTE1_USER = new Vote(VOTE1_ID, USER, RESTAURANT1, DATE1);
    public static final Vote VOTE2_USER = new Vote(VOTE1_ID+2, USER, RESTAURANT2, DATE2);

    public static final Vote VOTE1_ADMIN = new Vote(VOTE1_ID+1, ADMIN, RESTAURANT2, DATE1);
    public static final Vote VOTE2_ADMIN = new Vote(VOTE1_ID+3, ADMIN, RESTAURANT2, DATE2);

    public static final List<Vote> VOTES_USER = List.of(VOTE1_USER, VOTE2_USER);
    public static final List<Vote> VOTES_REST2_DATE2 = List.of(VOTE2_USER, VOTE2_ADMIN);

    public static Vote getNewVoteByUser(){
        return new Vote(null, USER, RESTAURANT1, LocalDate.now());
    }
}
