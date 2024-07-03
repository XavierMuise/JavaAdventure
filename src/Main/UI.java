import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class UI {

    Panel gp;
    Graphics2D g2;
    Font maruMonica;

    public boolean messageOn;
    public String message = "";
    public int msgCount = 0;
    public boolean gameFinished = false;

    public String currentDialogue = "";



    public UI(Panel gp){
        this.gp = gp;

        try {
            InputStream is = getClass().getResourceAsStream("Font/x12y16pxMaruMonica.ttf");
            this.maruMonica = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException e){
            e.printStackTrace();
        } catch(IOException e){
            e.printStackTrace();
        }

    }

    public void showMessage(String msg){
        message = msg;
        messageOn = true;
    }

    public void draw(Graphics2D g2){
        this.g2 = g2;
        g2.setFont(maruMonica);
        g2.setColor(Color.white);

        if(gp.gameState == gp.playState){
            // health and shit
        } else if(gp.gameState == gp.pauseState){
            drawPauseScreen();
        } else if(gp.gameState == gp.dialogueState){
            drawDialogueScreen();
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


    public void drawDialogueScreen(){

        // WINDOW
        int x = gp.TileSize;
        int y = gp.TileSize * 8;
        int width = gp.ScreenWidth - (gp.TileSize*2);
        int height = gp.TileSize*4;
        drawDialogueWindow(x,y,width, height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F));
        x += gp.TileSize;
        y += gp.TileSize;
        for(String line : currentDialogue.split("\n")) {
            g2.drawString(line, x, y);
            y += 40;
        }
    }

    public void drawDialogueWindow(int x, int y, int width, int height){
        Color c =  new Color(0,0,0, 210);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);
        c = new Color(255,255,255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);
    }
}
