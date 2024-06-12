import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UI {

    Panel gp;
    Font Arial_40, Arial_80B;
    BufferedImage keyImg;

    public boolean messageOn;
    public String message = "";
    public int msgCount = 0;
    public boolean gameFinished = false;
    public double playTime;
    public DecimalFormat dFormat = new DecimalFormat("#0.00");

    public UI(Panel gp){
        this.gp = gp;
        this.Arial_40 = new Font("Arial", Font.PLAIN, 40);
        this.Arial_80B = new Font("Arial", Font.BOLD, 80);
        OBJ_Key k = new OBJ_Key(gp);
        keyImg = k.img;
    }

    public void showMessage(String msg){
        message = msg;
        messageOn = true;
    }

    public void draw(Graphics2D g2){

        if(gameFinished){



            g2.setFont(Arial_80B);
            g2.setColor(Color.YELLOW);
            String text = "Congratulations!";
            int textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            int x = gp.ScreenWidth/2 - textLength/2;
            int y = gp.ScreenHeight/2 + (gp.TileSize * 2);
            g2.drawString(text, x, y);

            g2.setFont(Arial_40);
            g2.setColor(Color.white);
            text = "Your time is : " + dFormat.format(playTime);
            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.ScreenWidth/2 - textLength/2;
            y = gp.ScreenHeight/2 + (gp.TileSize * 3);

            g2.drawString(text, x, y);

            gp.gameThread =  null;

        } else {
            g2.setFont(Arial_40);
            g2.setColor(Color.white);
            g2.drawImage(keyImg, gp.TileSize / 2, gp.TileSize / 2, gp.TileSize, gp.TileSize, null);
            g2.drawString("x " + gp.player.hasKey, 74, 65);

            // TIME
            playTime += (double) 1/60;
            g2.drawString("Time:" + dFormat.format(playTime), gp.TileSize*11, 65);
            // MESSAGE
            if (messageOn) {
                g2.setFont(g2.getFont().deriveFont(30F));
                g2.drawString(message, gp.TileSize / 2, gp.TileSize * 5);
                msgCount++;
                if (msgCount > 120) {
                    msgCount = 0;
                    messageOn = false;
                }
            }
        }
    }
}
