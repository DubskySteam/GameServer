package com.dubsky.cardgame.apiserver.Ranking;

import lombok.Data;

import javax.persistence.*;

/**
 * @author Dubsky
 * @version 1.0
 */
@Data
@Entity
@Table(name="ranking")
public class Ranking {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    @JoinColumn(name = "id", referencedColumnName = "id")
    private int id;

    private int level;
    private String rank;

    public Ranking() {}

    public Ranking(int id) {
        this.id = id;
    }

    public Ranking(int id, int level, String rank) {
        this.id = id;
        this.level = level;
        this.rank = rank;
    }
}
