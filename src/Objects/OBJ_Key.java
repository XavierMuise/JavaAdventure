import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Key extends superObject{

    public OBJ_Key() {
        name = "Key";

        try{
            img = ImageIO.read(getClass().getResource("/Objects/Key.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
