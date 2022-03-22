package com.dubsky.cardgame.apiserver.Ranking;

import com.dubsky.cardgame.apiserver.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Dubsky
 * @version 1.0
 */
@Repository
public interface RankingRepository extends JpaRepository<User, Integer> {
}