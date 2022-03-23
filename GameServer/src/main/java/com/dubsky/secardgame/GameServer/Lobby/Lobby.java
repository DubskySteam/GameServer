package com.dubsky.secardgame.GameServer.Lobby;

import com.dubsky.secardgame.GameServer.Entity.Player;

/**
 * @author Dubsky
 * @version 1.3
 */
public class Lobby {

    int id;
    Player p1 = null;
    Player p2 = null;
    Player turn;

    /**
     * @since v1.0
     * @param id ID of the lobby
     */
    public Lobby(int id) {
        this.id = id;
    }

    /** Returns if the lobby is filled
     * @since v1.0
     * @return boolean
     */
    public boolean isFull() {
        return p1 != null && p2 != null;
    }

    /** Checks if 2 players are connected and connections are still alive
     * @since v1.0
     */
    public void check() {
        if(p1 != null && p2 != null && p1.isAlive() && p2.isAlive()) {
            System.out.println("[Lobby "+this.id+"] Lobby is full, game will start now!");
            start();
        }
    }

    /** Disconnects a player from the lobby
     * @since v1.0
     * @param player Player to be disconnected
     */
    public void leave(Player player) {
        if(p1 == player) {
            p1 = null;
            p2.sendToClient("[Lobby "+this.id+"] A player left the lobby");
        } else {
            p2 = null;
            p1.sendToClient("[Lobby "+this.id+"] A player left the lobby");
        }
        System.out.println("[Lobby "+this.id+"] Player left the lobby");
    }

    /** Joins a player to the lobby if not full
     * @since v1.0
     * @param player Player to join the game
     * @return true or false depending on the result of the action
     */
    public boolean join(Player player) {
        if(p1 == null) {
            p1 = player;
            check();
            return true;
        } else if(p2 == null) {
            p2 = player;
            check();
            return true;
        }
        return false;
    }

    /** Changes the players turn
     * @since v1.0
     */
    public void changeTurn() {
        if (turn.equals(p1)) {
            turn = p2;
        } else {
            turn = p1;
        }
    }

    /** Starts the game
     * @since v1.0
     */
    public void start() {
        this.turn = p1;
        p1.sendToClient("full");
        p2.sendToClient("full");
        p1.sendToClient("1");
        p2.sendToClient("2");
    }

    public Player getTurn() {
        return turn;
    }

    public void setTurn(Player turn) {
        this.turn = turn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Player getP1() {
        return p1;
    }

    public void setP1(Player p1) {
        this.p1 = p1;
    }

    public Player getP2() {
        return p2;
    }

    public void setP2(Player p2) {
        this.p2 = p2;
    }

    public void sendToChat(Player player, String msg) {
        try {
            if (player == p1 && player.equals(this.turn)) {
                p2.sendToClient(msg);
                p2.sendToClient("newturn");
                changeTurn();
            } else if(player == p2 && player.equals(this.turn)) {
                p1.sendToClient(msg);
                p1.sendToClient("newturn");
                changeTurn();
            }
        } catch (Exception e) {
            System.out.println("[Lobby] Player left, socket couldn't be communicate");
        }
    }

    @Override
    public String toString() {
        return "Lobby{" +
                "id=" + id +
                ", p1=" + p1 +
                ", p2=" + p2 +
                '}';
    }
}
