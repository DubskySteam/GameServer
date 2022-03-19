package com.dubsky.cardgame.apiserver.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Dubsky
 * @version 1.0
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
