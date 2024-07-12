import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_hole extends superObject{

    public OBJ_hole(Panel gp){
        super(gp);
        name = "hole";

        try{
            img = ImageIO.read(getClass().getResource("/Objects/PitHole.png"));
            img = uTool.scaleImg(img, gp.TileSize, gp.TileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
