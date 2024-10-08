import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Chest extends superObject{
    public OBJ_Chest(Panel gp) {
        super(gp);
        name = "Chest";
        Opened = false;
        canPickUp = false;

        try{
            img = ImageIO.read(getClass().getResource("/Objects/chestClosed.png"));
            img = uTool.scaleImg(img, gp.TileSize, gp.TileSize);

        } catch (IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }

   
}
