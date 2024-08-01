import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Boots extends superObject{
    public OBJ_Boots(Panel gp) {
        super(gp);
        name = "[Boots]";
        canPickUp = true;
        description = "Boots that can help \nyou run faster. \nhold SHIFT to use them";
        price = 20;

        try{
            img = ImageIO.read(getClass().getResource("/Objects/Boots.png"));
            img = uTool.scaleImg(img, gp.TileSize, gp.TileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean use(){
        gp.player.hasBoots = true;
        return true;
    }
}