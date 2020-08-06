package ru.lunchvoter.service;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.lunchvoter.dao.RestaurantDao;
import ru.lunchvoter.dao.UserDao;
import ru.lunchvoter.dao.VoteDao;
import ru.lunchvoter.model.Vote;
import ru.lunchvoter.util.exception.LateForVoteException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static ru.lunchvoter.util.ValidationUtil.checkNotFoundWithId;

@Service
public class VoteService {
    private final VoteDao dao;
    private final UserDao userDao;
    private final RestaurantDao restaurantDao;

    public VoteService(VoteDao dao, UserDao userDao, RestaurantDao restaurantDao) {
        this.dao = dao;
        this.userDao = userDao;
        this.restaurantDao = restaurantDao;
    }

    public List<Vote> getVotesByUser(int userId){
        return checkNotFoundWithId(dao.findByUserId(userId), userId);
    }

    public Vote findByUserIdAndDate(int userId, LocalDate date){
        Assert.notNull(date, "date must not be null");
        return checkNotFoundWithId(dao.findByUserIdAndDate(userId, date), userId);
    }

    public Vote findByUserIdAndRestaurantAndDate(int userId, int restaurantId, LocalDate date){
        Assert.notNull(date, "date must not be null");
        return checkNotFoundWithId(dao.findByUserIdAndRestaurantAndDate(userId, restaurantId, date), userId);
    }

    public List<Vote> getVotesByRestaurantAndDate(int restaurantId, LocalDate date){
        Assert.notNull(date, "date must not be null");
        return checkNotFoundWithId(dao.findByRestaurantAndDate(restaurantId, date), restaurantId);
    }

    public void vote(int userId, int restaurantId){
        Vote vote = dao.findByUserIdAndDate(userId, LocalDate.now());
        if ( vote == null )
            save(userId, restaurantId);
        else {
            if (LocalTime.now().isAfter(LocalTime.of(11, 0)))
            throw new LateForVoteException(LocalTime.of(11, 0));
            create(vote, userId, restaurantId);
        }
    }

    public Vote save(int userId, int restaurantId) {
        Vote vote = new Vote();
        vote.setRestaurant(restaurantDao.getOne(restaurantId));
        vote.setUser(userDao.getOne(userId));
        return dao.save(vote);
    }

    public void create(Vote vote, int userId, int restaurantId) {
        vote.setRestaurant(restaurantDao.getOne(restaurantId));
        vote.setUser(userDao.getOne(userId));
        checkNotFoundWithId(dao.save(vote), vote.getId());
    }

    public void delete(Vote vote, int userId, int restaurantId) {
        vote.setRestaurant(restaurantDao.getOne(restaurantId));
        vote.setUser(userDao.getOne(userId));
        checkNotFoundWithId(dao.save(vote), vote.getId());
    }
}
