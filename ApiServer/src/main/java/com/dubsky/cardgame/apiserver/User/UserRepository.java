package com.dubsky.cardgame.apiserver.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author Dubsky
 * @version 1.0
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = "SELECT * FROM gameservice.user WHERE username = :username AND password ="
                            + " :password",
            nativeQuery = true)
    User login(String username, String password);

}
