package main;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Utility {

    public static BufferedImage scaleImage(BufferedImage original, int width, int height) {
        BufferedImage scaledImage = new BufferedImage(width, height, original.getType());
        Graphics2D g2d = scaledImage.createGraphics();
        g2d.drawImage(original, 0, 0, width, height, null);
        return scaledImage;
    }

    public static int[] getStringCenterPosition(GamePanel gp, Graphics2D g2d, String msg) {
        int stringLength = (int) g2d.getFontMetrics().getStringBounds(msg, g2d).getWidth();
        int stringHeight = (int) g2d.getFontMetrics().getStringBounds(msg, g2d).getHeight();
        int x = gp.windowX / 2 - stringLength / 2;
        int y = gp.windowY / 2 + stringHeight / 2;
        return new int[]{x, y};
    }
}
