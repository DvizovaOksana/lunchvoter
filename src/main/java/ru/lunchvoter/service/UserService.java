package ru.lunchvoter.service;

import org.springframework.stereotype.Service;
import ru.lunchvoter.dao.UserDao;

@Service
public class UserService {
    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }
}
