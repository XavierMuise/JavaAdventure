import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;


public class Player extends Entity {

    KeyHandler KH;

    public final int screenX;
    public final int screenY;
    public int NPC;
    boolean hasBoots = false;
    public int maxHP = 6; // 2 HP for each heart
    public int HP = 6;

    public Player(Panel gp, KeyHandler KH){
        super(gp);

        this.KH = KH;

        screenX = gp.ScreenWidth/2 - gp.TileSize/2;
        screenY = gp.ScreenHeight/2 - gp.TileSize/2;

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 24;
        solidArea.height = 24;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){
        worldX = gp.TileSize * 23;
        worldY = gp.TileSize * 21;
        speed = 4;
        direction = "down";
        
    }

    public void getPlayerImage(){
            up0 = SetUpImg("/Player/boy_up_0");
            up1 = SetUpImg("/Player/boy_up_1");
            up2 = SetUpImg("/Player/boy_up_2");

            down0 = SetUpImg("/Player/boy_down_0");
            down1 = SetUpImg("/Player/boy_down_1");
            down2 = SetUpImg("/Player/boy_down_2");

            left0 = SetUpImg("/Player/boy_left_0");
            left1 = SetUpImg("/Player/boy_left_1");
            left2 = SetUpImg("/Player/boy_left_2");

            right0 = SetUpImg("/Player/boy_right_0");
            right1 = SetUpImg("/Player/boy_right_1");
            right2 = SetUpImg("/Player/boy_right_2");

    }



    public void update(){

        if(KH.upPressed || KH.downPressed || KH.leftPressed || KH.rightPressed){

            if(KH.upPressed){
                direction = "up";
            }
             if(KH.leftPressed){
                direction = "left";
            }
            if(KH.downPressed){
                direction = "down";
            }
            if(KH.rightPressed){
                direction = "right";
            } 
            if(KH.upPressed && KH.leftPressed){
                direction = "upLeft";
            }
            if(KH.upPressed && KH.rightPressed){
                direction = "upRight";
            }
            if(KH.downPressed && KH.leftPressed){
                direction = "downLeft";
            }
            if(KH.downPressed && KH.rightPressed){
                direction = "downRight";
            }

            //CHECK TILE COLLISION
            collisionOn = false;
            gp.cChecker.checkTile(this);

            //CHECK OBJECT COLLISION
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

            //CHECK NPC COLLISION
            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);

            if(!collisionOn){
                switch(direction){
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
                    case "upLeft":
                        worldX -= speed;
                        worldY -= speed;
                        break;
                    case "upRight":
                        worldX += speed;
                        worldY -= speed;
                        break;
                    case "downLeft":
                        worldX -= speed;
                        worldY += speed;
                        break;
                    case "downRight":
                        worldX += speed;
                        worldY += speed;
                        break;
                }

            }

            spriteCounter++;

            if(spriteCounter > 10){
                if(spriteNum == 0){
                    spriteNum = 1;
                }else if(spriteNum == 1){
                    spriteNum = 2;
                }else if(spriteNum == 2){
                    spriteNum = 3;
                } else if(spriteNum == 3){
                    spriteNum = 4;
                } else if(spriteNum == 4){
                    spriteNum = 1;
                }
                spriteCounter = 0;

            }

        } else{
            spriteNum = 0;
        }

        //Sprinting
        if(KH.shiftPressed && hasBoots){
            if(direction.equals("upLeft") || direction.equals("upRight") || direction.equals("downLeft") ||
                    direction.equals("downRight")){
                speed = 4;
            } else {
                speed = 6;
            }
        }else{
            if(direction.equals("upLeft") || direction.equals("upRight") || direction.equals("downLeft") ||
                    direction.equals("downRight")){
                speed = 3;
            } else {
                speed = 4;
            }
        }

    }

    public void pickUpObject(int i){
        if(i != 999){

        }
    }

    public void interactNPC(int i){

        if(i != 999){

            if(gp.KH.interactPressed) {
                NPC = i;
                gp.gameState = gp.dialogueState;
                gp.npc[i].speak();
            }
        }
        gp.KH.interactPressed = false;

    }

    public void draw(Graphics2D g2, int TileSize){
        
        BufferedImage img = null;

        switch(direction){
            case "up":
                if(spriteNum == 0 || spriteNum == 2 || spriteNum == 4) {
                    img = up0;
                }
                if(spriteNum == 1) {
                    img = up1;
                }
                if(spriteNum == 3) {
                    img = up2;
                }
                break;
            case "down":
                if(spriteNum == 0 || spriteNum == 2 || spriteNum == 4) {
                    img = down0;
                }
                if(spriteNum == 1) {
                    img = down1;
                }
                if(spriteNum == 3) {
                    img = down2;
                }
                break;
            case "left", "upLeft", "downLeft":
                if(spriteNum == 0 || spriteNum == 2 || spriteNum == 4) {
                    img = left0;
                }
                if(spriteNum == 1) {
                    img = left1;
                }
                if(spriteNum == 3) {
                    img = left2;
                }
                break;
            case "right", "upRight", "downRight":
                if(spriteNum == 0 || spriteNum == 2 || spriteNum == 4) {
                    img = right0;
                }
                if(spriteNum == 1) {
                    img = right1;
                }
                if(spriteNum == 3) {
                    img = right2;
                }
                break;
        }

        g2.drawImage(img, screenX, screenY,null);
    }
}
