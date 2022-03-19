package com.dubsky.cardgame.apiserver.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * @author Dubsky
 * @version 1.0
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/user")
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * POST-Method to create a new user
     *
     * @param key API-Key for authentication
     * @param username Username for the account to be created
     * @param password Password for the account to be created
     */
    @PostMapping(path = "/newUser")
    public void newUser(
            @RequestParam String key,
            @RequestParam String username,
            @RequestParam String password) {
        System.out.println("[POST] New User: " + username);
        userRepository.save(new User(username, password));
    }
}
