package com.dubsky.cardgame.apiserver.Ranking;

import com.dubsky.cardgame.apiserver.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @author Dubsky
 * @version 1.0
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/ranking")
public class RankingController {

    private final RankingRepository rankingRepository;

    @Autowired
    public RankingController(RankingRepository rankingRepository) {
        this.rankingRepository = rankingRepository;
    }

    /**
     * POST-Method to create a new ranking
     *
     * @param key API-Key for authentication
     * @param id id of the user
    */
    @PostMapping(path = "/newRanking")
    public void newRanking(
            @RequestParam String key,
            @RequestParam int id) {
        System.out.println("[POST] Create new ranking for User(id="+id+") >> " + id );
        rankingRepository.save(new Ranking(id));
    }

}
