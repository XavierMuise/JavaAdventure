import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Chest extends superObject{
    public OBJ_Chest() {
        name = "Chest";
        Opened = false;

        try{
            img = ImageIO.read(getClass().getResource("/Objects/chestClosed.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }

   
}
