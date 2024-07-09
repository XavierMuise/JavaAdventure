public class NPC extends Entity{
    int dialogueIndex = 0;
    public String[] dialogues;

    public NPC(Panel gp) {
        super(gp);
        type = Entity.NPC;
    }

    public void speak(){
        if(dialogues[dialogueIndex] == null){
            gp.player.NPC = 999;
            gp.gameState = gp.playState;
            dialogueIndex = 0;
        } else {
            gp.ui.currentDialogue = dialogues[dialogueIndex];
            dialogueIndex++;
        }

        switch(gp.player.direction){
            case "up":
                direction = "down";
                break;
            case "down":
                direction = "up";
                break;
            case "left":
                direction = "right";
                break;
            case "right":
                direction = "left";
                break;
        }
    }
}
