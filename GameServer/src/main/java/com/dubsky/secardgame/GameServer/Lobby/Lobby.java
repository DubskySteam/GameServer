package com.dubsky.secardgame.GameServer.Lobby;

import com.dubsky.secardgame.GameServer.Entity.Player;

public class Lobby {

    int id;
    Player p1 = null;
    Player p2 = null;
    Player turn;

    public Lobby(int id) {
        this.id = id;
        System.out.println("[INFO] com.dubsky.secardgame.GameServer.Lobby opened on ID " + this.id);
    }

    public void check() {
        if(p1 != null && p2 != null && p1.isAlive() && p2.isAlive()) {
            System.out.println("[com.dubsky.secardgame.GameServer.Lobby] com.dubsky.secardgame.GameServer.Lobby is full, game will start now!");
            start();
        }
    }

    public void leave(Player player) {
        if(p1 == player) {
            p1 = null;
            p2.sendToClient("[Server] A player left the lobby");
        } else {
            p2 = null;
            p1.sendToClient("[Server] A player left the lobby");
        }
        System.out.println("[com.dubsky.secardgame.GameServer.Lobby] Player left the lobby");
    }

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

    public void changeTurn() {
        if (turn.equals(p1)) {
            turn = p2;
        } else {
            turn  = p1;
        }
    }

    public void start() {
        this.turn = p1;
        p1.sendToClient("[Server] com.dubsky.secardgame.GameServer.Lobby is full, game will start now!");
        p2.sendToClient("[Server] com.dubsky.secardgame.GameServer.Lobby is full, game will start now!");
        System.out.println("[com.dubsky.secardgame.GameServer.Lobby] Send Info to Player 1");
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
        if (p1 == player) {p2.sendToClient("[P1]" + msg);} else {p1.sendToClient("[P2]" + msg);}
    }

    @Override
    public String toString() {
        return "com.dubsky.secardgame.GameServer.Lobby{" +
                "id=" + id +
                ", p1=" + p1 +
                ", p2=" + p2 +
                '}';
    }
}
