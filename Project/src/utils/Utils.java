package utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.Random;

public class Utils {

    public static final Random random = new Random();

    public static final Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();



    public static BufferedImage readImage(String filePath) {

        try {
            return ImageIO.read(Objects.requireNonNull(Utils.class.getResourceAsStream(filePath)));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static BufferedReader readTextFile(String filePath) {

        return new BufferedReader(new InputStreamReader(Objects.requireNonNull(Utils.class.getResourceAsStream(filePath))));
    }
}
