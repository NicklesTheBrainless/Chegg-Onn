package menu.buttons;

import _main.panel.GamePanel;
import utils.join_code.JoinCodeManager;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.Inet4Address;

import static _main.resources.MenuResources.JOIN_BUTTON_DEFAULT_IMG;
import static _main.resources.MenuResources.JOIN_BUTTON_FAILED_IMG;
import static utils.Utils.clipboard;

public class JoinButton extends MenuButton {

    int failedJoining = 0;

    public JoinButton(GamePanel gp, BufferedImage img, int x, int y, int width, int height) {
        super(gp, img, x, y, width, height);
    }

    @Override
    public void update(double delta) {
        super.update(delta);

        if (failedJoining > 0) {

            img = JOIN_BUTTON_FAILED_IMG;
            failedJoining--;
            return;
        }

        img = JOIN_BUTTON_DEFAULT_IMG;
    }

    @Override
    void onPressed() {

        try {
            String givenJoinCode = (String) clipboard.getData(DataFlavor.stringFlavor);

            byte[] givenServerAddress = JoinCodeManager.getOriginalIP(givenJoinCode);
            Inet4Address givenServerIP = (Inet4Address) Inet4Address.getByAddress(givenServerAddress);

            short givenJoinID = JoinCodeManager.getJoinID(givenJoinCode);

            boolean joined = gp.client.tryConnecting(givenServerIP, givenJoinID);
            if (joined)
                gp.gameM.startGame();
            else
                failedJoining = 225;

        } catch (UnsupportedFlavorException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
