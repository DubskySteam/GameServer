package com.dubsky.cardgame.apiserver.Ranking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
     * @param id  id of the user
     */
    @PostMapping(path = "/newRanking")
    public void newRanking(
            @RequestParam String key,
            @RequestParam int id) {
        System.out.println("[POST] Create new ranking for User(id=" + id + ") >> " + id);
        rankingRepository.save(new Ranking(id));
    }

    /**
     * POST-Method to create a new ranking
     *
     * @param key API-Key for authentication
     * @param id  id of the user
     */
    @PostMapping(path = "/wonGame")
    public void wonGame(@RequestParam String key,
                        @RequestParam int id) {
        System.out.println("[POST] Added win to (id=" + id + ") >> " + id);
        rankingRepository.addWin(id);
    }

    /**
     * GET-Method to get a users level
     *
     * @return User information in a JSON format
     */
    @GetMapping(path = "/getLevel")
    public int getLevel(@RequestParam int id) {
        System.out.format("[GET] Get user level >> " + id);
        Optional<Ranking> tmp = rankingRepository.findById(id);
        return tmp.map(Ranking::getLevel).orElse(-1);
    }

    /**
     * GET-Method to get a users level
     *
     * @return User information in a JSON format
     */
    @GetMapping(path = "/getRank")
    public String getRank(@RequestParam int id) {
        System.out.format("[GET] Get user ranking >> " + id);
        Optional<Ranking> tmp = rankingRepository.findById(id);
        return tmp.map(Ranking::getRank).orElse("");
    }

    /**
     * GET-Method to get a users level
     *
     * @return User information in a JSON format
     */
    @GetMapping(path = "/getTopTen")
    public List<Ranking> getTopTen() {
        System.out.format("[GET] Get Top Ten rankings");
        return rankingRepository.findAll(Sort.by(Sort.Direction.DESC, "level"));
    }

}
