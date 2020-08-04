package ru.lunchvoter.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.lunchvoter.model.Meal;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface MealDao extends JpaRepository<Meal, Integer> {
    @Modifying
    @Transactional
    @Query("DELETE FROM Meal m WHERE m.id=:id AND m.restaurant.id=:restaurantId")
    int delete(@Param("id") int id, @Param("restaurantId") int restaurantId);

    @Query("SELECT m FROM Meal m WHERE m.restaurant.id=:restaurantId AND m.date=:date ORDER BY m.date DESC")
    List<Meal> getAll(@Param("restaurantId") int restaurantId, @Param("date") LocalDate date);

    @Override
    @Transactional
    Meal save(Meal meal);
}
