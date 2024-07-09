import java.util.Random;

public class Stranger extends NPC {
    
    public Stranger(Panel gp){
        super(gp);
        direction = "down";
        speed = 2;


        // COORDINATES
        worldX = gp.TileSize * 10;
        worldY = gp.TileSize * 9;
        setDialogue();
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

    public void setDialogue(){
        dialogues = new String[12];
        dialogues[0] = "Greetings Traveler.";
        dialogues[1] = "Where are you from? your clothing seems unusual.";
        dialogues[2] = "...";
        dialogues[3] = "...You... Dont know?";
        dialogues[4] = "How did you get here?";
        dialogues[5] = "...";
        dialogues[6] = "You dont remember?";
        dialogues[7] = "It's possible you have amnesia...";
        dialogues[8] = "Id recommend heading to Monta village just up north.";
        dialogues[9] = "...";
        dialogues[10] = "Not a problem, best of luck on your travels.";
    }
    // doesnt move
    public void update(){

    }
}
