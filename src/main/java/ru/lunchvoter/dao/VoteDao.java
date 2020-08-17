package ru.lunchvoter.dao;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.lunchvoter.model.Restaurant;
import ru.lunchvoter.model.Vote;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface VoteDao extends JpaRepository<Vote, Integer> {
    @Override
    @Transactional
    Vote save(Vote vote);

    @EntityGraph(attributePaths = {"restaurant"}, type = EntityGraph.EntityGraphType.LOAD)
    List<Vote> findByUserId(int userId);

    @EntityGraph(attributePaths = {"restaurant"}, type = EntityGraph.EntityGraphType.LOAD)
    Vote findByUserIdAndDate(@Param("userId") int userId, @Param("date") LocalDate date);

    Vote findByUserIdAndRestaurantIdAndDate(@Param("userId") int userId, @Param("restaurantId") int restaurantId, @Param("date") LocalDate localDate);

    @EntityGraph(attributePaths = {"user"}, type = EntityGraph.EntityGraphType.LOAD)
    List<Vote> findByRestaurantId(@Param("restaurantId") int restaurantId);

    @EntityGraph(attributePaths = {"user"}, type = EntityGraph.EntityGraphType.LOAD)
    List<Vote> findByRestaurantIdAndDate(@Param("restaurantId") int restaurantId, @Param("date") LocalDate localDate);
}
