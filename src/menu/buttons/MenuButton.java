package menu.buttons;

import _main.listeners.MouseHandler;
import _main.panel.GamePanel;
import menu.MenuManager;
import utils.GameObject;

import java.awt.*;
import java.awt.image.BufferedImage;

import static _main.setting._Settings.BUTTON_HOVER_GROWTH;

public abstract class MenuButton implements GameObject {

    GamePanel gp;
    MouseHandler mouse;

    MenuManager menu;

    BufferedImage img;
    Rectangle box;

    boolean hovered = false;

    public MenuButton(GamePanel gp, BufferedImage img, int x, int y, int width, int height) {

        this.gp = gp;
        this.mouse = gp.mouseH;

        this.menu = gp.menuM;

        this.img = img;
        this.box = new Rectangle(x, y, width, height);
    }



    @Override
    public void update(double delta) {

        hovered = box.contains(mouse.pos);

        if (!hovered)
            return;

        if (mouse.leftJustPressed)
            onPressed();
    }

    @Override
    public void draw(Graphics2D g2) {

        if (hovered) {

            int hoverX = box.x - BUTTON_HOVER_GROWTH;
            int hoverY = box.y - BUTTON_HOVER_GROWTH;

            int hoverWidth  = box.width + (BUTTON_HOVER_GROWTH * 2);
            int hoverHeight = box.height + (BUTTON_HOVER_GROWTH * 2);

            g2.drawImage(img, hoverX, hoverY, hoverWidth, hoverHeight, null);
            return;
        }

        g2.drawImage(img, box.x, box.y, box.width, box.height, null);
    }

    abstract void onPressed();
}
