package game;

import _main.panel.GamePanel;
import networking.communicators.NetworkCommunicator;
import utils.GameObject;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

import static _main.setting.NetworkingSettings.CHAT_PREFIX;

public class GameManager implements GameObject {

    GamePanel gp;

    BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
    PrintStream    consoleWriter = System.out;

    NetworkCommunicator communicator;


    public GameManager(GamePanel gp) {
        this.gp = gp;
    }

    public void startGame() {

        gp.inGame = true;
        this.communicator = gp.communicator;
    }



    @Override
    public void update(double delta) {

        try {
            System.out.print("YOU: ");
            String enteredMessage = consoleReader.readLine();
            if (enteredMessage != null)
                sendMessage(enteredMessage);

            String receivedData = communicator.readData();
            if (receivedData != null)
                printReceivedMessage(receivedData);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void draw(Graphics2D g2) {

    }



    private void sendMessage(String enteredMessage) {

        communicator.sendData(CHAT_PREFIX, enteredMessage);
    }

    private void printReceivedMessage(String receivedData) {

        if (!receivedData.startsWith(CHAT_PREFIX))
            return;

        String receivedMessage = receivedData.substring(CHAT_PREFIX.length());
        consoleWriter.println("OTHER PLAYER: " + receivedMessage);
    }
}
