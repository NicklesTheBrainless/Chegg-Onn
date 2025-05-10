package networking.communicators;

import _main.panel.GamePanel;
import networking.utils.NetworkingUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.Socket;

public abstract class NetworkCommunicator {

    GamePanel gp;

    Socket socket;

    BufferedReader in;
    BufferedWriter out;

    public NetworkCommunicator(GamePanel gp) {
        this.gp = gp;
    }



    public void sendData(String prefix, String data) {
        NetworkingUtils.sendData(out, prefix, data);
    }

    public String readData() {

        try {
            return in.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String waitForData() {

        try {
            return NetworkingUtils.waitForData(in);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    abstract void initConnection(Socket socket, BufferedReader in, BufferedWriter out);
}
