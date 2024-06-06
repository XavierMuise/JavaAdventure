import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Boots extends superObject{
    public OBJ_Boots() {
        name = "Boots";
        try{
            img = ImageIO.read(getClass().getResource("/Objects/Boots.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}