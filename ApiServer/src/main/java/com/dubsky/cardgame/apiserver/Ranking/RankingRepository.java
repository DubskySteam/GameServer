package com.dubsky.cardgame.apiserver.Ranking;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * @author Dubsky
 * @version 1.0
 */
@Repository
public interface RankingRepository extends JpaRepository<Ranking, Integer> {

    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE gameservice.ranking SET level = level+1 WHERE id = :id ", nativeQuery = true)
    @Transactional
    void addWin(int id);
}