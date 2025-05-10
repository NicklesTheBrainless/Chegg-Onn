package networking.utils;

import networking.communicators.GameServer;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import static _main.setting.NetworkingSettings.ACCEPT;
import static _main.setting.NetworkingSettings.ANSWER_PREFIX;
import static _main.setting._Settings.GAME_PORT;
import static networking.utils.NetworkingUtils.sendData;

public class ConnectingThread extends Thread {

    private final GameServer server;
    private final ServerSocket serverSocket;

    private boolean stopped = false;

    public ConnectingThread(GameServer server) {

        this.server = server;

        try {
            serverSocket = new ServerSocket(GAME_PORT);
            serverSocket.setSoTimeout(50);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {

        boolean validPlayerConnected = false;

        while (!validPlayerConnected && !stopped) {

            try {
                // will wait at this point until another player is connected or get stopped by a timeout
                Socket socket = serverSocket.accept();

                BufferedReader in  = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

                boolean validJoin = server.checkValidJoin(in);
                if (!validJoin) {

                    sendData(out, ANSWER_PREFIX, ACCEPT);
                    continue;
                }

                // initialize connection and start game (also stops this thread, because connection to a valid player was found)
                server.initConnection(socket, in, out);
                validPlayerConnected = true;

            } catch (Exception e) { continue; }
        }

        try {
            serverSocket.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (validPlayerConnected)
            System.out.println("GAME: Player joined!");
        if (stopped)
            System.out.println("GAME: Stopped waiting for player to join!");
    }

    public void stopConnecting() {

        stopped = true;
    }

}
