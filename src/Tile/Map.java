import java.awt.*;
import java.awt.image.BufferedImage;

public class Map extends TileManager{
    BufferedImage worldMap[];
    public boolean mapOn = false;

    public Map(Panel gp){
        super(gp);
        createWorldMap();

    }

    public void createWorldMap(){
        worldMap = new BufferedImage[gp.maxMap];

        int worldMapWidth = gp.TileSize * gp.maxWorldCol;
        int worldMapHeight = gp.TileSize * gp.maxWorldRow;

        for(int i = 0; i < gp.maxMap; i++){
            worldMap[i] = new BufferedImage(worldMapWidth, worldMapHeight, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2 = (Graphics2D)worldMap[i].createGraphics();

            int col = 0;
            int row = 0;

            while(col < gp.maxWorldCol && row < gp.maxWorldRow){

                int TileNum = mapTileNum[i][col][row];

                int x = gp.TileSize * col;
                int y = gp.TileSize * row;
                g2.drawImage(tile[TileNum].img, x, y, null);
                col++;

                if(col >= gp.maxWorldCol){
                    col = 0;
                    row++;
                }
            }
            g2.dispose();
        }
    }

    public void drawFullMapScreen(Graphics2D g2){
        g2.setColor(Color.black);
        g2.fillRect(0, 0, gp.ScreenWidth, gp.ScreenHeight);

        // Draw Map

        int width = 500;
        int height = 500;
        int x = gp.ScreenWidth / 2 - width / 2;
        int y = gp.ScreenHeight / 2 - height / 2;
        g2.drawImage(worldMap[gp.currentMap], x, y, width, height, null);

        // draw scale

        double scale = (double) (gp.TileSize * gp.maxWorldCol) / width;
        int playerX = (int)(x + gp.player.worldX / scale);
        int playerY = (int)(y + gp.player.worldY / scale);
        int playerSize = (int) (gp.TileSize/3);
        g2.drawImage(gp.player.down0, playerX-3, playerY, playerSize, playerSize, null);

    }
}
