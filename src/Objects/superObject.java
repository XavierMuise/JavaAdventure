import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class superObject {
    public BufferedImage img;
    public String name;
    UtilityTool uTool = new UtilityTool();
    public boolean collision = false;
    public int worldX, worldY;
    public Rectangle solidArea = new Rectangle(0,0,48,48);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;
    Panel gp;
    public boolean Opened;
    public String description = "";
    public int attackScale = -1;
    boolean canPickUp;

    public superObject(Panel gp){
        this.gp = gp;
    }



    public void draw(Graphics2D g2, Panel gp){

        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if(worldX + gp.TileSize> gp.player.worldX - gp.player.screenX &&
                worldX - gp.TileSize< gp.player.worldX + gp.player.screenX &&
                worldY + gp.TileSize> gp.player.worldY - gp.player.screenY &&
                worldY - gp.TileSize< gp.player.worldY + gp.player.screenY) {
            g2.drawImage(img, screenX, screenY, gp.TileSize, gp.TileSize, null);

        }
    }

    public BufferedImage SetUpImg(String imgPath, int width, int height){
        UtilityTool uTool = new UtilityTool();
        BufferedImage ScaledImg = null;

        try{
            ScaledImg = ImageIO.read(getClass().getResource(imgPath + ".png"));
            ScaledImg = uTool.scaleImg(ScaledImg, width, height);

        }catch(IOException e){
            e.printStackTrace();
        }
        return ScaledImg;
    }

    public BufferedImage SetUpImg(String imgPath){
        UtilityTool uTool = new UtilityTool();
        BufferedImage ScaledImg = null;

        try{
            ScaledImg = ImageIO.read(getClass().getResource(imgPath + ".png"));
            ScaledImg = uTool.scaleImg(ScaledImg, gp.TileSize, gp.TileSize);

        }catch(IOException e){
            e.printStackTrace();
        }
        return ScaledImg;
    }

    public boolean use(){
        return false;
    }

}
