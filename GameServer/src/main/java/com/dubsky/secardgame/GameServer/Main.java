package com.dubsky.secardgame.GameServer;

import com.dubsky.secardgame.GameServer.Entity.Player;
import com.dubsky.secardgame.GameServer.Lobby.Handler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Dubsky
 * @version 1.2
 */
public class Main {

    static final int PORT = 25565;

    public static void main(String[] args) {

        System.out.println("[INFO] Server started");
        Handler handler = new Handler(5);
        int id = 1;

        ServerSocket server = null;
        Socket socket = null;

        try {
            server = new ServerSocket(PORT);
        } catch (IOException e) {
            e.printStackTrace();

        }
        while (true) {
            try {
                socket = server.accept();
            } catch (IOException e) {
                System.out.println("I/O error: " + e);
            }
            System.out.printf("[SOCKET] New socket generated (%s)\n>> Player will join Lobby %d\n", socket.getInetAddress().getHostAddress(), handler.findLobby().getId());
            new Player(id, handler.findLobby(), socket).start();
            id++;
        }

    }

}