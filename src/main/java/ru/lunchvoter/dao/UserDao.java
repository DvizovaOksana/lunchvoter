package ru.lunchvoter.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.lunchvoter.model.User;

@Transactional(readOnly = true)
public interface UserDao extends JpaRepository<User, Integer> {
}
