package ru.lunchvoter;

import org.springframework.web.bind.UnsatisfiedServletRequestParameterException;
import ru.lunchvoter.model.Role;
import ru.lunchvoter.model.User;

import java.time.LocalDate;
import java.util.Arrays;

import static java.time.LocalDateTime.now;
import static java.time.LocalDateTime.of;
import static ru.lunchvoter.model.AbstractBaseEntity.START_SEQ;
import static java.time.LocalDate.of;
import static ru.lunchvoter.RestaurantTestData.*;

/*
100000,User,user@gmail.com,password,2020-08-04 20:17:10.040000,true
100001,Admin,admin@gmail.com,admin,2020-08-04 20:17:10.040000,true
 */
public class UserTestData {
}
