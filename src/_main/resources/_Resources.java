package _main.resources;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;

import static utils.Utils.readImage;
import static utils.Utils.readTextFile;

public class _Resources {

    public static final BufferedImage ICON_IMG = readImage("/icon.png");



    // look-up-table for the encryption codes, for each day of the year
    public static final BufferedReader KEY_LIST = readTextFile("/data/key_list");
}
