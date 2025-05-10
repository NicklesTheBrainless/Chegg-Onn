package menu;

import _main.panel.GamePanel;
import menu.buttons.CopyButton;
import menu.buttons.JoinButton;
import menu.buttons.OpenButton;
import utils.GameObject;

import java.awt.*;

import static _main.resources.MenuResources.*;
import static _main.setting._Settings.*;

public class MenuManager implements GameObject {

    GamePanel gp;

    OpenButton openButton;

    CopyButton copyButton;
    JoinButton joinButton;

    public MenuManager(GamePanel gp) {

        this.gp = gp;
        this.openButton = new OpenButton(gp, OPEN_BUTTON_OFF_IMG, 69 * PIXEL_SIZE, 24 * PIXEL_SIZE, 21 * PIXEL_SIZE, 9 * PIXEL_SIZE);
        this.copyButton = new CopyButton(gp, COPY_BUTTON_IMG, 6 * PIXEL_SIZE, 24 * PIXEL_SIZE, 59 * PIXEL_SIZE, 9 * PIXEL_SIZE);
        this.joinButton = new JoinButton(gp, JOIN_BUTTON_DEFAULT_IMG, 6 * PIXEL_SIZE, 36 * PIXEL_SIZE, 84 * PIXEL_SIZE, 9 * PIXEL_SIZE);
    }



    @Override
    public void update(double delta) {

        openButton.update(delta);
        copyButton.update(delta);
        joinButton.update(delta);
    }

    @Override
    public void draw(Graphics2D g2) {

        g2.drawImage(MENU_BG_IMG, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT, null);

        openButton.draw(g2);
        copyButton.draw(g2);
        joinButton.draw(g2);
    }
}
