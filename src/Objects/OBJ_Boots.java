import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Boots extends superObject{
    public OBJ_Boots(Panel gp) {
        name = "Boots";
        this.gp = gp;

        try{
            img = ImageIO.read(getClass().getResource("/Objects/Boots.png"));
            img = uTool.scaleImg(img, gp.TileSize, gp.TileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}