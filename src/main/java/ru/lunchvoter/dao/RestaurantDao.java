package ru.lunchvoter.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.lunchvoter.model.Restaurant;

@Transactional(readOnly = true)
public interface RestaurantDao extends JpaRepository<Restaurant, Integer> {
}