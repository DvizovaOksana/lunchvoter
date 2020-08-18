package ru.lunchvoter.util;

import ru.lunchvoter.model.Role;
import ru.lunchvoter.model.User;
import ru.lunchvoter.to.UserTo;

public class UserUtil {

    public static User createNewFromTo(UserTo userTo) {
        return new User(null, userTo.getName(), userTo.getEmail().toLowerCase(), userTo.getPassword(), Role.ROLE_USER);
    }

}
