package _main.resources;

import java.awt.image.BufferedImage;

import static utils.Utils.readImage;

public abstract class MenuResources {

    public static final BufferedImage MENU_BG_IMG = readImage("/menu/menu_bg.png");

    public static final BufferedImage COPY_BUTTON_IMG = readImage("/menu/copy_button/default.png");

    public static final BufferedImage JOIN_BUTTON_DEFAULT_IMG = readImage("/menu/join_button/default.png");
    public static final BufferedImage JOIN_BUTTON_FAILED_IMG  = readImage("/menu/join_button/failed.png");

    public static final BufferedImage OPEN_BUTTON_ON_IMG  = readImage("/menu/open_button/on.png");
    public static final BufferedImage OPEN_BUTTON_OFF_IMG = readImage("/menu/open_button/off.png");
}
