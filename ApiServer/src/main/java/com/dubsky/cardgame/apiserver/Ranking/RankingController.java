package com.dubsky.cardgame.apiserver.Ranking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
