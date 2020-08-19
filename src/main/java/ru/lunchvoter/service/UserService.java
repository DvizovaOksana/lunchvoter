package ru.lunchvoter.service;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.lunchvoter.AuthorizedUser;
import ru.lunchvoter.dao.UserDao;
import ru.lunchvoter.model.User;
import ru.lunchvoter.to.UserTo;
import ru.lunchvoter.util.UserUtil;

import java.util.List;

import static ru.lunchvoter.util.ValidationUtil.checkNotFoundWithId;
import static ru.lunchvoter.util.ValidationUtil.checkNotFound;

@Service("userService")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserService implements UserDetailsService {
    private final UserDao dao;

    public UserService(UserDao dao) {
        this.dao = dao;
    }

    public User create(User user) {
        Assert.notNull(user, "user must not be null");
        return dao.save(user);
    }

    public void delete(int id) {
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
        dao.save(user);
    }

    @Transactional
    public void update(UserTo userTo) {
        Assert.notNull(userTo, "user must not be null");
        User user = get(userTo.getId());
        User updatedUser = UserUtil.updateFromTo(user, userTo);
        dao.save(updatedUser);
    }

    @Transactional
    public void enable(int id, boolean enabled) {
        User user = get(id);
        user.setEnabled(enabled);
        dao.save(user);
    }

    @Override
    public AuthorizedUser loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = dao.getByEmail(email.toLowerCase());
        if (user == null) {
            throw new UsernameNotFoundException("User " + email + " is not found");
        }
        return new AuthorizedUser(user);
    }
}
