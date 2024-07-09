import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{

    public Panel gp;

    public boolean upPressed, leftPressed, rightPressed, downPressed, shiftPressed, interactPressed, attackPressed;
    public boolean debugMode;


    public KeyHandler(Panel gp){
        this.gp = gp;
    }
    // NOT used 
    public void keyTyped(KeyEvent e) {
        
    }

    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode(); // returns the int associated with the key pressed

        // PLAY STATE
        if(gp.gameState == gp.playState || gp.gameState == gp.pauseState) {
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
            if (code == KeyEvent.VK_ESCAPE) {
                if(gp.gameState == gp.playState) {
                    gp.gameState = gp.pauseState;
                } else if(gp.gameState == gp.pauseState) {
                    gp.gameState = gp.playState;
                }
            }
        }

        // DIALOGUE STATE

        if(gp.gameState == gp.dialogueState){
            if(code == KeyEvent.VK_E && gp.player.NPC != 999){
                gp.npc[gp.player.NPC].speak();
            }
            if(code == KeyEvent.VK_E && gp.player.NPC == 999){
                gp.gameState = gp.playState;
            }

        }

        // TITLE SCREEN STATE

        if(gp.gameState == gp.titleState){
            if(code == KeyEvent.VK_ENTER){
                // NEW GAME
                if(gp.ui.commandNum == 0){
                    gp.gameState = gp.playState;
                }
                // LOAD GAME
                if(gp.ui.commandNum == 1){
                    gp.gameState = gp.playState;
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



    
}