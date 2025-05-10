package menu.buttons;

import _main.panel.GamePanel;
import utils.join_code.JoinCodeManager;

import java.awt.datatransfer.StringSelection;
import java.awt.image.BufferedImage;

import static utils.Utils.clipboard;
import static utils.Utils.random;

public class CopyButton extends MenuButton {

    public CopyButton(GamePanel gp, BufferedImage img, int x, int y, int width, int height) {
        super(gp, img, x, y, width, height);
    }

    @Override
    void onPressed() {

        short joinID = (short) random.nextInt();
        String joinCode = JoinCodeManager.getJoinCode(joinID);

        gp.currentJoinID = joinID;

        StringSelection joinCodeStringData = new StringSelection(joinCode);
        clipboard.setContents(joinCodeStringData, null);
    }
}
