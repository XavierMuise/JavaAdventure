//Super class for Player, Npcs, enemies...
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Entity{

    public Panel gp;
    public int worldX,worldY; // coordinates
    public Rectangle solidArea = new Rectangle(0,0 , 48, 48);
    public Rectangle attackArea;
    public int solidAreaDefaultX, solidAreaDefaultY;
    public BufferedImage up0, up1, up2, down0, down1, down2, left0, left1, left2, right0, right1, right2;
    public int type;
    public static final int player = 0;
    public static final int NPC = 1;
    public static final int enemy = 2;
    public Projectile projectile;

    // STATE
    public String direction;
    public boolean invincible;
    public boolean collisionOn = false;
    public int spriteNum = 1;
    public boolean attacking = false;
    boolean alive = true;
    boolean dying = false;

    // COUNTERS
    public int actionCounter = 0;
    public int iframeCounter = 0;
    public int spriteCounter = 0;
    public int dyingCounter = 0;

    // ATTRIBUTES
    int damage;
    public int maxHP; // 2 HP for each heart
    public int HP;
    public int speed;
    public int resistance;

    public Entity(Panel gp){
        this.gp = gp;
    }

    public void SetAction(){

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


    public BufferedImage SetUpImg(String imgPath, int width, int height){
        UtilityTool uTool = new UtilityTool();
        BufferedImage ScaledImg = null;

        try{
            ScaledImg = ImageIO.read(getClass().getResource(imgPath + ".png"));
            ScaledImg = uTool.scaleImg(ScaledImg, width, height);

        }catch(IOException e){
            e.printStackTrace();
        }
        return ScaledImg;
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

    public void dyingAnimation(Graphics2D g2){

        dyingCounter++;

        if(dyingCounter <= 5){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0f));
        } else if(dyingCounter <= 10){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        } else if(dyingCounter <= 20){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0f));
        } else if(dyingCounter <= 30){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        } else if(dyingCounter <= 40){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0f));
        } else if(dyingCounter <= 50){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        } else if(dyingCounter <= 60){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0f));
        }

        if(dyingCounter > 60){
            alive = false;
        }

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

            if(invincible){
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
            }

            if(dying){
                dyingAnimation(g2);
            }

            g2.drawImage(img, screenX, screenY, gp.TileSize, gp.TileSize, null);
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

        }
    }



}