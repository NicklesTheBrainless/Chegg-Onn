package networking.communicators;

import _main.panel.GamePanel;
import networking.utils.NetworkingUtils;

import java.io.*;
import java.net.Inet4Address;
import java.net.Socket;

import static _main.setting.NetworkingSettings.*;
import static _main.setting._Settings.GAME_PORT;
import static networking.utils.NetworkingUtils.checkJoinAccepted;

public class GameClient extends NetworkCommunicator {

    public GameClient(GamePanel gp) {
        super(gp);
    }



    public boolean tryConnecting(Inet4Address serverIP, short joinID) {

        try {
            Socket socket = new Socket(serverIP, GAME_PORT);

            BufferedReader in  = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            String joinIdString = Short.toString(joinID);
            NetworkingUtils.sendData(out, JOIN_PREFIX, joinIdString);

            boolean accepted = checkJoinAccepted(in);
            if (!accepted)
                return false;

            initConnection(socket, in, out);
            return true;

        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public void initConnection(Socket socket, BufferedReader in, BufferedWriter out) {

        this.socket = socket;
        this.in = in;
        this.out = out;

        gp.communicator = this;
        gp.gameM.startGame();
    }
}
