package com.dubsky.secardgame.GameServer;

import com.dubsky.secardgame.GameServer.Entity.Player;
import com.dubsky.secardgame.GameServer.Lobby.Lobby;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Dubsky
 * @version 1.1
 */
public class Main {

    static final int PORT = 25565;

    public static void main(String[] args) {

        System.out.println("[INFO] Server started");
        Lobby lobby = new Lobby(0);
        int id = 0;

        ServerSocket serverSocket = null;
        Socket socket = null;

        try {
            serverSocket = new ServerSocket(PORT);
        } catch (IOException e) {
            e.printStackTrace();

        }
        while (true) {
            try {
                socket = serverSocket.accept();
            } catch (IOException e) {
                System.out.println("I/O error: " + e);
            }
            System.out.printf("[SOCKET] New socket generated (%s)\n>> Player will join com.dubsky.secardgame.GameServer.Lobby %d\n", socket.getInetAddress().getHostAddress(), lobby.getId());
            new Player(id, lobby, socket).start();
            id++;
        }

    }

}