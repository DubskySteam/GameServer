package com.dubsky.cardgame.apiserver.User;

import com.sun.istack.NotNull;
import lombok.Data;
import org.springframework.data.annotation.Id;

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
@Table(name="user")
public class User {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String username;
    @NotNull
    private String password;
    @NotNull
    private String email;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
