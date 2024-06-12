import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Door extends superObject{

    public OBJ_Door(Panel gp) {
        this.gp = gp;
        name = "Door";
        Opened = false;
        try{
            img = ImageIO.read(getClass().getResource("/Objects/doorClosed.png"));
            img = uTool.scaleImg(img, gp.TileSize, gp.TileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }
}