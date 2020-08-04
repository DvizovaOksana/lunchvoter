package ru.lunchvoter.service;

import org.springframework.stereotype.Service;
import ru.lunchvoter.dao.RestaurantDao;
import ru.lunchvoter.dao.UserDao;
import ru.lunchvoter.dao.VoteDao;

@Service
public class VoteService {
    private final VoteDao voteDao;
    private final UserDao userDao;
    private final RestaurantDao restaurantDao;

    public VoteService(VoteDao voteDao, UserDao userDao, RestaurantDao restaurantDao) {
        this.voteDao = voteDao;
        this.userDao = userDao;
        this.restaurantDao = restaurantDao;
    }
}
