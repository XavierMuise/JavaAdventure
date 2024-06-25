import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UI {

    Panel gp;
    Graphics2D g2;
    Font Arial_40, Arial_80B;
    // BufferedImage keyImg;

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
        // OBJ_Key k = new OBJ_Key(gp);
        // keyImg = k.img;
    }

    public void showMessage(String msg){
        message = msg;
        messageOn = true;
    }

    public void draw(Graphics2D g2){
        this.g2 = g2;
        g2.setFont(Arial_40);
        g2.setColor(Color.white);

        if(gp.gameState == gp.playState){
            // health and shit
        } else if(gp.gameState == gp.pauseState){
            drawPauseScreen();
        }
    }

    public void drawPauseScreen(){
        String txt = "PAUSED";
        int x = getXCenteredText(txt);
        int y = gp.ScreenHeight/2;
        g2.drawString(txt, x, y);

    }

    public int getXCenteredText(String text){
        int length  = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return gp.ScreenWidth/2 - length/2;
    }


}
