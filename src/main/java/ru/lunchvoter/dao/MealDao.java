package ru.lunchvoter.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.lunchvoter.model.Meal;

@Transactional(readOnly = true)
public interface MealDao extends JpaRepository<Meal, Integer> {
}
