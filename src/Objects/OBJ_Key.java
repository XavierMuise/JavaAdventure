import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Key extends superObject{

    public OBJ_Key(Panel gp) {
        super(gp);
        name = "[Key]";
        description = "Might be able to \nunlock a chest or \ndoor";

        try{
            img = ImageIO.read(getClass().getResource("/Objects/Key.png"));
            img = uTool.scaleImg(img, gp.TileSize, gp.TileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
