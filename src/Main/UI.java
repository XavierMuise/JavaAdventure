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
    public Entity npc;

    public String currentDialogue = "";
    public int attributeNum = 0;
    public int commandNum = 0;
    public int deathNum = 0;

    public int slotCol = 0;
    public int slotRow = 0;
    public int subState = 0;
    public int transitionCounter = 0;



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
        } else if(gp.gameState == gp.inventoryState){
            drawInventoryScreen();
            drawHealthBar();
        } else if(gp.gameState == gp.deathState){
            drawDeathScreen();
            drawHealthBar();
        } else if(gp.gameState == gp.transitionState){
            drawTransition();
        } else if(gp.gameState == gp.tradeState){
            drawTradeScreen();
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

        // DRAW SHARD COUNT
        g2.drawImage(gp.chunks[0][0].down0, gp.TileSize * 18,0, gp.TileSize - gp.TileSize/4, gp.TileSize - gp.TileSize/4, null);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F));
        g2.setColor(Color.white);
        g2.drawString("" + gp.player.shards, gp.TileSize * 18 + gp.TileSize/2 + 10, gp.TileSize - gp.TileSize/4 - 5);


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
        final int frameX = gp.TileSize*6;
        final int frameY = gp.TileSize;
        final int frameWidth = gp.TileSize*8;
        final int frameHeight= gp.TileSize*10;
        drawDialogueWindow(frameX, frameY, frameWidth, frameHeight);

        switch(subState){
            case 0:
                optionsTop(frameX, frameY);
                break;
            case 1: break;
            case 2: break;
        }

    }
    public void optionsTop(int frameX, int frameY){
        int textX;
        int textY;
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 32F));
        String text = "Options";
        textX = getXCenteredText(text);
        textY = frameY + gp.TileSize;
        g2.drawString(text, textX, textY);

        textX = frameX + gp.TileSize;
        int CursorX = textX - gp.TileSize/2;
        textY += gp.TileSize*2;
        g2.drawString("FullScreen", textX, textY);
        if(commandNum == 0){
            g2.drawString(">", CursorX, textY);
        }

        // checkbox
        g2.drawRect(frameX + gp.TileSize * 4, textY - gp.TileSize/2, gp.TileSize/2, gp.TileSize/2);
        if(gp.fullScreen){
            g2.fillRect(frameX + gp.TileSize * 4, textY - gp.TileSize/2, gp.TileSize/2, gp.TileSize/2);
        }

        textY += gp.TileSize;
        g2.drawString("Music", textX, textY);
        if(commandNum == 1){
            g2.drawString(">", CursorX, textY);
        }

        textY += gp.TileSize;
        g2.drawString("SE", textX, textY);
        if(commandNum == 2){
            g2.drawString(">", CursorX, textY);
        }

        textY += gp.TileSize;
        g2.drawString("Controls", textX, textY);
        if(commandNum == 3){
            g2.drawString(">", CursorX, textY);
        }

        textY += gp.TileSize;
        g2.drawString("Quit Game", textX, textY);
        if(commandNum == 4){
            g2.drawString(">", CursorX, textY);
        }

        textY += gp.TileSize*2;
        g2.drawString("Back", textX, textY);
        if(commandNum == 5){
            g2.drawString(">", CursorX, textY);
        }



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
        final int frameWidth = gp.TileSize*6;
        final int frameHeight= gp.TileSize*10;
        drawDialogueWindow(frameX, frameY, frameWidth, frameHeight);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F));
        g2.setColor(Color.white);

        int textX = frameX + 20;
        int textY = frameY + gp.TileSize;
        final int lineHeight = 32;
        int tailX = (frameX + frameWidth) - 60;

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
        if(attributeNum == 0 && gp.gameState == gp.statsState){
            g2.setColor(Color.white);
            g2.drawString(">",textX - 32 , textY );
        }
        textY += lineHeight;

        g2.drawString("Strength", textX, textY);
        g2.drawString("" + gp.player.strength, tailX, textY);
        if(attributeNum == 1 && gp.gameState == gp.statsState){
            g2.setColor(Color.white);
            g2.drawString(">",textX - 32 , textY );
        }
        textY += lineHeight;

        g2.drawString("Defence", textX, textY);
        g2.drawString("" + gp.player.defense, tailX, textY);
        if(attributeNum == 2 && gp.gameState == gp.statsState){
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
    public void drawInventoryScreen(){
        final int frameX = gp.TileSize*12;
        final int frameY = gp.TileSize;
        final int frameWidth = gp.TileSize*6;
        final int frameHeight= gp.TileSize*5;
        drawDialogueWindow(frameX, frameY, frameWidth, frameHeight);
        drawUpgradeScreen();

        // SLOTS
        final int slotXStart = frameX + 20;
        final int slotYStart = frameY + 20;
        int slotX = slotXStart;
        int slotY = slotYStart;
        int slotSize = gp.TileSize + 3;
        // CURSOR
        int cursorX = slotXStart + (slotSize * slotCol);
        int cursorY = slotYStart + (slotSize * slotRow);
        int cursorWidth = gp.TileSize;
        int cursorHeight = gp.TileSize;
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight,10,10);

        // ITEMS
        for(int i = 0; i < gp.player.Inventory.length; i++){
            for(int j = 0; j < gp.player.Inventory[i].length; j++){
                if(gp.player.Inventory[i][j] != null){
                    if(gp.player.Inventory[i][j] == gp.player.currentWeapon){
                        g2.setColor(Color.orange);
                        g2.fillRoundRect(slotX, slotY, gp.TileSize, gp.TileSize, 10, 10);
                    }
                    g2.drawImage(gp.player.Inventory[i][j].img, slotX, slotY, null);
                    slotX += slotSize;
                }
            }
            slotY += slotSize;
            slotX = slotXStart;
        }

        // Item Description

        int dFrameX = frameX;
        int dFrameY = frameY + frameHeight + 20;
        int dFrameWidth = frameWidth;
        int dFrameHeight = gp.TileSize*4;
        if(gp.player.Inventory[slotRow][slotCol] != null) {
            drawDialogueWindow(dFrameX, dFrameY, dFrameWidth, dFrameHeight);
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F));
            g2.setColor(Color.white);
            dFrameY += gp.TileSize;
            g2.drawString(gp.player.Inventory[slotRow][slotCol].name, dFrameX + 20, dFrameY);
            dFrameY += 32;
            for(String line : gp.player.Inventory[slotRow][slotCol].description.split("\n")) {
                g2.drawString(line, dFrameX + 20, dFrameY);
                dFrameY += 32;
            }
            if(gp.player.Inventory[slotRow][slotCol].attackScale != -1){
                g2.drawString("Damage scaling : x" + gp.player.Inventory[slotRow][slotCol].attackScale,
                        dFrameX + 20, frameY + frameHeight + dFrameHeight);
            }

        }


    }
    public void drawDeathScreen(){
        int textX;
        int textY;

        g2.setColor(Color.red);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
        String text = "YOU DIED";
        textX = getXCenteredText(text);
        textY = gp.TileSize * 6;
        g2.drawString(text, textX, textY);

        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));
        text = "Respawn";
        textX = gp.TileSize * 7;
        textY = gp.TileSize * 8;
        g2.drawString(text, textX, textY);
        if(deathNum == 0){
            g2.drawString(">", textX - gp.TileSize/3, textY);
        }

        text = "Quit";
        textX = gp.TileSize * 11 + gp.TileSize/2;
        g2.drawString(text, textX, textY);
        if(deathNum == 1){
            g2.drawString(">",textX - gp.TileSize/3, textY);
        }




    }
    public void drawTransition(){
        transitionCounter++;
        g2.setColor(new Color(0,0,0, transitionCounter * 5));
        g2.fillRect(0,0, gp.ScreenWidth, gp.ScreenHeight);
        if(transitionCounter == 50){
            transitionCounter = 0;
            gp.gameState = gp.playState;
            gp.currentMap = gp.EH.tempMap;
            gp.player.worldX = gp.EH.tempX;
            gp.player.worldY = gp.EH.tempY;
        }

    }
    public void drawTradeScreen(){
        final int frameX = gp.TileSize*12;
        final int frameY = gp.TileSize;
        final int frameWidth = gp.TileSize*6;
        final int frameHeight= gp.TileSize*5;
        drawDialogueWindow(frameX, frameY, frameWidth, frameHeight);

        // SLOTS
        final int slotXStart = frameX + 20;
        final int slotYStart = frameY + 20;
        int slotX = slotXStart;
        int slotY = slotYStart;
        int slotSize = gp.TileSize + 3;
        // CURSOR
        int cursorX = slotXStart + (slotSize * slotCol);
        int cursorY = slotYStart + (slotSize * slotRow);
        int cursorWidth = gp.TileSize;
        int cursorHeight = gp.TileSize;
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight,10,10);

        // ITEMS
        for(int i = 0; i < npc.Inventory.length; i++){
            for(int j = 0; j < npc.Inventory[i].length; j++){
                if(npc.Inventory[i][j] != null){
                    g2.drawImage(npc.Inventory[i][j].img, slotX, slotY, null);
                    slotX += slotSize;
                }
            }
            slotY += slotSize;
            slotX = slotXStart;
        }

        // Item Description

        int dFrameX = frameX;
        int dFrameY = frameY + frameHeight + 20;
        int dFrameWidth = frameWidth;
        int dFrameHeight = gp.TileSize*4;
        if(npc.Inventory[slotRow][slotCol] != null) {
            drawDialogueWindow(dFrameX, dFrameY, dFrameWidth, dFrameHeight);
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F));
            g2.setColor(Color.white);
            dFrameY += gp.TileSize;
            g2.drawString(npc.Inventory[slotRow][slotCol].name, dFrameX + 20, dFrameY);
            dFrameY += 32;
            for(String line : npc.Inventory[slotRow][slotCol].description.split("\n")) {
                g2.drawString(line, dFrameX + 20, dFrameY);
                dFrameY += 32;
            }
            g2.drawString("Price : " + npc.Inventory[slotRow][slotCol].price,
                    dFrameX + 20, frameY + frameHeight + dFrameHeight);


        }

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
