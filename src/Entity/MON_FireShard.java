import java.util.Random;

public class MON_FireShard extends Enemy {
    public MON_FireShard(Panel gp, int x, int y) {
        super(gp);
        speed = 2;
        HP = 12;
        damage = 3;
        shardsDropped = 5;
        direction = "down";
        collisionOn = false;
        worldX = x;
        worldY = y;

        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 32;
        solidArea.height = 42;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        projectile = new PROJ_FireBall(gp);
        gp.aSetter.addProj(projectile);

        getMONImage();
    }
    public void getMONImage(){
        up0 = SetUpImg("/MON/FireShard");
        up1 = SetUpImg("/MON/FireShard");
        up2 = SetUpImg("/MON/FireShard");

        down0 = SetUpImg("/MON/FireShard");
        down1 = SetUpImg("/MON/FireShard");
        down2 = SetUpImg("/MON/FireShard");

        left0 = SetUpImg("/MON/FireShard");
        left1 = SetUpImg("/MON/FireShard");
        left2 = SetUpImg("/MON/FireShard");

        right0 = SetUpImg("/MON/FireShard");
        right1 = SetUpImg("/MON/FireShard");
        right2 = SetUpImg("/MON/FireShard");
    }

    public void SetAction() {
        actionCounter++;

        if (actionCounter == 120) {
            projectile.set(worldX, worldY, direction, true, this);
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
