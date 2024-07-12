import java.util.Random;

public class MON_Slime extends Enemy{
    public MON_Slime(Panel gp, int x, int y) {
        super(gp);
        speed = 1;
        HP = 6;
        damage = 1;
        shardsDropped = 1;
        direction = "down";
        collisionOn = false;
        worldX = x;
        worldY = y;

        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 35;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getMONImage();
    }

    public void getMONImage(){
        up0 = SetUpImg("/MON/GreenSlime");
        up1 = SetUpImg("/MON/GreenSlimeUp");
        up2 = SetUpImg("/MON/GreenSlime");

        down0 = SetUpImg("/MON/GreenSlime");
        down1 = SetUpImg("/MON/GreenSlimeUp");
        down2 = SetUpImg("/MON/GreenSlime");

        left0 = SetUpImg("/MON/GreenSlime");
        left1 = SetUpImg("/MON/GreenSlimeUp");
        left2 = SetUpImg("/MON/GreenSlime");

        right0 = SetUpImg("/MON/GreenSlime");
        right1 = SetUpImg("/MON/GreenSlimeUp");
        right2 = SetUpImg("/MON/GreenSlime");
    }


    public void SetAction() {

        actionCounter++;

        if (actionCounter == 120) {
            Random rand = new Random();
            int i = rand.nextInt(100) + 1;
            if (i <= 25) {
                direction = "up";
            } else if (i <= 50) {
                direction = "down";
            } else if (i <= 75) {
                direction = "left";
            } else {
                direction = "right";
            }
            actionCounter = 0;
        }
    }

    public void damageReaction(){
        actionCounter = 0;
        direction = gp.player.direction;
    }

}
