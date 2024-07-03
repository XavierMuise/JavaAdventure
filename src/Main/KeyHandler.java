import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
            if(code == KeyEvent.VK_E){
                gp.npc[gp.player.NPC].speak();
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
        if(code == KeyEvent.VK_SHIFT ){
            shiftPressed = false;
        }
        if(code == KeyEvent.VK_T){
            debugMode = false;
        }

    }



    
}