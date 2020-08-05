package ru.lunchvoter.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.lunchvoter.dao.UserDao;
import ru.lunchvoter.model.User;

import java.util.List;

import static ru.lunchvoter.util.ValidationUtil.checkNotFoundWithId;
import static ru.lunchvoter.util.ValidationUtil.checkNotFound;

@Service
public class UserService {
    private final UserDao dao;

    public UserService(UserDao dao) {
        this.dao = dao;
    }

    public User create(User user) {
        Assert.notNull(user, "user must not be null");
        return dao.save(user);
    }

    public void delete(int id){
        checkNotFoundWithId(dao.delete(id) != 0, id);
    }

    public User get(int id) {
        return checkNotFoundWithId(dao.findById(id).orElse(null), id);
    }

    public User getByEmail(String email) {
        Assert.notNull(email, "email must not be null");
        return checkNotFound(dao.getByEmail(email), "email=" + email);
    }

    public List<User> getAll() {
        return dao.findAll();
    }

    public void update(User user) {
        Assert.notNull(user, "user must not be null");
        checkNotFoundWithId(dao.save(user), user.getId());
    }
}
