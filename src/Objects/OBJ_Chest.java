import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Chest extends superObject{
    public OBJ_Chest(superObject item) {
        name = "Chest";
        Opened = false;
        this.item = item;

        try{
            img = ImageIO.read(getClass().getResource("/Objects/chestClosed.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }

   
}
