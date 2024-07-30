public class Merchant extends NPC {
    public Merchant(Panel gp) {
        super(gp);
        direction = "down";
        speed = 1;
        setDialogue();
        getNPCimage();
    }


    public void getNPCimage(){
        up0 = SetUpImg("/NPC/Merchant");
        up1 = SetUpImg("/NPC/Merchant");
        up2 = SetUpImg("/NPC/Merchant");

        down0 = SetUpImg("/NPC/Merchant");
        down1 = SetUpImg("/NPC/Merchant");
        down2 = SetUpImg("/NPC/Merchant");

        left0 = SetUpImg("/NPC/Merchant");
        left1 = SetUpImg("/NPC/Merchant");
        left2 = SetUpImg("/NPC/Merchant");

        right0 = SetUpImg("/NPC/Merchant");
        right1 = SetUpImg("/NPC/Merchant");
        right2 = SetUpImg("/NPC/Merchant");
    }

    public void setDialogue(){
        dialogues = new String[3];
        dialogues[0] = "Sup";

    }

    // doesnt move
    public void update(){

    }
    

}
