import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Door extends superObject{

    public OBJ_Door(Panel gp, boolean open) {
        super(gp);
        name = "Door";
        Opened = open;
        canPickUp = false;
        try{
            if(Opened){
                img = ImageIO.read(getClass().getResource("/Objects/doorOpened.png"));
                img = uTool.scaleImg(img, gp.TileSize, gp.TileSize);
                collision = false;
            }else {
                img = ImageIO.read(getClass().getResource("/Objects/doorClosed.png"));
                img = uTool.scaleImg(img, gp.TileSize, gp.TileSize);
                collision = true;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void open() throws IOException {
        if(!Opened){
            Opened = true;
            img = ImageIO.read(getClass().getResource("/Objects/doorOpened.png"));
            img = uTool.scaleImg(img, gp.TileSize, gp.TileSize);
            collision = false;
        }
    }
    }
