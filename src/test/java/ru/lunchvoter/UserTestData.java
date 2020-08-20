package ru.lunchvoter;

import ru.lunchvoter.model.Role;
import ru.lunchvoter.model.User;
import ru.lunchvoter.util.json.JsonUtil;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.time.LocalDateTime.now;
import static java.time.LocalDateTime.of;
import static ru.lunchvoter.model.AbstractBaseEntity.START_SEQ;
import static java.time.LocalDate.of;

/*
100000,User,user@gmail.com,password,2020-08-04 20:17:10.040000,true
100001,Admin,admin@gmail.com,admin,2020-08-04 20:17:10.040000,true
 */
public class UserTestData {
    public static TestMatcher<User> USER_MATCHER = TestMatcher.usingFieldsWithIgnoringComparator(User.class, "registered", "votes", "password");

    public static final int NOT_FOUND = 10;
    public static final int USER_ID = START_SEQ;
    public static final int ADMIN_ID = START_SEQ + 1;

    public static final User USER = new User(USER_ID, "User", "user@gmail.com", "password", Role.ROLE_USER);
    public static final User ADMIN = new User(ADMIN_ID, "Admin", "admin@gmail.com", "admin", Role.ROLE_ADMIN, Role.ROLE_USER);

    public static final List<User> USERS = Arrays.asList(USER, ADMIN);

    public static User getNew() {
        return new User(null, "NewUser", "new@gmail.com", "newPassword", Role.ROLE_USER);
    }

    public static User getUpdated() {
        User updated = new User(USER);
        updated.setName("UpdatedName");
        updated.setRoles(Collections.singletonList(Role.ROLE_ADMIN));
        return updated;
    }

    public static User getDuplicate(){
        return new User(null, "Duplicate", "user@gmail.com", "newPass", Role.ROLE_USER);
    }

    public static String jsonWithPassword(User user, String passw) {
        return JsonUtil.writeAdditionProps(user, "password", passw);
    }
}
