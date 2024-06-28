import java.util.Random;

public class Stranger extends Entity {
    
    public Stranger(Panel gp){
        super(gp);
        direction = "down";
        speed = 2;


        // COORDINATES
        worldX = gp.TileSize * 10;
        worldY = gp.TileSize * 9;
        
        getNPCimage();
    }

    public void getNPCimage(){
        up0 = SetUpImg("/NPC/HoodedStranger");
        up1 = SetUpImg("/NPC/HoodedStranger");
        up2 = SetUpImg("/NPC/HoodedStranger");

        down0 = SetUpImg("/NPC/HoodedStranger");
        down1 = SetUpImg("/NPC/HoodedStranger");
        down2 = SetUpImg("/NPC/HoodedStranger");

        left0 = SetUpImg("/NPC/HoodedStranger");
        left1 = SetUpImg("/NPC/HoodedStranger");
        left2 = SetUpImg("/NPC/HoodedStranger");

        right0 = SetUpImg("/NPC/HoodedStranger");
        right1 = SetUpImg("/NPC/HoodedStranger");
        right2 = SetUpImg("/NPC/HoodedStranger");
    }
    // doesnt move
    public void update(){

    }



}
