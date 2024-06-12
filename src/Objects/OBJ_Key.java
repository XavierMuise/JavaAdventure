import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Key extends superObject{

    public OBJ_Key(Panel gp) {
        name = "Key";
        this.gp = gp;

        try{
            img = ImageIO.read(getClass().getResource("/Objects/Key.png"));
            img = uTool.scaleImg(img, gp.TileSize, gp.TileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
