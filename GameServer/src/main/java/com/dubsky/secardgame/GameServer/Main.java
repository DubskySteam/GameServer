package com.dubsky.secardgame.GameServer;

import com.dubsky.secardgame.GameServer.Entity.Player;
import com.dubsky.secardgame.GameServer.Lobby.Handler;
import com.dubsky.secardgame.GameServer.Lobby.Lobby;

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
        //Lobby lobby = new Lobby(0);
        int id = 1;

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
            System.out.printf("[SOCKET] New socket generated (%s)\n>> Player will join Lobby %d\n", socket.getInetAddress().getHostAddress(), handler.findLobby().getId());
            new Player(id, handler.findLobby(), socket).start();
            id++;
        }

    }

}