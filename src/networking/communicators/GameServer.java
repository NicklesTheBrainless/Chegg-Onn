package networking.communicators;

import _main.panel.GamePanel;
import networking.utils.ConnectingThread;

import java.io.*;
import java.net.Socket;

import static _main.setting.NetworkingSettings.ANSWER_PREFIX;
import static _main.setting.NetworkingSettings.ACCEPT;
import static networking.utils.NetworkingUtils.checkJoinID;

public class GameServer extends NetworkCommunicator {

    ConnectingThread connectingThread;

    public GameServer(GamePanel gp) {
        super(gp);
    }



    public void startConnectionWaiting() {

        // creates a thread that waits for a connection
        // if another player connects, it initializes the socket and starts the game
        connectingThread = new ConnectingThread(this);
        connectingThread.start();
    }

    public void stopConnectionWaiting() {

        connectingThread.stopConnecting();
        connectingThread = null;
    }



    public boolean checkValidJoin(BufferedReader joinIn) throws IOException {

        if (gp.inGame)
            throw new RuntimeException("Player joined host, while host is in game!");

        return checkJoinID(joinIn, gp.currentJoinID);
    }

    public void initConnection(Socket socket, BufferedReader in, BufferedWriter out) {

        this.socket = socket;
        this.in  = in;
        this.out = out;
        connectingThread = null;

        // tells the other player, that the join was successful
        sendData(ANSWER_PREFIX, ACCEPT);

        gp.communicator = this;
        gp.gameM.startGame();
    }

}
