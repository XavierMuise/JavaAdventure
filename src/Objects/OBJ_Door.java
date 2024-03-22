import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Door extends superObject{

    public OBJ_Door() {
        name = "Door";
        Opened = false;
        try{
            img = ImageIO.read(getClass().getResource("/Objects/doorClosed.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }
}