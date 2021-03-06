package com.dubsky.cardgame.apiserver.User;

import com.dubsky.cardgame.apiserver.Ranking.Ranking;
import com.dubsky.cardgame.apiserver.Ranking.RankingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author Dubsky
 * @version 1.1
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/user")
public class UserController {

    private final UserRepository userRepository;
    private final RankingRepository rankingRepository;

    @Autowired
    public UserController(UserRepository userRepository, RankingRepository rankingRepository) {
        this.userRepository = userRepository;
        this.rankingRepository = rankingRepository;
    }

    /**
     * GET-Method to verify login information
     *
     * @param key API-Key for authentication
     * @param username Username used to logging in
     * @param password Password used to logging in
     * @return User information in a JSON format
     */
    @GetMapping(path = "/login")
    public User login(
            @RequestParam String key,
            @RequestParam String username,
            @RequestParam String password) {
        System.out.format("[GET] Login attempt >> " + username);
        User tmpUser = userRepository.login(username, password);
        if (tmpUser != null
                && tmpUser.getUsername().equals(username)
                && tmpUser.getPassword().equals(password)) {
            return tmpUser;
        }
        return null;
    }

    /**
     * GET-Method to get all users
     *
     * @param key API-Key for authentication
     * @return User information in a JSON format
     */
    @GetMapping(path = "/getUsers")
    public List<User> getUsers(
            @RequestParam String key) {
        System.out.format("[GET] Get Users");
        return userRepository.findAll();
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
        System.out.println("[POST] New User >> " + username);
        userRepository.save(new User(username, password));
        User tmpuser = userRepository.findByUsername(username);
        //rankingRepository.save(new Ranking(tmpuser.getId()));
    }

    /** POST-Method to delete a user
     *
     * @param key API-Key for authentication
     * @param id User ID
     */
    @PostMapping(path = "deleteUser")
    public void deleteUser(@RequestParam String key, @RequestParam int id) {
        System.out.println("[POST] Delete User with ID >> " + id);
        Optional<User> u = userRepository.findById(id);
        u.ifPresent(userRepository::delete);
    }

    /**
     * POST-Method to a users email
     *
     * @param key API-Key for authentication
     * @param id id of the user
     * @param email Email to be changed to     */
    @PostMapping(path = "/changeEmail")
    public void changeEmail(
            @RequestParam String key,
            @RequestParam int id,
            @RequestParam String email) {
        System.out.println("[POST] Change User(id="+id+") Email to >> " + email );
        Optional<User> tmp = userRepository.findById(id);
        if(tmp.isPresent()) {
            tmp.get().setEmail(email);
            userRepository.save(tmp.get());
        }
    }

    /**
     * POST-Method to a users password
     *
     * @param key API-Key for authentication
     * @param id id of the user
     * @param password new password
     */
    @PostMapping(path = "/changePassword")
    public void changePassword(
            @RequestParam String key,
            @RequestParam int id,
            @RequestParam String password) {
        System.out.println("[POST] Change User(id="+id+") Password to >> " + password );
        Optional<User> tmp = userRepository.findById(id);
        if(tmp.isPresent()) {
            tmp.get().setPassword(password);
            userRepository.save(tmp.get());
        }
    }
}
