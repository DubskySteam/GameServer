package com.dubsky.cardgame.apiserver.Ranking;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

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
    private int id;

    @NotNull
    private int level;
    @NotNull
    private String rank;

    public Ranking() {}

    public Ranking(int id, int level, String rank) {
        this.id = id;
        this.level = level;
        this.rank = rank;
    }
}
