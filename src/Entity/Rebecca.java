import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Random;

public class Rebecca extends Entity{

    public Rebecca(Panel gp){
        super(gp);
        direction = "down";
        speed = 1;

        // COORDINATES
        worldX = gp.TileSize * 25;
        worldY = gp.TileSize * 19;
        setDialogue();
        getNPCimage();
    }

    public void getNPCimage(){
        up0 = SetUpImg("/NPC/rebeca");
        up1 = SetUpImg("/NPC/rebeca");
        up2 = SetUpImg("/NPC/rebeca");

        down0 = SetUpImg("/NPC/rebeca");
        down1 = SetUpImg("/NPC/rebeca");
        down2 = SetUpImg("/NPC/rebeca");

        left0 = SetUpImg("/NPC/rebeca");
        left1 = SetUpImg("/NPC/rebeca");
        left2 = SetUpImg("/NPC/rebeca");

        right0 = SetUpImg("/NPC/rebeca");
        right1 = SetUpImg("/NPC/rebeca");
        right2 = SetUpImg("/NPC/rebeca");
    }

    public void setDialogue(){
        dialogues = new String[4];
        dialogues[0] = "What do YOU want?";
        dialogues[1] = "...";
        dialogues[2] = "Get out of my face shithead.";

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

}
