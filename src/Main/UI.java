import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
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
    BufferedImage FullHeart, HalfHeart, EmptyHeart;

    public String currentDialogue = "";
    public int attributeNum = 0;
    public int commandNum = 0 ;



    public UI(Panel gp){
        this.gp = gp;

        try {
            InputStream is = getClass().getResourceAsStream("Font/x12y16pxMaruMonica.ttf");
            this.maruMonica = Font.createFont(Font.TRUETYPE_FONT, is);
            FullHeart = SetUpImg("/HUD/HeartFull");
            HalfHeart = SetUpImg("/HUD/HeartHalf");
            EmptyHeart = SetUpImg("/HUD/HeartEmpty");
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

        if(gp.gameState == gp.titleState){
            drawTitleScreen();
        }
        else if(gp.gameState == gp.playState){
            drawHealthBar();
        } else if(gp.gameState == gp.pauseState){
            drawPauseScreen();
            drawHealthBar();
        } else if(gp.gameState == gp.dialogueState){
            drawDialogueScreen();
            drawHealthBar();
        } else if(gp.gameState == gp.statsState){
            drawUpgradeScreen();
            drawHealthBar();
        }
    }
    public void drawHealthBar(){
        int x = gp.TileSize/4;
        int y = 0;

        // DRAW MAX LIFE
        for(int i = 0; i < gp.player.maxHP/2; i++){
            if(i == 10){
                y = gp.TileSize - gp.TileSize/4;
                x = gp.TileSize/4;
            }
            g2.drawImage(EmptyHeart, x, y, gp.TileSize - gp.TileSize/4, gp.TileSize - gp.TileSize/4, null);
            x += gp.TileSize - gp.TileSize/4;
        }

        x = gp.TileSize/4;
        y = 0;
        int i = 0;

        // DRAW CURRENT LIFE
        while(i < gp.player.HP){
            if(i == 20){
                y = gp.TileSize - gp.TileSize/4;
                x = gp.TileSize/4;
            }
            g2.drawImage(HalfHeart, x, y, gp.TileSize - gp.TileSize/4, gp.TileSize - gp.TileSize/4, null);
            i++;
            if( i < gp.player.HP){
                g2.drawImage(FullHeart, x, y, gp.TileSize - gp.TileSize/4, gp.TileSize - gp.TileSize/4, null);
            }
            i++;
            x += gp.TileSize - gp.TileSize/4;
        }

    }

    public void drawTitleScreen(){
        // TITLE BACKGROUND
        g2.setColor(Color.blue);
        g2.fillRect(0,0, gp.ScreenWidth, gp.ScreenHeight);
        // TITLE NAME
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 90F));
        String text = "Shards of Red Mirror"; // cant think of a title
        int x = getXCenteredText(text);
        int y = gp.TileSize * 3;
        // TITLE SHADOW
        g2.setColor(Color.black);
        g2.drawString(text, x + 4, y + 4);
        // TITLE MAIN COLOR
        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        // PLAYER IMG
        x = gp.ScreenWidth/2 - gp.TileSize;
        y += gp.TileSize*2;
        g2.drawImage(gp.player.down0, x,y, gp.TileSize*2, gp.TileSize*2, null);

        // MENU
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));
        text = "NEW GAME";
        x = getXCenteredText(text);
        y += gp.TileSize*3;
        g2.setColor(Color.black);
        g2.drawString(text, x + 4, y + 4);
        g2.setColor(Color.white);
        g2.drawString(text, x, y);
        if(commandNum == 0){
            g2.setColor(Color.black);
            g2.drawString(">", x - gp.TileSize + 4, y + 4);
            g2.setColor(Color.white);
            g2.drawString(">", x - gp.TileSize, y);
        }

        text = "LOAD GAME";
        x = getXCenteredText(text);
        y += gp.TileSize;
        g2.setColor(Color.black);
        g2.drawString(text, x + 4, y + 4);
        g2.setColor(Color.white);
        g2.drawString(text, x, y);
        if(commandNum == 1){
            g2.setColor(Color.black);
            g2.drawString(">", x - gp.TileSize + 4, y + 4);
            g2.setColor(Color.white);
            g2.drawString(">", x - gp.TileSize, y);
        }

        text = "QUIT";
        x = getXCenteredText(text);
        y += gp.TileSize;
        g2.setColor(Color.black);
        g2.drawString(text, x + 4, y + 4);
        g2.setColor(Color.white);
        g2.drawString(text, x, y);
        if(commandNum == 2){
            g2.setColor(Color.black);
            g2.drawString(">", x - gp.TileSize + 4, y + 4);
            g2.setColor(Color.white);
            g2.drawString(">", x - gp.TileSize, y);
        }

    }
    public void drawPauseScreen(){
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
        String txt = "PAUSED";
        int x = getXCenteredText(txt);
        int y = gp.ScreenHeight/2 + gp.TileSize/2;
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
    public void drawUpgradeScreen(){
        final int frameX = gp.TileSize*2;
        final int frameY = gp.TileSize;
        final int frameWidth = gp.TileSize*5;
        final int frameHeight= gp.TileSize*10;
        drawDialogueWindow(frameX, frameY, frameWidth, frameHeight);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F));
        g2.setColor(Color.white);

        int textX = frameX + 20;
        int textY = frameY + gp.TileSize;
        final int lineHeight = 32;
        int tailX = (frameX + frameWidth) - 30;

        g2.drawString("Level", textX, textY);
        g2.drawString("" + gp.player.level, tailX, textY);
        textY += lineHeight;
        g2.drawString("Shards Held" , textX, textY);
        g2.drawString("" + gp.player.shards, tailX, textY);
        textY += lineHeight;
        g2.drawString("Shards Needed" , textX, textY);
        g2.drawString("" + gp.player.nextLevelShards, tailX, textY);
        textY += lineHeight;
        g2.drawString("--------------------", textX, textY);

        // ATTRIBUTES
        textY += lineHeight;
        g2.drawString("Attributes" , textX, textY);
        textY += lineHeight;

        g2.drawString("Vigor" , textX, textY);
        g2.drawString("" + gp.player.vigor, tailX, textY);
        if(attributeNum == 0){
            g2.setColor(Color.white);
            g2.drawString(">",textX - 32 , textY );
        }
        textY += lineHeight;

        g2.drawString("Strength", textX, textY);
        g2.drawString("" + gp.player.strength, tailX, textY);
        if(attributeNum == 1){
            g2.setColor(Color.white);
            g2.drawString(">",textX - 32 , textY );
        }
        textY += lineHeight;

        g2.drawString("Defence", textX, textY);
        g2.drawString("" + gp.player.defense, tailX, textY);
        if(attributeNum == 2){
            g2.setColor(Color.white);
            g2.drawString(">",textX - 32 , textY );
        }
        textY += lineHeight;
        g2.drawString("--------------------", textX, textY);

        // STATS
        textY += lineHeight;
        g2.drawString("Stats" , textX, textY);
        textY += lineHeight;
        g2.drawString("HP" , textX, textY);
        g2.drawString("" + gp.player.maxHP, tailX, textY);
        textY += lineHeight;
        g2.drawString("Damage" , textX, textY);
        g2.drawString("" + gp.player.damage, tailX, textY);
        textY += lineHeight;
        g2.drawString("Speed", textX, textY);
        g2.drawString("" + gp.player.speed, tailX, textY);
        textY += lineHeight;
        g2.drawString("Resistance" , textX, textY);
        g2.drawString("" + gp.player.resistance, tailX, textY);


    }
    public BufferedImage SetUpImg(String imgPath){
        UtilityTool uTool = new UtilityTool();
        BufferedImage ScaledImg = null;

        try{
            ScaledImg = ImageIO.read(getClass().getResource(imgPath + ".png"));
            ScaledImg = uTool.scaleImg(ScaledImg, gp.TileSize, gp.TileSize);

        }catch(IOException e){
            e.printStackTrace();
        }
        return ScaledImg;
    }
}
