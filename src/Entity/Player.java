import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;


public class Player extends Entity {
    
    Panel gp;
    KeyHandler KH;

    public final int screenX;
    public final int screenY;
    int hasKey = 0;
    boolean hasBoots = false;

    public Player(Panel gp, KeyHandler KH){

        this.gp = gp;
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

        try {
            up0 = ImageIO.read(getClass().getResource("/Player/boy_up_0.png"));
            up1 = ImageIO.read(getClass().getResource("/Player/boy_up_1.png"));
            up2 = ImageIO.read(getClass().getResource("/Player/boy_up_2.png"));

            down0 = ImageIO.read(getClass().getResource("/Player/boy_down_0.png"));
            down1 = ImageIO.read(getClass().getResource("/Player/boy_down_1.png"));
            down2 = ImageIO.read(getClass().getResource("/Player/boy_down_2.png"));

            left0 = ImageIO.read(getClass().getResource("/Player/boy_left_0.png"));
            left1 = ImageIO.read(getClass().getResource("/Player/boy_left_1.png"));
            left2 = ImageIO.read(getClass().getResource("/Player/boy_left_2.png"));

            right0 = ImageIO.read(getClass().getResource("/Player/boy_right_0.png"));
            right1 = ImageIO.read(getClass().getResource("/Player/boy_right_1.png"));
            right2 = ImageIO.read(getClass().getResource("/Player/boy_right_2.png"));

        } catch(IOException e){
            e.printStackTrace();
        }
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
                    spriteNum = 1;
                }
                spriteCounter = 0;

            }

        } else{
            spriteNum = 0;
        }

        //Sprinting
        if(KH.shiftPressed && this.hasBoots){
            speed = 8;
        }else{
            speed = 4;
        }



    }

    public void pickUpObject(int i){
        if(i != 999){
            String objName = gp.obj[i].name;

            switch(objName){
                case "Key":
                    hasKey++;
                    gp.obj[i] = null;
                    break;
                case "Door":
                    if(hasKey > 0 && !gp.obj[i].Opened ){
                        gp.obj[i].collision = false;
                        gp.obj[i].Opened = true;
                        hasKey--;
                        try{
                            gp.obj[i].img = ImageIO.read(getClass().getResource("/Player/doorOpened.png"));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case "Chest":
                    if(!gp.obj[i].Opened){
                        gp.obj[i].Opened = true;
                        gp.obj[i].item.revealed = true;
                        try{
                            gp.obj[i].img =  ImageIO.read(getClass().getResource("/Player/chestOpened.png"));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    break;

                case "Boots":
                    hasBoots = true;
                    gp.obj[i] = null;
                    break;
            }
        }
    }

    public void draw(Graphics2D g2, int TileSize){
        
        BufferedImage img = null;

        switch(direction){
            case "up":
                if(spriteNum == 0) {
                    img = up0;
                }
                if(spriteNum == 1) {
                    img = up1;
                }
                if(spriteNum == 2) {
                    img = up2;
                }
                break;
            case "down":
                if(spriteNum == 0) {
                    img = down0;
                }
                if(spriteNum == 1) {
                    img = down1;
                }
                if(spriteNum == 2) {
                    img = down2;
                }
                break;
            case "left":
                if(spriteNum == 0) {
                    img = left0;
                }
                if(spriteNum == 1) {
                    img = left1;
                }
                if(spriteNum == 2) {
                    img = left2;
                }
                break;
            case "right":
                if(spriteNum == 0) {
                    img = right0;
                }
                if(spriteNum == 1) {
                    img = right1;
                }
                if(spriteNum == 2) {
                    img = right2;
                }
                break;
            case "upLeft":
                if(spriteNum == 0) {
                    img = left0;
                }
                if(spriteNum == 1) {
                    img = left1;
                }
                if(spriteNum == 2) {
                    img = left2;
                }
                break;
            case "upRight":
                if(spriteNum == 0) {
                    img = right0;
                }
                if(spriteNum == 1) {
                    img = right1;
                }
                if(spriteNum == 2) {
                    img = right2;
                }
                break;
            case "downLeft":
                if(spriteNum == 0) {
                    img = left0;
                }
                if(spriteNum == 1) {
                    img = left1;
                }
                if(spriteNum == 2) {
                    img = left2;
                }
                break;
            case "downRight":
                if(spriteNum == 0) {
                    img = right0;
                }
                if(spriteNum == 1) {
                    img = right1;
                }
                if(spriteNum == 2) {
                    img = right2;
                }
                break;
        }

        g2.drawImage(img, screenX, screenY, TileSize, TileSize, null);
    }
}
