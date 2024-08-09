import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedWriter;

public class KeyHandler implements KeyListener{

    public Panel gp;

    public boolean upPressed, leftPressed, rightPressed, downPressed, shiftPressed, interactPressed;
    public boolean debugMode;


    public KeyHandler(Panel gp){
        this.gp = gp;
    }
    // NOT used 
    public void keyTyped(KeyEvent e) {
        
    }

    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode(); // returns the int associated with the key pressed

        if (code == KeyEvent.VK_ESCAPE) {
            if(gp.gameState == gp.playState) {
                gp.gameState = gp.pauseState;
                gp.ui.commandNum = 0;
            } else if(gp.gameState == gp.pauseState) {
                gp.gameState = gp.playState;
            }
        }
        // PLAY STATE
        if(gp.gameState == gp.playState) {
            playState(code);
        }

        // DIALOGUE STATE
        if(gp.gameState == gp.dialogueState){
           dialogueState(code);
        }

        // TITLE SCREEN STATE
        if(gp.gameState == gp.titleState){
            titleState(code);
        }

        // UPGRADE SCREEN STATE
        if(gp.gameState == gp.statsState){
            upgradeState(code);
        }

        // INVENTORY SCREEN STATE
        if(gp.gameState == gp.inventoryState){
            invState(code);
        }

        // PAUSE/OPTIONS STATE
        if(gp.gameState == gp.pauseState){
            optionsState(code);
        }

        if(gp.gameState == gp.deathState){
            deathState(code);
        }

        if(gp.gameState == gp.tradeState){
            tradeState(code);
        }

        if(gp.gameState == gp.mapState){
            mapState(code);
        }
    }
    public void playState(int code){
        if (code == KeyEvent.VK_W) {
            upPressed = true;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = true;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = true;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = true;
        }
        if (code == KeyEvent.VK_SHIFT) {
            shiftPressed = true;
        }
        if (code == KeyEvent.VK_T) {
            debugMode = true;
        }
        if (code == KeyEvent.VK_E) {
            interactPressed = true;
        }
        if(code == KeyEvent.VK_Q){
            gp.player.attacking = true;
        }
        if(code == KeyEvent.VK_I){
            gp.gameState = gp.inventoryState;
        }
        if(code == KeyEvent.VK_M){
            gp.gameState = gp.mapState;
        }
    }
    public void dialogueState(int code){
        if(code == KeyEvent.VK_E && gp.player.NPC != 999){
            gp.npc[gp.currentMap][gp.player.NPC].speak();
        }
        if(code == KeyEvent.VK_E && gp.player.NPC == 999){
            gp.gameState = gp.playState;
        }
    }
    public void titleState(int code){
        if(code == KeyEvent.VK_ENTER){
            // NEW GAME
            if(gp.ui.commandNum == 0){
                gp.gameState = gp.playState;
            }
            // LOAD GAME
            if(gp.ui.commandNum == 1){
                gp.gameState = gp.playState;
                gp.ui.commandNum = 0;
            }
            // EXIT
            if(gp.ui.commandNum == 2){
                System.exit(0);
            }
        }
        if(code == KeyEvent.VK_DOWN && gp.ui.commandNum < 2){
            gp.ui.commandNum++;
        }
        if(code == KeyEvent.VK_UP && gp.ui.commandNum > 0){
            gp.ui.commandNum--;
        }
    }
    public void upgradeState(int code){

        if(code == KeyEvent.VK_ESCAPE){
            gp.ui.attributeNum = 0;
            gp.gameState = gp.playState;
        }
        if(code == KeyEvent.VK_ENTER){
            // Vigor
            if(gp.ui.attributeNum == 0){
                gp.player.levelUp("vigor");
            }
            // Strength
            if(gp.ui.attributeNum == 1){
                gp.player.levelUp("strength");
            }
            // Defense
            if(gp.ui.attributeNum == 2){
                gp.player.levelUp("defense");
            }
        }

        if(code == KeyEvent.VK_DOWN && gp.ui.attributeNum < 2){
            gp.ui.attributeNum++;
        }
        if(code == KeyEvent.VK_UP && gp.ui.attributeNum > 0){
            gp.ui.attributeNum--;
        }
    }
    public void invState(int code){
        if(code == KeyEvent.VK_ESCAPE){
            gp.gameState = gp.playState;
        }

        if(code == KeyEvent.VK_DOWN && gp.ui.slotRow < 3){
            gp.ui.slotRow++;
        }
        if(code == KeyEvent.VK_UP && gp.ui.slotRow > 0){
            gp.ui.slotRow--;
        }
        if(code == KeyEvent.VK_LEFT && gp.ui.slotCol > 0){
            gp.ui.slotCol--;
        }
        if(code == KeyEvent.VK_RIGHT && gp.ui.slotCol < 4){
            gp.ui.slotCol++;
        }
        if(code == KeyEvent.VK_E){
            gp.player.selectItem();
        }
    }
    public void optionsState(int code){

        if(code == KeyEvent.VK_ENTER){
            switch(gp.ui.commandNum){
                // Full Screen
                case 0:
                    gp.fullScreen = !gp.fullScreen;
                    break;
                // Music
                case 1:
                    break;
                // Sound Effects
                case 2:
                    break;
                // Controls
                case 3:
                    break;
                // Quit Game
                case 4:
                    Main.cg.saveConfig(gp);
                    System.exit(0);
                    break;
                // Back
                case 5:
                    gp.gameState = gp.playState;
                    break;
            }
        }
        if(code == KeyEvent.VK_DOWN && gp.ui.commandNum < 5){
            gp.ui.commandNum++;
        }
        if(code == KeyEvent.VK_UP && gp.ui.commandNum > 0){
            gp.ui.commandNum--;
        }

    }
    public void deathState(int code){
        if(code == KeyEvent.VK_ENTER){
            switch(gp.ui.deathNum){
                // respawn
                case 0:
                    gp.player.respawn();
                    break;
                // quit
                case 1:
                    Main.cg.saveConfig(gp);
                    System.exit(0);
                    break;

            }
        }
        if(code == KeyEvent.VK_RIGHT && gp.ui.deathNum == 0){
            gp.ui.deathNum = 1;
        }
        if(code == KeyEvent.VK_LEFT && gp.ui.deathNum == 1){
            gp.ui.deathNum = 0;
        }
    }
    public void tradeState(int code){
        if(code == KeyEvent.VK_ESCAPE){
            gp.gameState = gp.playState;
        }

        if(code == KeyEvent.VK_E){
            if(gp.player.shards >= gp.ui.npc.Inventory[gp.ui.slotRow][gp.ui.slotCol].price && gp.player.Inventory[3][4] == null) {
                if(gp.player.canObtain(gp.ui.npc.Inventory[gp.ui.slotRow][gp.ui.slotCol])) {
                    gp.player.shards -= gp.ui.npc.Inventory[gp.ui.slotRow][gp.ui.slotCol].price;
                }
            }

        }

        if(code == KeyEvent.VK_DOWN && gp.ui.slotRow < 3){
            gp.ui.slotRow++;
        }
        if(code == KeyEvent.VK_UP && gp.ui.slotRow > 0){
            gp.ui.slotRow--;
        }
        if(code == KeyEvent.VK_LEFT && gp.ui.slotCol > 0){
            gp.ui.slotCol--;
        }
        if(code == KeyEvent.VK_RIGHT && gp.ui.slotCol < 4){
            gp.ui.slotCol++;
        }

    }
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode(); // returns the int associated with the key pressed


        // WASD Keys, if released, stop going in that direction
        
        if(code == KeyEvent.VK_W){
            upPressed = false;
        }
        if(code == KeyEvent.VK_A){
            leftPressed = false; 
        }
        if(code == KeyEvent.VK_S){
            downPressed = false;
        }
        if(code == KeyEvent.VK_D){
            rightPressed = false;
        }
        if(code == KeyEvent.VK_E){
            interactPressed = false;
        }
        if(code == KeyEvent.VK_SHIFT ){
            shiftPressed = false;
        }
        if(code == KeyEvent.VK_T){
            debugMode = false;
        }

    }
    public void mapState(int code){
        if(code == KeyEvent.VK_ESCAPE){
            gp.gameState = gp.playState;
        }
    }
}