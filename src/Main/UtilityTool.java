import java.awt.*;
import java.awt.image.BufferedImage;

public class UtilityTool {

    public BufferedImage scaleImg(BufferedImage original, int width, int height){
        BufferedImage ScaledImage = new BufferedImage(width, height, original.getType());
        Graphics2D g2  = ScaledImage.createGraphics();
        g2.drawImage(original, 0, 0, width, height, null);
        return ScaledImage;
    }
}
