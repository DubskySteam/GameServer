package com.dubsky.secardgame.GameServer.Entity;

import com.dubsky.secardgame.GameServer.Lobby.Lobby;

import java.io.*;
import java.net.Socket;

/**
 * @author Dubsky
 * @version 1.1
 */
public final class Player extends Thread {

    private final int id;
    private String uname;
    private final Socket connection;
    private final Lobby lobby;
    private PrintWriter writer = null;

    public Player(int id, Lobby lobby, Socket socket) {
        this.id = id;
        this.lobby = lobby;
        this.connection = socket;
        try {
            this.writer = new PrintWriter(connection.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        if (!this.lobby.join(this)) {
            System.out.println("[INFO] Access denied for Player " + this.id + " -- Lobby was full");
            try {
                connection.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            } catch (IOException e) {
                return;
            }
            String inp;
            while (true) {
                try {
                    inp = reader.readLine();
                    if ((inp == null) || inp.equalsIgnoreCase("over")) {
                        System.out.println("[SOCKET] Socket disconnected [" + connection.getInetAddress().getHostAddress() + "]");
                        lobby.leave(this);
                        connection.close();
                        return;
                    } else {
                        System.out.println("Player " + id + " >> " + inp + "\n\r");
                        lobby.sendToChat(this, inp);
                    }
                } catch (IOException e) {
                    System.out.println("[SOCKET] Socket disconnected [" + connection.getInetAddress().getHostAddress() + "]");
                    lobby.leave(this);
                    return;
                }
            }
        }
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public void sendToClient(String msg) {
        writer.println(msg);
        writer.flush();
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", uname='" + uname + '\'' +
                ", connection=" + connection +
                '}';
    }
}
