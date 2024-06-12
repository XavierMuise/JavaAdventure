import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{


    public boolean upPressed, leftPressed, rightPressed, downPressed, shiftPressed;
    public boolean debugMode;
    // NOT used 
    public void keyTyped(KeyEvent e) {
        
    }

    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode(); // returns the int associated with the key pressed

        // WASD Keys, if pressed, go in that direction 

        if(code == KeyEvent.VK_W){
            upPressed = true;
        }
        if(code == KeyEvent.VK_A){
            leftPressed = true; 
        }
        if(code == KeyEvent.VK_S){
            downPressed = true;
        }
        if(code == KeyEvent.VK_D){
            rightPressed = true;
        }
        if(code == KeyEvent.VK_SHIFT ){
            shiftPressed = true;
        }
        if(code == KeyEvent.VK_T){
            debugMode = true;
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