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
    public BufferedImage att_up_1, att_up_2, att_down_1, att_down_2, att_left_1, att_left_2, att_right_1, att_right_2;
    public superObject[][] Inventory;
    public int latestChunk = 0;

    // ATTRIBUTES
    public int level;
    public int vigor;
    public int strength;
    public int defense;
    public int shards;
    public int nextLevelShards;
    public superObject currentWeapon;

    public Player(Panel gp, KeyHandler KH){
        super(gp);

        this.KH = KH;

        screenX = gp.ScreenWidth/2 - gp.TileSize/2;
        screenY = gp.ScreenHeight/2 - gp.TileSize/2;

        type = Entity.player;
        Inventory = new superObject[4][5];
        solidArea = new Rectangle();
        solidArea.x = 14;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 20;
        solidArea.height = 24;
        attackArea = new Rectangle(36,36);

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){
        worldX = gp.TileSize * 23;
        worldY = gp.TileSize * 22;
        direction = "down";
        NPC = 999;

        level = 1;
        vigor = 1;
        strength = 1;
        defense = 0;

        shards = 0;
        nextLevelShards = 3 * level;

        maxHP = vigor * 2;
        HP = maxHP;
        speed = 4;
        resistance = defense / 3;
        currentWeapon = new SWORD_broken(gp);
        damage = strength * currentWeapon.attackScale;
        addItem(new OBJ_Key(gp));
        addItem(currentWeapon);


    }

    public void addItem(superObject item){
        // add item to first available inventory slot
        for(int i = 0; i < Inventory.length; i++){
            for(int j = 0; j < Inventory[i].length; j++){
                if(Inventory[i][j] == null){
                    Inventory[i][j] = item;
                    return;
                }
            }
        }

    }

    public void selectItem(){
        if(Inventory[gp.ui.slotRow][gp.ui.slotCol] != null && Inventory[gp.ui.slotRow][gp.ui.slotCol].attackScale != -1){
            currentWeapon = Inventory[gp.ui.slotRow][gp.ui.slotCol];
            updateStats();
        } else if(Inventory[gp.ui.slotRow][gp.ui.slotCol] != null){
            if(Inventory[gp.ui.slotRow][gp.ui.slotCol].use()) {
                Inventory[gp.ui.slotRow][gp.ui.slotCol] = null;
            }
        }
    }

    public void updateStats(){
        this.damage = strength * currentWeapon.attackScale;
        this.maxHP = vigor * 2;
        this.HP = maxHP;
        this.resistance = defense / 3;

    }

    public void getPlayerImage(){
            up0 = SetUpImg("/Player/boy_up_0", gp.TileSize, gp.TileSize);
            up1 = SetUpImg("/Player/boy_up_1", gp.TileSize, gp.TileSize);
            up2 = SetUpImg("/Player/boy_up_2", gp.TileSize, gp.TileSize);

            down0 = SetUpImg("/Player/boy_down_0", gp.TileSize, gp.TileSize);
            down1 = SetUpImg("/Player/boy_down_1", gp.TileSize, gp.TileSize);
            down2 = SetUpImg("/Player/boy_down_2", gp.TileSize, gp.TileSize);

            left0 = SetUpImg("/Player/boy_left_0", gp.TileSize, gp.TileSize);
            left1 = SetUpImg("/Player/boy_left_1", gp.TileSize, gp.TileSize);
            left2 = SetUpImg("/Player/boy_left_2", gp.TileSize, gp.TileSize);

            right0 = SetUpImg("/Player/boy_right_0", gp.TileSize, gp.TileSize);
            right1 = SetUpImg("/Player/boy_right_1", gp.TileSize, gp.TileSize);
            right2 = SetUpImg("/Player/boy_right_2", gp.TileSize, gp.TileSize);

            // attack sprites
            att_up_1 = SetUpImg("/Player/attack_up_1", gp.TileSize, gp.TileSize*2);
            att_up_2 = SetUpImg("/Player/attack_up_2", gp.TileSize, gp.TileSize*2);

            att_down_1 = SetUpImg("/Player/attack_down_1", gp.TileSize, gp.TileSize*2);
            att_down_2 = SetUpImg("/Player/attack_down_2", gp.TileSize, gp.TileSize*2);

            att_left_1 = SetUpImg("/Player/attack_left_1", gp.TileSize*2, gp.TileSize);
            att_left_2 = SetUpImg("/Player/attack_left_2", gp.TileSize*2, gp.TileSize);

            att_right_1 = SetUpImg("/Player/attack_right_1", gp.TileSize*2, gp.TileSize);
            att_right_2 = SetUpImg("/Player/attack_right_2", gp.TileSize*2, gp.TileSize);
    }

    public void update(){

        if(attacking){
            attack();
        } else if(KH.upPressed || KH.downPressed || KH.leftPressed || KH.rightPressed || KH.interactPressed){
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
            int npcIndex = gp.cChecker.checkEntity(this, gp.npc[gp.currentMap]);
            interactNPC(npcIndex);

            //CHECK ENEMY COLLISION
            int enemyIndex = gp.cChecker.checkEntity(this, gp.mon[gp.currentMap]);
            contactEnemy(enemyIndex);

            //CHECK CHUNK COLLISION
            int chunkIndex = gp.cChecker.checkEntity(this, gp.chunks[gp.currentMap]);
            restAtChunk(chunkIndex);


            // CHECK EVENT
            gp.EH.checkEvent();

            if(!collisionOn && !KH.interactPressed){
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
            gp.KH.interactPressed = false;

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

        if(invincible){
            iframeCounter++;
            if(iframeCounter > 60){
                iframeCounter = 0;
                invincible = false;
            }
        }

        if(HP <= 0){
            gp.gameState = gp.deathState;
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

    public void attack(){

        spriteCounter++;

        if(spriteCounter <= 5){
            spriteNum = 1;
        } else if(spriteCounter <= 25){
            spriteNum = 2;

            // store current info
            int worldx = worldX;
            int worldy = worldY;
            int solidAreaW = solidArea.width;
            int solidAreaH = solidArea.height;

            switch(direction){
                case "up": worldY -= attackArea.height; break;
                case "down": worldY += attackArea.height; break;
                case "left": worldX -= attackArea.width; break;
                case "right": worldX += attackArea.width; break;
            }
            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;

            int monIndex = gp.cChecker.checkEntity(this, gp.mon[gp.currentMap]);
            damageEnemy(monIndex);


            worldX = worldx;
            worldY = worldy;
            solidArea.width = solidAreaW;
            solidArea.height = solidAreaH;

        } else{
            spriteNum = 0;
            spriteCounter = 0;
            attacking = false;
        }

    }

    public void damageEnemy(int i){
        if(i != 999) {
            if(!gp.mon[gp.currentMap][i].invincible) {
                gp.mon[gp.currentMap][i].HP -= damage;
                gp.mon[gp.currentMap][i].invincible = true;
                gp.mon[gp.currentMap][i].damageReaction();
                if(gp.mon[gp.currentMap][i].HP <= 0){
                    gp.mon[gp.currentMap][i].dying = true;
                    shards += gp.mon[gp.currentMap][i].shardsDropped;
                }
            }
        }

    }

    public void pickUpObject(int i){
        if(i != 999 && Inventory[3][4] == null && gp.obj[gp.currentMap][i].canPickUp){
            addItem(gp.obj[gp.currentMap][i]);
            gp.obj[gp.currentMap][i] = null;
        }
    }

    public void interactNPC(int i){

        if(i != 999){
            if(gp.KH.interactPressed) {
                NPC = i;
                gp.gameState = gp.dialogueState;
                gp.npc[gp.currentMap][i].speak();
            }
        }
        gp.KH.interactPressed = false;
    }

    public void contactEnemy(int i){
        if(i != 999){
            if(!invincible) {
                gp.mon[gp.currentMap][i].contactPlayer();
            } else {
                iframeCounter++;
            }
        }
    }

    public void restAtChunk(int i){
        if(i != 999){
            gp.chunks[gp.currentMap][i].rest();
            latestChunk = i;
        }
    }

    public void levelUp(String Attribute){
        if(shards >= nextLevelShards){
            switch(Attribute){
                case "vigor":
                    if(vigor < 20) {
                        vigor++;
                        updateStats();
                        shards -= nextLevelShards;
                        level++;
                        nextLevelShards = level * 3;
                    }
                    break;
                case "strength":
                    if(strength < 20) {
                        strength++;
                        updateStats();
                        shards -= nextLevelShards;
                        level++;
                        nextLevelShards = level * 3;
                    }
                    break;
                case "defense":
                    if(defense < 20) {
                        defense++;
                        updateStats();
                        shards -= nextLevelShards;
                        level++;
                        nextLevelShards = level * 3;
                    }
                    break;
            }
        }
    }

    public void respawn(){
        gp.chunks[gp.currentMap][latestChunk].rest();
        invincible = false;
    }

    public void draw(Graphics2D g2, int TileSize){
        
        BufferedImage img = null;
        int tempScreenX = screenX;
        int tempScreenY = screenY;

        switch(direction){
            case "up":
                if(!attacking) {
                    if (spriteNum == 0 || spriteNum == 2 || spriteNum == 4) {
                        img = up0;
                    }
                    if (spriteNum == 1) {
                        img = up1;
                    }
                    if (spriteNum == 3) {
                        img = up2;
                    }
                } else {
                    tempScreenY = screenY - gp.TileSize;
                    if (spriteNum == 0){
                        img = up0;
                    }
                    if (spriteNum == 1){
                        img = att_up_1;
                    }
                    if(spriteNum == 2){
                        img = att_up_2;
                    }

                }
                break;
            case "down":
                if(!attacking) {
                    if (spriteNum == 0 || spriteNum == 2 || spriteNum == 4) {
                        img = down0;
                    }
                    if (spriteNum == 1) {
                        img = down1;
                    }
                    if (spriteNum == 3) {
                        img = down2;
                    }
                } else {
                    if (spriteNum == 0){
                        img = down0;
                    }
                    if (spriteNum == 1){
                        img = att_down_1;
                    }
                    if(spriteNum == 2){
                        img = att_down_2;
                    }

                }
                break;
            case "left", "upLeft", "downLeft":
                if(!attacking) {
                    if (spriteNum == 0 || spriteNum == 2 || spriteNum == 4) {
                        img = left0;
                    }
                    if (spriteNum == 1) {
                        img = left1;
                    }
                    if (spriteNum == 3) {
                        img = left2;
                    }
                } else {
                    tempScreenX = screenX - gp.TileSize;
                    if (spriteNum == 0){
                        img = left0;
                    }
                    if (spriteNum == 1){
                        img = att_left_1;
                    }
                    if(spriteNum == 2){
                        img = att_left_2;
                    }

                }
                break;
            case "right", "upRight", "downRight":
                if(!attacking) {
                    if (spriteNum == 0 || spriteNum == 2 || spriteNum == 4) {
                        img = right0;
                    }
                    if (spriteNum == 1) {
                        img = right1;
                    }
                    if (spriteNum == 3) {
                        img = right2;
                    }
                } else {
                    if (spriteNum == 0){
                        img = right0;
                    }
                    if (spriteNum == 1){
                        img = att_right_1;
                    }
                    if(spriteNum == 2){
                        img = att_right_2;
                    }

                }
                break;
        }

        if(invincible){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        }
        g2.drawImage(img, tempScreenX, tempScreenY,null);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }
}
