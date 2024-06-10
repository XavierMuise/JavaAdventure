import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class UI {

    Panel gp;
    Font Arial_40;
    BufferedImage keyImg;

    public boolean messageOn;
    public String message = "";
    public int msgCount = 0;

    public UI(Panel gp){
        this.gp = gp;
        this.Arial_40 = new Font("Arial", Font.PLAIN, 40);
        OBJ_Key k = new OBJ_Key();
        keyImg = k.img;
    }

    public void showMessage(String msg){
        message = msg;
        messageOn = true;
    }

    public void draw(Graphics2D g2){
        g2.setFont(Arial_40);
        g2.setColor(Color.white);
        g2.drawImage(keyImg, gp.TileSize/2, gp.TileSize/2, gp.TileSize, gp.TileSize, null );
        g2.drawString("x " + gp.player.hasKey, 74, 65);

        if(messageOn){
            g2.setFont(g2.getFont().deriveFont(30F));
            g2.drawString(message, gp.TileSize/2, gp.TileSize * 5);
            msgCount++;
            if(msgCount > 120 ){
                msgCount = 0;
                messageOn = false;
            }
        }
    }
}
