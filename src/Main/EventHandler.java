import java.awt.*;

public class EventHandler {

    public Panel gp;
    Rectangle eventRect;

    int previousEventX, previousEventY;
    boolean canTouchEvent = true;
    int eventRectDefaultX, eventRectDefaultY;

    public EventHandler(Panel gp){
        this.gp = gp;
        eventRect = new Rectangle();
        eventRect.x = 23;
        eventRect.y = 23;
        eventRect.width = 2;
        eventRect.height = 2;
        eventRectDefaultX = eventRect.x;
        eventRectDefaultY = eventRect.y;
    }

    public void checkEvent(){
        int distanceX = Math.abs(gp.player.worldX - previousEventX);
        int distanceY = Math.abs(gp.player.worldY - previousEventY);
        int distance  = Math.max(distanceX, distanceY);

        if(distance > gp.TileSize){
            canTouchEvent = true;
        }
        // world map
        if(gp.currentMap == 0) {
            if (hit(27, 16, "right") && canTouchEvent) {
                damagePit(gp.dialogueState);
                canTouchEvent = false;
            }

            if (hit(23, 12, "up") && canTouchEvent) {
                healingPool(gp.dialogueState);
                canTouchEvent = false;
            }

            if (hit(34, 38, "any") && canTouchEvent) {
                teleport(1, 12, 13);
            }
        }

        // house
        if(gp.currentMap == 1){
            if (hit(12, 13, "down") && canTouchEvent) {
                teleport(0, 34, 39);
            }
        }
    }

    public boolean hit(int eventCol, int eventRow, String reqDirection){
        boolean hit = false;
        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
        eventRect.x = eventCol*gp.TileSize + eventRect.x;
        eventRect.y = eventRow*gp.TileSize + eventRect.y;

        if(gp.player.solidArea.intersects(eventRect)){
            if(gp.player.direction.equals(reqDirection) || reqDirection.equals("any")){
                hit = true;
                previousEventX = gp.player.worldX;
                previousEventY = gp.player.worldY;
            }
        }
        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
        eventRect.x = eventRectDefaultX;
        eventRect.y = eventRectDefaultY;

        return hit;

    }

    public void damagePit(int gameState){
        gp.gameState = gameState;
        gp.ui.currentDialogue = "You fell into a pit!";
        gp.player.HP -= 1;

    }

    public void healingPool(int gameState){
        if(gp.player.HP != gp.player.maxHP) {
            gp.gameState = gameState;
            gp.ui.currentDialogue = "The pool restored your HP!";
            gp.player.HP = gp.player.maxHP;
        }
    }

    public void teleport(int mapNum, int x, int y){
        gp.currentMap = mapNum;
        gp.player.worldX = x * gp.TileSize;
        gp.player.worldY = y * gp.TileSize;

    }

}
