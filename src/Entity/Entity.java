//Super class for Player, Npcs, enemies...
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Entity{

    public Panel gp;
    public int worldX,worldY; // coordinates
    public int speed;

    public BufferedImage up0, up1, up2, down0, down1, down2, left0, left1, left2, right0, right1, right2;
    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 1;

    public Rectangle solidArea = new Rectangle(0,0 , 48, 48);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;

    public int actionCounter = 0;
    public String[] dialogues;
    int dialogueIndex = 0;


    public Entity(Panel gp){
        this.gp = gp;
    }

    public void SetAction(){

    }

    public void speak(){
        if(dialogues[dialogueIndex] == null){
            gp.gameState = gp.playState;
            dialogueIndex = 0;
        }
        gp.ui.currentDialogue = dialogues[dialogueIndex];
        dialogueIndex++;

        switch(gp.player.direction){
            case "up":
                direction = "down";
                break;
            case "down":
                direction = "up";
                break;
            case "left":
                direction = "right";
                break;
            case "right":
                direction = "left";
                break;
        }
    }

    public void update() {
        SetAction();
        collisionOn = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this, false);
        gp.cChecker.checkPlayer(this);


        if (!collisionOn) {
            switch (direction) {
                case "up":
                    worldY -= speed;
                    break;
                case "down":
                    worldY += speed;
                    break;
                case "left":
                    worldX -= speed;
                    break;
                case "right":
                    worldX += speed;
                    break;
            }
        }

        spriteCounter++;

        if (spriteCounter > 10) {
            if (spriteNum == 0) {
                spriteNum = 1;
            } else if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0;

        } else {
            spriteNum = 0;
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

    public void draw(Graphics2D g2){

        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if(worldX + gp.TileSize> gp.player.worldX - gp.player.screenX &&
                worldX - gp.TileSize< gp.player.worldX + gp.player.screenX &&
                worldY + gp.TileSize> gp.player.worldY - gp.player.screenY &&
                worldY - gp.TileSize< gp.player.worldY + gp.player.screenY) {

            BufferedImage img = null;
            switch(direction) {
                case "up":
                    if (spriteNum == 0) {
                        img = up0;
                    }
                    if (spriteNum == 1) {
                        img = up1;
                    }
                    if (spriteNum == 2) {
                        img = up2;
                    }
                    break;
                case "down":
                    if (spriteNum == 0) {
                        img = down0;
                    }
                    if (spriteNum == 1) {
                        img = down1;
                    }
                    if (spriteNum == 2) {
                        img = down2;
                    }
                    break;
                case "left":
                    if (spriteNum == 0) {
                        img = left0;
                    }
                    if (spriteNum == 1) {
                        img = left1;
                    }
                    if (spriteNum == 2) {
                        img = left2;
                    }
                    break;
                case "right":
                    if (spriteNum == 0) {
                        img = right0;
                    }
                    if (spriteNum == 1) {
                        img = right1;
                    }
                    if (spriteNum == 2) {
                        img = right2;
                    }
                    break;
            }

            g2.drawImage(img, screenX, screenY, gp.TileSize, gp.TileSize, null);

        }
    }



}