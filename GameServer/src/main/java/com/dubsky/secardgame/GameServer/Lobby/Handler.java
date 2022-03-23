package com.dubsky.secardgame.GameServer.Lobby;

import java.util.ArrayList;

/**
 * @author Dubsky
 * @version 1.0
 */
public final class Handler {

    int max_lobbies;
    ArrayList<Lobby> lobbies = new ArrayList<>();

    /**
     * Constructor
     *
     * @param lim The amount of lobbies the handler can handle
     * @since v1.0
     */
    public Handler(int lim) {
        this.max_lobbies = lim;
        init();
    }

    /**
     * Initializes the lobbies
     *
     * @since v1.0
     */
    public void init() {
        for (int i = 0; lobbies.size() < max_lobbies; i++) {
            lobbies.add(new Lobby(i));
            System.out.println("[HANDLER] new Lobby [id: " + i + "]");
        }
    }

    /**
     * Finds a free lobby
     *
     * @return A free lobby
     * @since v1.0
     */
    public Lobby findLobby() {
        for (Lobby l : lobbies) {
            if (!l.isFull()) {
                return l;
            }
        }
        return null;
    }

}
