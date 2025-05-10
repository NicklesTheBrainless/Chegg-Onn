package _main.panel;

import _main.listeners.KeyHandler;
import _main.listeners.MouseHandler;
import game.GameManager;
import menu.MenuManager;
import networking.communicators.GameClient;
import networking.communicators.GameServer;
import networking.communicators.NetworkCommunicator;

import java.awt.*;

import static _main.setting._Settings.*;
import static utils.Utils.random;

public class GamePanel extends BasePanel {

    // input listeners
    public KeyHandler   keyH   = new KeyHandler();
    public MouseHandler mouseH = new MouseHandler();

    public MenuManager menuM = new MenuManager(this);
    public GameManager gameM = new GameManager(this);

    public GameServer server = new GameServer(this);
    public GameClient client = new GameClient(this);

    public NetworkCommunicator communicator;

    public boolean inGame = false;
    public boolean open = false;
    public short currentJoinID = (short) random.nextInt();

    public GamePanel() {

        super(SETTING_MAX_FPS);

        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(new Color(70, 90, 110));

        this.setDoubleBuffered(true);
        this.setFocusable(true);

        this.addKeyListener(keyH);

        this.addMouseListener(mouseH);
        this.addMouseMotionListener(mouseH);
        this.addMouseWheelListener(mouseH);
    }



    @Override
    protected void update(double delta) {

        if (!inGame)
            menuM.update(delta);
        else
            gameM.update(delta);

        mouseH.update();
    }

    @Override
    protected void draw(Graphics2D g2) {

        if (!inGame)
            menuM.draw(g2);
        else
            gameM.draw(g2);
    }

    @Override
    protected void onSecond(int currentFPS) {

        //System.out.println(currentFPS);
    }
}

