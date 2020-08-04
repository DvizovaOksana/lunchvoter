package ru.lunchvoter.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.lunchvoter.model.Vote;

@Transactional(readOnly = true)
public interface VoteDao extends JpaRepository<Vote, Integer> {
}
