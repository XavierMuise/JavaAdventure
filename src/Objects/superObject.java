import java.awt.*;
import java.awt.image.BufferedImage;

public class superObject {
    public BufferedImage img;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;
    public Rectangle solidArea = new Rectangle(0,0,48,48);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;

    public boolean Opened;

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

}
